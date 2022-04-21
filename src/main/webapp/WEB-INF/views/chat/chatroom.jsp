<!-- <?xml version="1.0" encoding="UTF-8"?> -->
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at
      http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta charset="UTF-8">
<title>iMint :: 채팅방</title>
<jsp:include page="../libs/libsStyles.jsp" flush="false" />
<style>
* {
	margin: 0;
	padding: 0;
}

.container {
	width: 500px;
	margin: 0 auto;
	padding: 25px;
}

/* .container h1 {
	text-align: left;
	padding: 5px 5px 5px 15px;
	color: #3387a8;
	margin-bottom: 20px;
	font-weight: bolder;
} */
.container h4 {
	text-align: left;
	padding: 5px 5px 5px 15px;
	color: #3387a8;
	border-left: 3px solid #3387a8;
	margin-bottom: 20px;
	font-weight: bolder;
}

.chatting-container {
	padding: 1.5rem;
	border: 1px;
	border-radius: 1rem;
	/* border-style: solid;
	border-color: rgb(155, 155, 155); */
	width: 500px;
	height: 500px;
	overflow: auto;
	background-color: #CDF0EA;
	color: #3387a8;
}

input {
	width: 380px;
	height: 25px;
}

#inputTable {
	width: 600px;
}

#caution {
	color: #3387a8;
	text-align: left;
	font-weight: bold;
}

/* 흥정가능여부div */
#goods-suggestible {
	padding: 0.8rem;
	border: 1px;
	background-color: white;
	border-radius: 1rem;
	border-style: solid;
	text-align: center;
	width: 400px;
	margin: 0 28 auto;
	border: 2px solid #3387a8;
	position: absolute;
}

/* 채팅방콘테이너 */
.chatting-container {
	color: #3387a8;
	text-align: left;
}

#yourMsg-beforeReserve, #yourMsg-afterReserve {
	display: none;
}
</style>
</head>

<script type="text/javascript">
	var ws;

	function wsOpen() {
		ws = new WebSocket("ws://" + location.host + "/chat");
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 초기화 세팅하기
		}

		ws.onmessage = function(data) {
			var msg = data.data;
			var chattingInsert = document.getElementById('chatting-insert');
			if (msg != null && msg.trim() != '') {
				$("#chatting-insert").append("<p>" + msg + "</p>");
			}
		}

		document.addEventListener("keypress", function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
	}
	
	function chatName() {
		var userName = $("#userName").val();
		if (userName == null || userName.trim() == "") {
			alert("사용자 이름을 입력해주세요.");
			$("#userName").focus();
		} else {
			wsOpen();
			$("#yourName").hide();
			$("#yourMsg-beforeReserve").show();
		}
	}
	
 	function clickReserve(){
		$("#yourMsg-beforeReserve").hide();
		$("#yourMsg-afterReserve").show();
		
	}
	
	function clickReserveCancel(){
		$("#yourMsg-afterReserve").hide();
		$("#yourMsg-beforeReserve").show();
		
	}

	function send() {
		var uN = $("#userName").val();
		var msg = $("#chatting").val();
		ws.send(uN + " : " + msg);
		$('#chatting').val("")
		var chat = document.querySelector('#chatting-container');
    	chat.scrollTop = chat.scrollHeight;
		
	}
</script>
<body>
	<div id="container" class="container">
		<!-- <h1>채팅으로 거래하기</h1> -->
		<h4>A님과의 대화입니다.</h4>
		<img width=30 height=30 src="/static/images/caution.png"> <span
			id="caution">개인정보(주소, 학교정보, 전화번호)에 관한 질문과 답변을 금지합니다.</span><br>
		<br>
		<div id="chatting-container" class="chatting-container">
			<div id="goods-suggestible">가격흥정여부표시</div>
			<br> <br> <br>
			<div id="chatting-insert" class="chatting-insert"></div>
		</div>
		<br>
		<div id="yourName">
			<table class="inputTable">
				<tr>
					<td>사용자</td>
					<td><input type="text" name="userName" id="userName"></td>
					<td><button onclick="chatName()" id="startBtn"
							class="btn btn-primary">이름 등록</button></td>
				</tr>
			</table>
		</div>
		<div id="yourMsg-beforeReserve">
			<table class="inputTable">
				<tr>
					<!-- <th>예약하기 누르기전</th> -->
					<td><input id="chatting" placeholder="메세지를 입력하세요.">
					<button onclick="send()" id="sendBtn" class="btn btn-primary">전송</button></td>
				</tr>
				<tr>
					<td>
						<button id="reserveBtn" class="btn btn-primary" onclick="clickReserve()">예약하기</button>
						<button id="completeBtn" class="btn btn-primary">거래완료</button>
						<button id="goBackBtn" class="btn btn-primary">뒤로가기</button>
					</td>
				</tr>
			</table>
		</div>
		<div id="yourMsg-afterReserve">
			<table class="inputTable">
				<tr>
					<!-- <th>예약하기 눌렀을때</th> -->
					<td><input id="chatting" placeholder="메시지를 입력하세요.">
					<button onclick="send()" id="sendBtn" class="btn btn-primary">전송</button></td>
				</tr>
				<tr>
					<td>
						<button id="reserveCancelBtn" class="btn btn-primary" onclick="clickReserveCancel()">예약취소</button>
						<button id="completeBtn" class="btn btn-primary">거래완료</button>
						<button id="goBackBtn" class="btn btn-primary">뒤로가기</button>
					</td>
				</tr>
			</table>
		</div>
	</div>


	<jsp:include page="../include/footer.jsp" flush="false" />
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
<!-- 	<script src="/webjars/stomp-websocket/2.3.3-1/stomp.js"
		type="text/javascript"></script>
	<script src="/webjars/sockjs-client/1.1.2/sockjs.js"
		type="text/javascript"></script> -->
	<!-- <script src="/static/js/chat.js"></script> -->


