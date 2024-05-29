import pandas as pd
import re, boto3, string, pymysql
from collections import Counter
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer

### 환경변수 불러오기
from main.python.config import *


def wc():
    conn = pymysql.connect(
        host=HOST, user=USER, password=PASSWORD, db=DB, charset="utf8"
    )

    cur = conn.cursor()
    cur.execute("select dailydate, ticker, name, news_summary from daily_update")
    result = cur.fetchall()

    cur.close()
    conn.close()

    result_df = pd.DataFrame(result)
    result_df.columns = ["dailydate", "ticker", "name", "news_summary"]
    s3_client = boto3.client(
        "s3",
        aws_access_key_id=AWS_ACCESS_KEY_ID,
        aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
        region_name=AWS_DEFAULT_REGION,
    )

    contents = []
    for i in range(len(result_df["news_summary"])):
        if (result_df["news_summary"][i] == "None") or (
            (result_df["news_summary"][i] == "오류 발생")
        ):
            content = "None"
        else:
            content = (
                s3_client.get_object(
                    Bucket=BUCKET_NAME, Key=result_df["news_summary"][i]
                )["Body"]
                .read()
                .decode()
            )
        contents.append(content)

    result_df["contents"] = contents

    stop_words = set(stopwords.words("english"))
    list_punct = list(string.punctuation)
    lemmatizer = WordNetLemmatizer()
    token = [
        _
        for _ in re.sub("\W+", " ", result_df["contents"].sum()).split()
        if _ not in stop_words
    ]
    tags = Counter(token).most_common(20)

    jd = []
    for x, y in tags:
        jd.append({"tag": x, "count": y})

    return jd
