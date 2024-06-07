import os
from airflow.hooks.base import BaseHook
import boto3
import requests
from bs4 import BeautifulSoup
import pymysql
from datetime import datetime, timedelta
import pandas as pd
import google.generativeai as genai
import yfinance as yf
from airflow.providers.amazon.aws.hooks.s3 import S3Hook

# 나스닥 100 기업 명단
companies = {
    'MSFT': 'microsoft-corp',
    'AAPL': 'apple-computer-inc'
}

industries = {
    'MSFT': 'Software - Infrastructure / Technology',
    'AAPL': 'Consumer Electronics / Technology'
}

# ## S3 Bucket 이름
BUCKET_NAME="버켓이름 입력"

## Gemini API key
GEM_API_KEY = "API key 입력"

# Gemini 모델 로드
genai.configure(api_key=GEM_API_KEY)
genai.GenerationConfig(max_output_tokens=180)
MODEL = genai.GenerativeModel('gemini-pro')


# S3
def s3_connection():
    connection = BaseHook.get_connection('airflow_s3')
    s3_client = boto3.client('s3',
                             aws_access_key_id=connection.login,
                             aws_secret_access_key=connection.password,
                             region_name=connection.extra_dejson.get('region_name'))
    print("S3 bucket connected!")
    return s3_client

def upload_to_s3(client, article, bucket_name, file_name):
    try:
        client.put_object(Bucket=bucket_name, Key=file_name, Body=bytes(article, 'utf-8'))
        print(f"Successfully uploaded {file_name} to {bucket_name}")
    except Exception as e:
        print(f"Failed to upload {file_name} to {bucket_name}: {e}")
        raise


def read_text_from_s3(client, bucket_name, file_name):
    response = client.get_object(Bucket=bucket_name, Key=file_name)
    text_data = response['Body'].read().decode('utf-8')
    return f"{text_data}\n\n"


# MySQL 함수 선언
def mysql_connection():
    connection=BaseHook.get_connection('mysql')
    conn = pymysql.connect(
            host=connection.host,
            user=connection.login,
            password=connection.password,
            db=connection.schema,
            port=int(connection.port)
    )
    return conn

def insert_data(conn, SQL, *args):
    cursor = conn.cursor(pymysql.cursors.DictCursor)
    cursor.execute(SQL, args)
    conn.commit()


# 뉴스 크롤링 함수들

# investing.com 뉴스 목록에 있는 뉴스들 url 수집
## url 접속 후 기사 작성 시간 확인하기 위함
def get_urls(ticker):
    urls = []
    for i in range(1, 3):
        url = f"https://www.investing.com/equities/{companies[ticker]}-news/{i}"
        
        response = requests.get(url)
        soup = BeautifulSoup(response.text, 'html.parser')
        
        articles = soup.select('li[class="border-b border-[#E6E9EB] first:border-t last:border-0"]')
        
        for article in articles:
            pro = article.select_one('svg[height="17.5"]')
            if not pro:
                title_element = article.select_one('a[data-test="article-title-link"]')
                href = title_element.get('href')
                link = "http://www.investing.com" + href
                urls.append(link)
                
    print(f"{ticker}의 뉴스기사 {len(urls)}개를 확인합니다.")
    return urls

