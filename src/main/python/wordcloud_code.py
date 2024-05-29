import pandas as pd
import boto3, pymysql
from collections import Counter
from konlpy.tag import Okt

### 환경변수 불러오기
from config import *


def mysql_conn(HOST, USER, PASSWORD, DB, SQL, CHARSET="utf8"):
    conn = pymysql.connect(
        host=HOST, user=USER, password=PASSWORD, db=DB, charset=CHARSET
    )
    try:
        with conn.cursor() as cur:
            cur.execute(SQL)
            result = cur.fetchall()
            return result
    except:
        print("Not connected")

    finally:
        cur.close()
        conn.close()


def s3_conn(
    AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_DEFAULT_REGION, BUCKET_NAME, Key
):
    s3_client = boto3.client(
        "s3",
        aws_access_key_id=AWS_ACCESS_KEY_ID,
        aws_secret_access_key=AWS_SECRET_ACCESS_KEY,
        region_name=AWS_DEFAULT_REGION,
    )
    contents = []

    for _ in Key:
        if _.startswith("news"):
            content = (
                s3_client.get_object(Bucket=BUCKET_NAME, Key=_)["Body"].read().decode()
            )
            contents.append(content)
        else:
            contents.append("None")
    return contents


def wc(data, num=100):
    json_data = []
    okt = Okt()
    noun = okt.nouns(data)
    counter = Counter(noun)
    top_num_noun = counter.most_common(num)
    for x, y in top_num_noun:
        json_data.append({"tag": x, "count": y})
    return json_data
