from flask import Flask, jsonify
from flask_cors import CORS

from wordcloud_code import *

app = Flask(__name__)
CORS(app)


@app.route("/tospring", methods=["GET", "POST"])
def spring():

    columns = ["dailydate", "ticker", "name", "news_summary"]
    SQL = f"select {', '.join(columns)} from daily_update"
    result = mysql_conn(HOST, USER, PASSWORD, DB, SQL=SQL)
    df = pd.DataFrame(result, columns=columns)

    contents = s3_conn(
        AWS_ACCESS_KEY_ID,
        AWS_SECRET_ACCESS_KEY,
        AWS_DEFAULT_REGION,
        BUCKET_NAME,
        df["news_summary"],
    )

    df["contents"] = contents
    json_data = wc(df["contents"].sum(), 30)
    result = json_data

    return jsonify({"result": result})


if __name__ == "__main__":
    app.run(debug=True, host="127.0.0.1", port=5000)

# Flask 디자인패턴 적용하기
# __init__.py, model, services, daos, controllers
