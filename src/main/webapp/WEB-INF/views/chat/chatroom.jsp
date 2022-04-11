<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iMint :: 채팅방</title>
<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />
	<aside></aside>
	
	<div class="container">
		<div class="title_text">
			<h2>${goodsTitle}</h2>
		</div>
		<div class="row">
			<%--chatMessage(이전 기록)와 member가 실시간 입력하는 메시지 출력 --%>
			<div id="content">
				<c:forEach var="chatRoom" items="${chatMessageContent}">
					<p>
						<span id="chatRoomSenderName">${chatRoom.mbNick}</span><br>
						<span id="chatRoomContent">${chatRoom.content}</span><br> <span
							id="chatRoomSendTime">${chatRoom.sendTime}</span><br>
					</p>
				</c:forEach>
			</div>
			<%--메시지 입력창과 보내기 버튼 --%>
			<div class="row_3">
				<div class="input_group" id="sendMessage">
					<input type="text" placeholder="Message" id="message"
						class="form_control" />
					<div class="input_group_append">
						<button id="send" class="btn btn-primary" onclick="send()">보내기</button>
						<input type="hidden" value="${login.getNickName()}" id="buyerName" />
						<input type="hidden" value="${login.getEmail()}" id="buyerId" />
						<input type="hidden" value="${chatRoomInfo.pr_id}" id="pr_id" />
						<input type="hidden" value="${chatRoomInfo.sellerId}"
							id="sellerId" /> <input type="hidden"
							value="${chatRoomInfo.sellerName}" id="sellerName" /> <input
							type="hidden" value="${chatRoomInfo.id}" id="id" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<%-- STOMP와 sockjs webjars import --%>
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
        
        <%-- invoke when DOM(Documents Object Model; HTML(<head>, <body>...etc) is ready --%>
        $(document).ready(connect());
        
        function connect() {
            <%-- map URL using SockJS--%>
            var socket = new SockJS('/broadcast');
            var url = '/user/' + id + '/queue/messages';
            <%-- webSocket 대신 SockJS을 사용하므로 Stomp.client()가 아닌 Stomp.over()를 사용함--%>
            stompClient = Stomp.over(socket);
            
            <%-- connect(header, connectCallback(==연결에 성공하면 실행되는 메서드))--%>
            stompClient.connect({}, function() {
                <%-- url: 채팅방 참여자들에게 공유되는 경로--%>
                <%-- callback(function()): 클라이언트가 서버(Controller broker)로부터 메시지를 수신했을 때(== STOMP send()가 실행되었을 때) 실행 --%>
                stompClient.subscribe(url, function(output) {
                    <%-- JSP <body>에 append할 메시지 contents--%>
                    showBroadcastMessage(createTextNode(JSON.parse(output.body)));
                });
                }, 
                    <%-- connect() 에러 발생 시 실행--%>
                        function (err) {
                            alert('error' + err);
            });
 
        };
        
        <%-- WebSocket broker 경로로 JSON 타입 메시지데이터를 전송함 --%>
        function sendBroadcast(json) {
            
            stompClient.send("/app/broadcast", {}, JSON.stringify(json));
        }
        
        <%-- 보내기 버튼 클릭시 실행되는 메서드--%>
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
        
        <%-- 메시지 입력 창에서 Enter키가 보내기와 연동되도록 설정 --%>
        var inputMessage = document.getElementById('message'); 
        inputMessage.addEventListener('keyup', function enterSend(event) {
            
            if (event.keyCode === null) {
                event.preventDefault();
            }
            
            if (event.keyCode === 13) {
                send();
            }
        });
        
        <%-- 입력한 메시지를 HTML 형태로 가공 --%>
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
        
        <%-- HTML 형태의 메시지를 화면에 출력해줌 --%>
        <%-- 해당되는 id 태그의 모든 하위 내용들을 message가 추가된 내용으로 갱신해줌 --%>
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









	<jsp:include page="../include/footer.jsp" flush="false" />
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/webjars/stomp-websocket/2.3.3-1/stomp.js"
		type="text/javascript"></script>
	<script src="/webjars/sockjs-client/1.1.2/sockjs.js"
		type="text/javascript"></script>
	<script src="/static/js/chat.js"></script>
</body>
</html>