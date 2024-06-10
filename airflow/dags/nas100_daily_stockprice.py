import warnings
warnings.filterwarnings(action='ignore')
import FinanceDataReader as fdr
import pymysql
import pandas as pd
from datetime import datetime
from airflow.hooks.base import BaseHook


def read_stock_data(ticker, current_date):
    df = fdr.DataReader(ticker, '2022-01-01', current_date)
    if 'Close' not in df.columns:
        raise KeyError(f"DataFrame does not contain 'Close' column for ticker {ticker}")
    df = df.reset_index()
    df = df.assign(Ticker=ticker, axis=0)
    df['ma20'] = df['Close'].rolling(window=20).mean()
    df['std'] = df['Close'].rolling(window=20).std()
    df['upper'] = df['ma20'] + (df['std'] * 2)
    df['lower'] = df['ma20'] - (df['std'] * 2)
    df = df.fillna(value="None")
    df_ohlc = df[['Ticker', 'Date', 'Open', 'High', 'Low', 'Close', 'ma20', 'std', 'upper', 'lower']]
    df_ohlc['Date'] = pd.to_datetime(df_ohlc['Date'])
    df_ohlc['Date'] = df_ohlc['Date'].astype('int64') // 10**6
    return df_ohlc


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

def insert_data(tickers):
    conn = mysql_connection()
    today = datetime.strftime(datetime(2024, 6, 6), "%Y-%m-%d") 
    # today = datetime.now().strftime("%Y-%m-%d")

    SQL7_INSERT = '''
    INSERT INTO stock_data (ticker, date, open_price, high_price, low_price, close_price, ma20, std, upper, lower)
    VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
    '''

    SQL7_UPDATE = '''
    UPDATE stock_data
    SET open_price = %s, high_price = %s, low_price = %s, close_price = %s, ma20 = %s, std = %s, upper = %s, lower = %s
    WHERE ticker = %s AND date = %s
    '''
    
    try:
        with conn.cursor() as cursor:
            for ticker in tickers:
                df_ohlc = read_stock_data(ticker, today)
                
                cursor.execute("SELECT COUNT(*) FROM stock_data WHERE ticker = %s", (ticker,))
                result = cursor.fetchone()
                
                if result[0] == 0:
                    # 데이터가 없는 경우 전체 데이터프레임을 삽입
                    for index, row in df_ohlc.iterrows():
                        try:
                            cursor.execute(SQL7_INSERT, (row['Ticker'], row['Date'], row['Open'], row['High'], row['Low'],
                                                        row['Close'], row['ma20'], row['std'], row['upper'], row['lower']))
                        except pymysql.err.IntegrityError as e:
                            if e.args[0] == 1062:  # 중복 항목이 있을 경우 업데이트
                                cursor.execute(SQL7_UPDATE, (row['Open'], row['High'], row['Low'], row['Close'], row['ma20'], 
                                                            row['std'], row['upper'], row['lower'], row['Ticker'], row['Date']))
                            else:
                                raise e
                else:
                    # 데이터가 있는 경우 마지막 행만 삽입
                    last_row = df_ohlc.iloc[-1]
                    try:
                        cursor.execute(SQL7_INSERT, (last_row['Ticker'], last_row['Date'], last_row['Open'], last_row['High'],
                                                    last_row['Low'], last_row['Close'], last_row['ma20'], last_row['std'],
                                                    last_row['upper'], last_row['lower']))
                    except pymysql.err.IntegrityError as e:
                        if e.args[0] == 1062:  # 중복 항목이 있을 경우 업데이트
                            cursor.execute(SQL7_UPDATE, (last_row['Open'], last_row['High'], last_row['Low'], last_row['Close'],
                                                        last_row['ma20'], last_row['std'], last_row['upper'], last_row['lower'],
                                                        last_row['Ticker'], last_row['Date']))
                        else:
                            raise e
                
                print(f"{ticker} insert/update 완료")
            
            conn.commit()
    
    except Exception as e:
        print(f"Failed to insert/update data: {e}")
        conn.rollback()
    
    finally:
        conn.close()