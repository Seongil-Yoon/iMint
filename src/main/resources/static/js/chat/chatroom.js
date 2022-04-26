var stompClient = null;

function connect() {
    var socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect(
        { "user-name": myId },
        function (frame) {
            setConnected(true);
            console.log("Connected: " + frame);
            stompClient.subscribe(
                "/chat/chatroom/" + chatroomId,
                function (chatMessage) {
                    showGreeting(JSON.parse(chatMessage.body));
                }
            );
        },
        function () {}
    );
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send(
        "/chat/send/chatroom/" + chatroomId,
        {},
        JSON.stringify({ message: $("#name").val() })
    );
    $("#name").val("");
}

function showGreeting(chatMessage) {
    $("#greetings").prepend(
        "<tr><td>" +
            chatMessage.senderNick +
            ": " +
            chatMessage.message +
            "</td></tr>"
    );
}

$(function () {
    $("form").on("submit", function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
    connect();
    getChatMessages(chatroomId, 0, 50);
});
