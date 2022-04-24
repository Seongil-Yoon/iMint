var stompClient = null;

function getChatMessages(chatroomId, pageNumber, numberOfItems) {
	$.ajax({
		url: "/chat/get",
		type: "GET",
		data: {
			"chatroomId": chatroomId,
			"pageNumber": pageNumber,
			"numberOfItems": numberOfItems
		},
		dataType: "JSON",
		success: function(result) {
			for (let i = 0; i < result.length; i++) {
				$("#greetings").append("<tr><td>" + result[i].senderNick + ": " + result[i].message + "</td></tr>");
			}
		}
	});
}

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
}

function connect() {
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({ "user-name": myId }, function(frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/chat/chatroom/' + chatroomId, function(chatMessage) {
			showGreeting(JSON.parse(chatMessage.body));
		});
	}, function() {

	});
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/chat/send/chatroom/" + chatroomId, {}, JSON.stringify({ 'message': $("#name").val() }));
	$("#name").val("")
}

function showGreeting(chatMessage) {
	$("#greetings").prepend("<tr><td>" + chatMessage.senderNick + ": " + chatMessage.message + "</td></tr>");
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$("#connect").click(function() { connect(); });
	$("#disconnect").click(function() { disconnect(); });
	$("#send").click(function() { sendName(); });
	connect();
	getChatMessages(chatroomId, 0, 50);
});