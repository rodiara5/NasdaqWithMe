from flask import Flask, jsonify
import wordcloud_code as wordcloud_code
from flask_cors import CORS
 
app = Flask(__name__)
CORS(app)
@app.route("/tospring", methods=["GET","POST"])
def spring():
    
    result = wordcloud_code.wc()
    print(result)
    
    return jsonify({"result":result})

if __name__ == '__main__':
    app.run(debug=True,host="127.0.0.1",port=5000)

# Flask 디자인패턴 적용하기
# __init__.py, model, services, daos, controllers