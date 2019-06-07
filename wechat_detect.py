import itchat
from itchat.content import *
import http.client
import json


def send_http(title, author, description, link):
    ip = "47.103.0.246"
    port = "12580"
    request_url = "http://"+ip+":"+port+"/links/upload"
    test_data =json.dumps({'title':title, 'author':author, 'description':description, 'link':link})
    conn = http.client.HTTPConnection(ip, port)
    header = {"Content-type": "application/json", "Accept": "*/*"}
    conn.request(method="POST", url=request_url, headers=header, body=test_data)
    response = conn.getresponse()
    print(response.status)


@itchat.msg_register([TEXT], isGroupChat=True)
def monitor_chatroom(msg):
    detect_msg = msg['Content']
    if detect_msg.startswith('-/'):
        contents = detect_msg.split('-/')
        if len(contents) == 4:
            author = msg['ActualNickName']
            send_http(contents[1], author, contents[2], contents[3])


if __name__ == '__main__':
    itchat.auto_login(enableCmdQR=2)
    itchat.run(debug=True)
