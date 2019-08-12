import time
import redis
from flask import Flask, jsonify
from flask_cors import CORS
from oauth2client.service_account import ServiceAccountCredentials

# CORS를 허용한 플라스크 웹서버
app = Flask(__name__)
CORS(app)
# Redis DB
cache = redis.Redis(host="redis", port=6379)

# GAPI Consts
SCOPE = 'https://www.googleapis.com/auth/analytics.readonly'
KEY_FILEPATH = 'client_secret_527574403487-5rjveiqpfsptnn22qge8ohmkl3p3obmi.apps.googleusercontent.com.json'

# API 서버 상태
@app.route("/")
def index():
    return "<h1>GAPI 토큰 발급 서버</h1><h2>해당 토큰이 만료되었을 경우 새로운 토큰을 발급합니다.</h2>"

# Key_FILEPATH에 있는 jwt 로 엑세스 토큰을 발급받아 보내준다.
@app.route("/gapi/token/access")
def getAccessToken():
    accessToken = ServiceAccountCredentials.from_json_keyfile_name(
    KEY_FILEPATH, SCOPE).get_access_token().access_token
    return jsonify(accessToken)

if(__name__ == "__main__"):
    app.run(host="0.0.0.0", debug=True)