# 기사 작성시간 리턴
def get_written_time(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    wtime = soup.select('div[class="flex flex-col gap-2 text-warren-gray-700 md:flex-row md:items-center md:gap-0"] div span')
    if not wtime:
        return None
    wtime = wtime[0].text[10:]
    if wtime[-2:] == 'AM':
        year = wtime[6:10]
        month = wtime[:2]
        day = wtime[3:5]
        hour = wtime[12:14]
        minute = wtime[15:17]
        wtime = datetime.strptime(f"{year}-{month}-{day} {hour}:{minute}", "%Y-%m-%d %H:%M")
    else:
        year = wtime[6:10]
        month = wtime[:2]
        day = wtime[3:5]
        if int(wtime[12:14]) == 12:
            hour = wtime[12:14]
        else:
            hour = str(int(wtime[12:14]) + 12)
        minute = wtime[15:17]
        wtime = datetime.strptime(f"{year}-{month}-{day} {hour}:{minute}", "%Y-%m-%d %H:%M")
    return wtime

# 기사 원문 크롤링하는 함수
def get_article(wtime, url, ticker, s3_client):
    print(f"{wtime}에 작성된 기사를 긁어옵니다.")
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    
    all_p_tags = soup.select('div[id="article"] p')
    blockquote_p_tags = soup.select('div[id="article"] blockquote p')
    filtered_p_tags = [p for p in all_p_tags if p not in blockquote_p_tags]
    
    article = "\n".join([text.text for text in filtered_p_tags if text.name != 'span'])
    
    ymd = str(wtime)[:10]
    upload_to_s3(s3_client, article, BUCKET_NAME,
                 f"news/article/{ticker}/year={ymd[:4]}/month={ymd[5:7]}/day={ymd[8:10]}/{wtime}.txt")
    return article

def summarize_article(wtime, ticker, article, s3_client):
    prompt = f"""
    I would like you to summarize the provided news article about {ticker} in a concise and objective way, mainly focusing on the impact on {ticker}'s stock.
    
    Followings are rules you must follow when generating an answer.
    
    1. If article includes advertisement such as "InvestingPro", it must be excluded from the summary.
    2. Do NOT start with an opening sentence, just give me the answer right away.
    3. The answer MUST be in Korean.
    4. When translating into Korean, you must end each sentence with period(.).
    5. The final Korean translated summary MUST NOT EXCEED 5 sentences.

    article: 
    {article}
    """
    try:
        summary = MODEL.generate_content(prompt).text
        ymd = str(wtime)[:10]
        file_name = f"news/summary/{ticker}/year={ymd[:4]}/month={ymd[5:7]}/day={ymd[8:10]}/{wtime}_sum.txt"
        upload_to_s3(s3_client, summary, BUCKET_NAME, file_name)
        print(f"Summary: {summary}")
        return file_name
    
    except Exception as e:
        print(f"Failed to summarize article: {e}")
        return "오류 발생"

def return_indicators(ticker):
    
    nasdaq = yf.Ticker(ticker).info
    
    name = nasdaq["shortName"]
    industry = f'{nasdaq["industry"]}/{nasdaq["sector"]}'
    industry = industry.replace(" & ","-")
    industry = industry.replace("—", " - ")
    
    market_cap = nasdaq["marketCap"]
    
    try:
        per = round(nasdaq["trailingPE"], 2)
    except:
        per = 0
    
    try:
        psr = round(nasdaq["priceToSalesTrailing12Months"], 2)
    except:
        psr = 0

    try:
        pbr = round(nasdaq["priceToBook"], 2)
    except:
        pbr = 0

    try:
        ev_ebitda = round(nasdaq["enterpriseToEbitda"], 2)
    except:
        ev_ebitda = 0

    return name, industry, market_cap, per, psr, pbr, ev_ebitda

def execute(tickers):
    s3_client = s3_connection()
    conn = mysql_connection()
    
    for ticker in tickers:
        urls = get_urls(ticker)
        summaries = []
        count = 0

        for idx, url in enumerate(urls):
            wtime = get_written_time(url)
            if wtime is not None:
                now = datetime.now().strftime("%Y-%m-%d")
                # now = datetime(2024,5,30)
                year = now.year
                month = now.month
                day = now.day

                previous_close_time = datetime.strptime(f"{year}-{month}-{day} 16:00", "%Y-%m-%d %H:%M") - timedelta(days=1)
                crawling_time = datetime.strptime(f"{year}-{month}-{day} 7:00", "%Y-%m-%d %H:%M")

                if previous_close_time <= wtime <= crawling_time:
                    count += 1
                    article = get_article(wtime, url, ticker, s3_client)
                    # s3 적재
                    summary = summarize_article(wtime, ticker, article, s3_client)
                    summaries.append(summary)
                else:
                    continue
            else:
                continue

        SQL1_INSERT = '''
        INSERT INTO daily_update (dailydate, ticker, name, industry, news_summary, market_cap, per, psr, pbr, ev_ebitda)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        '''

        SQL1_UPDATE = '''
        UPDATE daily_update
        SET news_summary = %s, market_cap = %s, per = %s, psr = %s, pbr = %s, ev_ebitda = %s
        WHERE dailydate = %s AND ticker = %s
        '''

        if count == 0:
            date = datetime.strptime(str(crawling_time)[:10], "%Y-%m-%d")
            summary = "None"
            name, industry, market_cap, per, psr, pbr, ev_ebitda = return_indicators(ticker)

            df = pd.DataFrame({"date": [date], "ticker": [ticker], "name": [name], "industry": [industry], "summary": [summary],
                               "market_cap": [market_cap], "per": [per], "psr": [psr], "pbr": [pbr], "ev_ebitda": [ev_ebitda]})
            
            df["date"] = pd.to_datetime(df["date"])
            df = df[~df["date"].dt.dayofweek.isin([5, 6])]
            
            if df.empty:
                print(f"{ticker} 주말 데이터는 제외합니다.")
                continue
            
            # DB에 데이터가 이미 존재하는지 여부
            try:
                with conn.cursor() as cursor:
                    cursor.execute("SELECT COUNT(*) FROM daily_update WHERE dailydate = %s AND ticker = %s", (date, ticker))
                    result = cursor.fetchone()
            
                    if result[0] == 0:
                        cursor.execute(SQL1_INSERT, (date, ticker, name, industry, summary, market_cap, per, psr, pbr, ev_ebitda))
                    else:
                        cursor.execute(SQL1_UPDATE, (summary, market_cap, per, psr, pbr, ev_ebitda, date, ticker))
                    
                    conn.commit()
                    print(f"{ticker} insert/update 완료")

            except Exception as e:
                print(f"Failed to insert/update data for {ticker}: {e}")
                conn.rollback()
        
        else:
            date = datetime.strptime(str(crawling_time)[:10], "%Y-%m-%d")
            name, industry, market_cap, per, psr, pbr, ev_ebitda = return_indicators(ticker)

            for summary in summaries:
                df = pd.DataFrame({"date": [date], "ticker": [ticker], "name": [name], "industry": [industry], "summary": [summary],
                                   "market_cap": [market_cap], "per": [per], "psr": [psr], "pbr": [pbr], "ev_ebitda": [ev_ebitda]})
                df["date"] = pd.to_datetime(df["date"])
                df = df[~df["date"].dt.dayofweek.isin([5, 6])]
                
                if df.empty:
                    print(f"{ticker} 주말 데이터는 제외합니다.")
                    continue
                
                try:
                    with conn.cursor() as cursor:
                        cursor.execute("SELECT COUNT(*) FROM daily_update WHERE dailydate = %s AND ticker = %s", (date, ticker))
                        result = cursor.fetchone()
                        
                        if result[0] == 0:
                            cursor.execute(SQL1_INSERT, (date, ticker, name, industry, summary, market_cap, per, psr, pbr, ev_ebitda))
                        else:
                            cursor.execute(SQL1_UPDATE, (summary, market_cap, per, psr, pbr, ev_ebitda, date, ticker))
                        
                        conn.commit()
                        print(f"{ticker} insert/update 완료")
                
                except Exception as e:
                    print(f"Failed to insert/update data for {ticker}: {e}")
                    conn.rollback()
                
                print(f"{ticker} DB에 저장합니다.")
    
    conn.close()