</body>
</html>



<!-- <style type="text/css">
input#chat {
	width: 410px
}

#console-container {
	width: 400px;
}

#console {
	border: 1px solid #CCCCCC;
	border-right-color: #999999;
	border-bottom-color: #999999;
	height: 170px;
	overflow-y: scroll;
	padding: 5px;
	width: 100%;
}

#console p {
	padding: 0;
	margin: 0;
}
</style>
<script type="application/javascript">
        "use strict";

        var Chat = {}; // 챗 객채 생성

        Chat.socket = null;

        Chat.connect = (function(host) {
            if ('WebSocket' in window) { // 윈도우 객체에 웹소캣이 있는지?
                Chat.socket = new WebSocket(host); // 있으면 새로운 웹소캣 객체 생성 - 서버 소켓이랑 1:1로 연결되는 소켓 객체
            } else if ('MozWebSocket' in window)  { // 아니면 모즈웹소캣이 있는지? - 브라우저별 다름
                Chat.socket = new MozWebSocket(host); 
            } else {
                Console.log('에러: 이 브라우저에서 채팅이 지원되지 않습니다.'); //Error: WebSocket is not supported by this browser.
                return;
            }

            Chat.socket.onopen = function () { // 서버쪽웹소켓은 항상열려있음 - 클라이언트가웹소캣요청했을때 - 서버쪽에서 요청확인받고 응답으로 소켓연결해주면 실행
                Console.log('채팅방이 생성되었습니다.'); // 웹소캣 통신 가능 // Info: WebSocket connection opened.
                document.getElementById('chat').onkeydown = function(event) {
                    if (event.keyCode == 13) { // 13 = 엔터
                        Chat.sendMessage();
                    }
                };
            };

            Chat.socket.onclose = function () {
                document.getElementById('chat').onkeydown = null;
                Console.log('채팅방 연결이 끊어졌습니다.'); // Info: WebSocket closed.
            };

            Chat.socket.onmessage = function (message) { // 메시지가 도착하면
            	console.dir(message)
                Console.log(message.data);
            };
        });

        Chat.initialize = function() {
            if (window.location.protocol == 'http:') {
                Chat.connect('ws://' + window.location.host + '/websocket/chat'); // 이거랑 chatannotation's @serverEndPoint 동일해야함
            } else {
                Chat.connect('wss://' + window.location.host + '/websocket/chat');
            }
        };

        Chat.sendMessage = (function() {
            var message = document.getElementById('chat').value;
            if (message != '') {
                Chat.socket.send(message);
                document.getElementById('chat').value = '';
            }
        });

        // 채팅창 뿌려주기
        var Console = {};

        Console.log = (function(message) { // console.log에서 메세지를 받아오면
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.innerHTML = message;
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        });
        

        Chat.initialize();


        document.addEventListener("DOMContentLoaded", function() {
            // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
            var noscripts = document.getElementsByClassName("noscript");
            for (var i = 0; i < noscripts.length; i++) {
                noscripts[i].parentNode.removeChild(noscripts[i]);
            }
        }, false);

   </script>
</head>
<body>
	<div class="noscript">
		<h2 style="color: #ff0000">Seems your browser doesn't support
			JavaScript! Websockets rely on JavaScript being enabled. Please
			enable JavaScript and reload this page!</h2>
	</div>
	<div>
		<div id="console-container">
			<div id="console"></div>
		</div>
		<p>
			<input type="text" placeholder="메세지 작성후 Enter를 누르세요." id="chat" />
		</p>
	</div>
	 -->




