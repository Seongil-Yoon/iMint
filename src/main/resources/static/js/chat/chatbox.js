let stompClient = null;

$(function () {
    if (chatRole == "CHILD" || chatRole == "GUARD") {
        setTimeout(function () {
			$("#chatbox-openbtn").show();
            connectWS(chatId);
        }, 500);
    }

    // 이벤트 등록: 채팅 버튼 누르면 채팅 버튼 숨기고 채팅박스 표시
    $("#chatbox-openbtn").on("click", function () {
        $(this).hide();
        $("#chatbox-main").show();
        // 채팅박스 열 때 채팅방 새로 불러오기
        loadChatrooms();
    });

    // 이벤트 등록: 채팅박스 닫기 버튼 누르면 채팅박스 숨기고 채팅 버튼 표시
    $("#chatbox-close").on("click", function () {
        $("#chatbox-main").hide();
        $("#chatbox-openbtn").show();
    });

    $("#chatbox-view-send textarea")
        .keydown(function (event) {
            if (event.keyCode == 13) {
                if (!event.shiftKey) {
                    $("#chatbox-send-sendbtn").trigger("click");
                }
            }
        })
        .keyup(function (event) {
            if (event.keyCode == 13) {
                if (!event.shiftKey) {
                    $("#chatbox-view-send textarea").val("");
                }
            }
        });
});

function loadChatrooms() {
    // 채팅방 목록 초기화
    $("#chatbox-list-chatrooms").html("");

    // AJAX: 채팅방 목록 요청
    $.ajax({
        url: "/chat/getchatrooms",
        type: "GET",
        data: {
            myId: chatId,
        },
        dataType: "JSON",
        success: function (result) {
            // 채팅방 목록 갱신
            for (let i in result) {
                $("#chatbox-list-chatrooms").append(
                    `<div data-chatroomId="` +
                        result[i].id +
                        `" class="chatbox-chatrooms-chatroom"></div>`
                );
                $("div[data-chatroomId='" + result[i].id + "']")
                    .append(
                        `<img class="chatbox-chatroom-profile" src="` +
                            result[i].opponentThumbnail +
                            `" />`
                    )
                    .append("<p></p>")
                    .append(
                        `<img class="chatbox-chatroom-goods" src="` +
                            result[i].goodsImagesPath +
                            `" />`
                    );
                $("div[data-chatroomId='" + result[i].id + "'] p")
                    .append(
                        `<span class="chatbox-chatroom-nickname">` +
                            result[i].opponentNick +
                            `</span>`
                    )
                    .append(
                        `<span
				class="chatbox-chatroom-lastmessage">` +
                            result[i].message +
                            `</span>`
                    );
            }

            // 이벤트 등록: 채팅방 목록 누르면 채팅 화면 표시
            $(".chatbox-chatrooms-chatroom").each(function () {
                $(this).on("click", function () {
                    $("#chatbox-view").show();
                    $("#chatbox-list").hide();
                    joinChatroom(this);
                });
            });
        },
    });
}

function joinChatroom(chatroom) {
    // 상품 정보 초기화
    $("#chatbox-view-detail").html("");
    // 거래 버튼 초기화
    $("#chatbox-view-trxbtns").html("");
    $("#chatbox-view-trxbtns").hide();
    // 채팅 메세지 초기화
    $("#chatbox-view-chatmessages").html("");

    // 채팅창 제목표시줄 갱신
    $("#chatbox-view-title .title").text(
        $(chatroom).find(".chatbox-chatroom-nickname").text() + "님과의 채팅"
    );

    // 상품 정보 갱신
    $("#chatbox-view-detail")
        .append(
            `<img id="chatbox-detail-goods" src="` +
                $(chatroom).find(".chatbox-chatroom-goods").attr("src") +
                `" />`
        )
        .append("<p></p>");

    // DB에서 채팅 기록 조회
    getChatmessages($(chatroom).attr("data-chatroomId"), 1, 30);

    // STOMP 채팅방 SUBSCRIBE
    let subscription = stompClient.subscribe(
        "/chat/chatroom/" + $(chatroom).attr("data-chatroomId"),
        function (chatmessage) {
            putChatmessage(JSON.parse(chatmessage.body), false);
        }
    );

    // 이벤트 등록: 전송 버튼 누르면 메세지 보내기
    $("#chatbox-send-sendbtn")
        .off("click")
        .on("click", function () {
            stompClient.send(
                "/chat/send/chatroom/" + $(chatroom).attr("data-chatroomId"),
                {},
                JSON.stringify({
                    message: $("#chatbox-view-send textarea").val(),
                })
            );
            $("#chatbox-view-send textarea").val("");
        });

    // 이벤트 등록: 채팅방 닫기 버튼 누르면 채팅방 목록 표시
    $("#chatview-close")
        .off("click")
        .on("click", function () {
            subscription.unsubscribe();
            $("#chatbox-view").hide();
            $("#chatbox-list").show();
            loadChatrooms();
        });
}