<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>iMint :: 채팅방</title>
<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>
<body>
	<div class="container">
		<div class="title_text">
			<h2>${goods_title}</h2>
		</div>
		<div class="row">
			chatHistory와 member가 실시간 입력하는 메시지 출력
			<div id="content">
				<c:forEach var="chatRoom" items="${chatHistory}">
					<p>
						<span id="chatRoomSenderName">${chatRoom.senderName}</span><br>
						<span id="chatRoomContent">${chatRoom.content}</span> <span
							id="chatRoomSendTime">${chatRoom.sendTime}</span><br>
					</p>
				</c:forEach>
			</div>
			메시지 입력창과 보내기 버튼
			<div class="row_3">
				<div class="input_group" id="sendMessage">
					<input type="text" placeholder="메세지를 입력하세요" id="message"
						class="form_control" />
					<div class="input_group_append">
						<button id="send" class="btn btn-primary" onclick="send()">전송</button>
						<input type="hidden" value="${member.getMbNick()}" id="buyerName" />
						<input type="hidden" value="${member.getMbId()}" id="buyerId" /> <input
							type="hidden" value="${chatRoomInfo.pr_id}" id="pr_id" /> <input
							type="hidden" value="${chatRoomInfo.sellerId}" id="sellerId" /> <input
							type="hidden" value="${chatRoomInfo.sellerName}" id="sellerName" />
						<input type="hidden" value="${chatRoomInfo.id}" id="id" />
					</div>
				</div>
			</div>
		</div>
	</div>

	STOMP와 sockjs webjars import
	<script src="/webjars/stomp-websocket/2.3.3-1/stomp.js"
		type="text/javascript"></script>
	<script src="/webjars/sockjs-client/1.1.2/sockjs.js"
		type="text/javascript"></script>

	<script type="text/javascript">
    
        var stompClient = null;
        var buyerName = $('#buyerName').val();
        var buyerId = $('#buyerId').val();
        var pr_id = $('#pr_id').val();
        var sellerName = $('#sellerName').val();
        var sellerId = $('#sellerId').val();    
        var senderName = $('#buyerName').val();
        var id = $('#id').val();
        
        invoke when DOM(Documents Object Model; HTML(<head>, <body>...etc) is ready
        $(document).ready(connect());
        
        function connect() {
            map URL using SockJS
            var socket = new SockJS('/broadcast');
            var url = '/user/' + id + '/queue/messages';
            webSocket 대신 SockJS을 사용하므로 Stomp.client()가 아닌 Stomp.over()를 사용함
            stompClient = Stomp.over(socket);
            
            connect(header, connectCallback(==연결에 성공하면 실행되는 메서드))
            stompClient.connect({}, function() {
                url: 채팅방 참여자들에게 공유되는 경로
                callback(function()): 클라이언트가 서버(Controller broker)로부터 메시지를 수신했을 때(== STOMP send()가 실행되었을 때) 실행
                stompClient.subscribe(url, function(output) {
                    JSP <body>에 append할 메시지 contents
                    showBroadcastMessage(createTextNode(JSON.parse(output.body)));
                });
                }, 
                    connect() 에러 발생 시 실행
                        function (err) {
                            alert('error' + err);
            });
 
        };
        
        WebSocket broker 경로로 JSON 타입 메시지데이터를 전송함
        function sendBroadcast(json) {
            
            stompClient.send("/app/broadcast", {}, JSON.stringify(json));
        }
        
        보내기 버튼 클릭시 실행되는 메서드
        function send() {
            var content = $('#message').val();
            sendBroadcast({
                'id': id,
                'buyerName': buyerName, 'content': content,
                'sellerName': sellerName,
                'buyerId': buyerId, 'sellerId': sellerId,
                'pr_id': pr_id,
                'senderName': senderName
                });
            $('#message').val("");
        }
        
        메시지 입력 창에서 Enter키가 보내기와 연동되도록 설정
        var inputMessage = document.getElementById('message'); 
        inputMessage.addEventListener('keyup', function enterSend(event) {
            
            if (event.keyCode === null) {
                event.preventDefault();
            }
            
            if (event.keyCode === 13) {
                send();
            }
        });
        
        입력한 메시지를 HTML 형태로 가공
        function createTextNode(messageObj) {
            console.log("createTextNode");
            console.log("messageObj: " + messageObj.content);
            return '<p><div class="row alert alert-info"><div class="col_8">' +
            messageObj.senderName +
            '</div><div class="col_4 text-right">' +
            messageObj.content+
            '</div><div>[' +
            messageObj.sendTime +
            ']</div></p>';
        }
        
        HTML 형태의 메시지를 화면에 출력해줌
        해당되는 id 태그의 모든 하위 내용들을 message가 추가된 내용으로 갱신해줌
        function showBroadcastMessage(message) {
            $("#content").html($("#content").html() + message);
        }
        
 
    
    </script>

	<script>

/* View - Javascript에서 STOMP를 설정하고 메시지를 전송하는 코드부분 입니다. 
 * 여기서 볼 것은, 7행에 들어가는 subscribe url이 '/user...'로 시작되는 것과 
 * 21행의 send는 '/app...'으로 시작한다는 것입니다. 
 우리는 send를 통해 메시지를 전송했을 때 broker를 거쳐 
 유저에게 전달이 된다는 것을 알 수 있습니다. 
 또한 메시지를 다이렉트로 전달 받는 경로는 subscribe로 설정한다는 
 것까지알게되었습니다. */

</script>

	
</body>
</html> --%>