function getChatmessages(chatroomId, pageNumber, numberOfItems) {
    // AJAX: 메세지 목록 요청
    $.ajax({
        url: "/chat/getchatmessages",
        type: "GET",
        data: {
            chatroomId: chatroomId,
            pageNumber: pageNumber,
            numberOfItems: numberOfItems,
        },
        dataType: "JSON",
        success: function (result) {
            // 메세지 목록 갱신
            for (let i = 0; i < result.length; i++) {
                putChatmessage(result[i], true);
            }

            // 스크롤 맨 아래로 이동
            $("#chatbox-view-chatmessages").scrollTop(
                $("#chatbox-view-chatmessages").prop("scrollHeight")
            );
        },
    });
}

function connectWS(myId) {
    // 웹소켓 연결
    let socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect(
        { "user-name": myId },
        function (frame) {
            console.log("Connected: " + frame);
        },
        function () {
            // 웹소켓 연결 끊김/오류 발생시 10초마다 재접속 시도
            setTimeout(connectWS, 10000);
        }
    );
}

function putChatmessage(chatmessage, isloading) {
    if (isloading) {
        // DB의 메세지 목록을 불러올 때는 앞쪽으로 추가
        if (chatmessage.senderId != chatId) {
            $("#chatbox-view-chatmessages").prepend(
                `<div data-messageId="` +
                    chatmessage.id +
                    `" class="chatbox-chatmessages-chatmessage left"></div>`
            );
        } else {
            $("#chatbox-view-chatmessages").prepend(
                `<div data-messageId="` +
                    chatmessage.id +
                    `" class="chatbox-chatmessages-chatmessage right"></div>`
            );
        }
    } else {
        // 웹소켓을 통해 메세지를 받았을 때는 뒤쪽으로 추가
        if (chatmessage.senderId != chatId) {
            $("#chatbox-view-chatmessages").append(
                `<div data-messageId="` +
                    chatmessage.id +
                    `" class="chatbox-chatmessages-chatmessage left"></div>`
            );
        } else {
            $("#chatbox-view-chatmessages").append(
                `<div data-messageId="` +
                    chatmessage.id +
                    `" class="chatbox-chatmessages-chatmessage right"></div>`
            );
        }
    }

    // 메세지 내용 갱신
    $(
        ".chatbox-chatmessages-chatmessage[data-messageId='" +
            chatmessage.id +
            "']"
    )
        .append(
            `<div class="chatbox-chatmessage-message">` +
                chatmessage.message +
                `</div>`
        )
        .append(`<div class="chatbox-chatmessage-chatinfo"></div>`)
        .find(".chatbox-chatmessage-chatinfo")
        .append(
            `<div class="chatbox-chatinfo-sendtime">` +
                chatmessage.sendDate.substr(
                    chatmessage.sendDate.indexOf("T") + 1,
                    5
                ) +
                `</div>`
        )
        .append(
            `<div class="chatbox-chatinfo-isread">` +
                (chatmessage.read ? "읽음" : "") +
                `</div>`
        );
}
