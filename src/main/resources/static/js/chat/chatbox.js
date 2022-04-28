let stompClient = null;

$(function () {
    // 인증된 회원이면 웹소켓 접속
    if (chatboxMyRole == "CHILD" || chatboxMyRole == "GUARD") {
        setTimeout(function () {
            $("#chatbox-openbtn").show();
        }, 100);
    }

    // 보호자 회원이면 아이 선택 선택상자 추가
    if (chatboxMyRole == "GUARD") {
        addChildSelect();
    } else {
        connectWS(chatboxMyId);
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

    // 이벤트 등록: 메세지 입력창 엔터로 보내기(SHIFT+엔터는 줄바꿈)
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

    // 이벤트 등록: 새로운 메세지 버튼 클릭하면 맨 아래로 이동
    $("#chatbox-view-alertnew").on("click", function () {
        $("#chatbox-view-chatmessages").scrollTop(
            $("#chatbox-view-chatmessages").prop("scrollHeight")
        );
    });

    // 이벤트 등록: 스크롤이 맨 아래로 가면 새로운 메세지 알림 해제
    $("#chatbox-view-chatmessages").scroll(function () {
        if (
            $("#chatbox-view-chatmessages").scrollTop() +
                $("#chatbox-view-chatmessages").innerHeight() >=
            $("#chatbox-view-chatmessages").prop("scrollHeight")
        ) {
            $("#chatbox-view-alertnew").hide();
        }
    });
});

// 함수: 웹소켓에 연결
function connectWS(chatboxMyId) {
    let socket = new SockJS("/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect(
        { "user-name": chatboxMyId },
        function (frame) {
            console.log("Connected: " + frame);
        },
        function () {
            // 웹소켓 연결 끊김/오류 발생시 10초마다 재접속 시도
            setTimeout(connectWS, 10000);
        }
    );
}

// 함수: 웹소켓 연결 해제
function disconnectWS() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

// 함수: 보호자 회원일 때 내 아이 선택 상자 추가
function addChildSelect() {
    $.ajax({
        url: "/chat/getmychildren",
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            $("#chatbox-list-title .chatbox-title-text").after(
                `<select id="chatbox-title-select" class="form-select"></select>`
            );
            if (result.length > 0) {
                $("#chatbox-title-select")
                    .append(`<option selected>내 아이 선택</option>`)
                    .on("change", function () {
                        chatboxMyId = $(this).val();
                        loadChatrooms();
                        disconnectWS();
                        connectWS(chatboxMyId);
                    });

                for (let i in result) {
                    $("#chatbox-title-select").append(
                        `<option value="` +
                            result[i].childId +
                            `">` +
                            result[i].childNick +
                            `</option>`
                    );
                }
            } else {
                $("#chatbox-title-select").attr("disabled", true);
                $("#chatbox-title-select").append(
                    `<option selected>등록된 아이 없음</option>`
                );
            }
        },
    });
}

// 함수: 채팅방 목록 표시
function loadChatrooms() {
    // 채팅방 목록 초기화
    $("#chatbox-list-chatrooms").html("");

    // AJAX: 채팅방 목록 요청
    $.ajax({
        url: "/chat/getchatrooms",
        type: "GET",
        data: {
            myId: chatboxMyId,
        },
        dataType: "JSON",
        success: function (result) {
            // 채팅방 목록 갱신
            for (let i in result) {
                $("#chatbox-list-chatrooms").append(
                    `<div class="chatbox-chatrooms-chatroom" data-chatroomId="` +
                        result[i].id +
                        `" data-goodsId="` +
                        result[i].goodsId +
                        `" data-goodsTitle="` +
                        result[i].goodsTitle +
                        `" data-goodsPrice="` +
                        result[i].goodsPrice +
                        `" data-goodsSuggestible="` +
                        result[i].goodsSuggestible +
                        `" data-opponentId="` +
                        result[i].opponentId +
                        `"></div>`
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
                    )
                    .find("p")
                    .append(
                        `<span class="chatbox-chatroom-nickname">` +
                            result[i].opponentNick +
                            `</span>`
                    )
                    .append(
                        `<span class="chatbox-chatroom-lastmessage">` +
                            (result[i].message === null
                                ? ""
                                : result[i].message) +
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

// 함수: 거래 관련 버튼 영역 표시/숨기기
function setExtraUIs(trxbtns) {
    let defaultHeight =
        parseInt($(":root").css("--total-height")) -
        parseInt($(":root").css("--title-height")) -
        parseInt($(":root").css("--chatroom-height")) * 2;

    if (chatboxMyRole == "GUARD") {
        $("#chatbox-view-send").hide();
        $("#chatbox-view-trxbtns").hide();
        $("#chatbox-view-chatmessages").height(defaultHeight + 81);
    } else {
        $("#chatbox-view-trxbtns").html("");
        if (trxbtns) {
            $("#chatbox-view-chatmessages").height(defaultHeight - 30);
            $("#chatbox-view-trxbtns").show();
        } else {
            $("#chatbox-view-chatmessages").height(defaultHeight);
            $("#chatbox-view-trxbtns").hide();
        }
    }
}

// 함수: 보호자 회원일 때 메세지 입력 상자 숨기기

// 함수: 거래 상태 조회
function getTrxStatus(opponentId, goodsId, chatroomId) {
    $.ajax({
        url: "/transaction/trx/check",
        type: "GET",
        data: {
            opponentId: opponentId,
            goodsId: goodsId,
        },
        dataType: "JSON",
        success: function (trx) {
            if (trx.check.includes("wait")) {
                // 판매중
                if (trx.check.includes("seller")) {
                    // 판매중 - 판매자 시점
                    $("#chatbox-detail-goodsstatus")
                        .text("판매중")
                        .css("border-color", "blue")
                        .css("color", "blue");

                    setExtraUIs(true);
                    $("#chatbox-view-trxbtns")
                        .append(
                            `<div id="chatbox-trxbtns-makeresrv" class="chatbox-trxbtn short">예약하기</div>`
                        )
                        .append(
                            `<div id="chatbox-trxbtns-comptrx" class="chatbox-trxbtn short">판매완료</div>`
                        );
                } else {
                    // 판매중 - 기타 시점
                    $("#chatbox-detail-goodsstatus")
                        .text("구매가능")
                        .css("border-color", "green")
                        .css("color", "green");

                    setExtraUIs(false);
                }
            } else if (trx.check.includes("resrv")) {
                if (
                    trx.check.includes("match") ||
                    trx.check.includes("buyer")
                ) {
                    // 예약완료
                    $("#chatbox-detail-goodsstatus")
                        .text("예약완료")
                        .css("border-color", "#FFBB00")
                        .css("color", "#FFBB00");
                    if (trx.check.includes("seller")) {
                        // 예약완료 - 판매자 시점
                        setExtraUIs(true);
                        $("#chatbox-view-trxbtns")
                            .append(
                                `<div id="chatbox-trxbtns-cancelresrv" class="chatbox-trxbtn short">예약취소</div>`
                            )
                            .append(
                                `<div id="chatbox-trxbtns-comptrx" class="chatbox-trxbtn short">판매완료</div>`
                            );
                    } else {
                        // 예약완료 - 예약자 시점
                        setExtraUIs(false);
                    }
                } else {
                    // 예약중
                    $("#chatbox-detail-goodsstatus")
                        .text("예약중")
                        .css("border-color", "#9b9b9b")
                        .css("color", "#9b9b9b");

                    setExtraUIs(true);
                    if (trx.check.includes("seller")) {
                        // 예약중 - 판매자 시점
                        $("#chatbox-view-trxbtns").text(
                            "다른 회원과 판매 예약중인 상품입니다."
                        );
                    } else {
                        // 예약중 - 기타 시점
                        $("#chatbox-view-trxbtns").text(
                            "다른 회원이 구매 예약중인 상품입니다."
                        );
                    }
                }
            } else if (trx.check.includes("comp!")) {
                // 판매완료(구매자 지정)
                if (trx.check.includes("seller")) {
                    $("#chatbox-detail-goodsstatus")
                        .text("판매완료")
                        .css("border-color", "blue")
                        .css("color", "blue");

                    if (trx.check.includes("match")) {
                        // 판매완료 - 판매자:구매자 시점
                        setExtraUIs(true);
                        $("#chatbox-view-trxbtns").append(
                            `<div id="chatbox-trxbtns-ratetrx" class="chatbox-trxbtn long">거래 평가하기</div>`
                        );
                    } else {
                        // 판매완료 - 판매자:기타 시점
                        setExtraUIs(true);
                        $("#chatbox-view-trxbtns").text(
                            "다른 회원에게 판매한 상품입니다."
                        );
                    }
                } else {
                    // 구매완료 - 구매자 시점
                    $("#chatbox-detail-goodsstatus")
                        .text("구매완료")
                        .css("border-color", "#DD0000")
                        .css("color", "#DD0000");

                    setExtraUIs(true);
                    $("#chatbox-view-trxbtns").append(
                        `<div id="chatbox-trxbtns-ratetrx" class="chatbox-trxbtn long">거래 평가하기</div>`
                    );
                }
            } else if (trx.check.includes("comp?")) {
                // 판매완료(구매자 미지정) - 판매자 시점
                $("#chatbox-detail-goodsstatus")
                    .text("판매완료")
                    .css("border-color", "blue")
                    .css("color", "blue");

                setExtraUIs(true);
                $("#chatbox-view-trxbtns").append(
                    `<div id="chatbox-trxbtns-addbuyer" class="chatbox-trxbtn long">구매자 선택</div>`
                );
            } else if (trx.check.includes("comp")) {
                // 구매불가 - 기타 시점
                $("#chatbox-detail-goodsstatus")
                    .text("구매불가")
                    .css("border-color", "#9b9b9b")
                    .css("color", "#9b9b9b");

                setExtraUIs(true);
                $("#chatbox-view-trxbtns").text("이미 판매된 상품입니다.");
            } else {
                // 오류발생
                $("#chatbox-detail-goodsstatus")
                    .text("오류발생")
                    .css("border-color", "#9b9b9b")
                    .css("color", "#9b9b9b");

                setExtraUIs(true);
                $("#chatbox-view-trxbtns").text(
                    "판매글이 삭제되었거나 잘못된 채팅방입니다."
                );

            // 이벤트 등록: 예약하기
            $("#chatbox-trxbtns-makeresrv")
                .off("click")
                .on("click", function () {
                    $.ajax({
                        url: "/transaction/resrv/make",
                        type: "POST",
                        data: {
                            goodsId: goodsId,
                            chatroomId: chatroomId,
                        },
                        dataType: "JSON",
                        success: function (r) {
                            getTrxStatus(opponentId, goodsId, chatroomId);
                        },
                    });
                });

            // 이벤트 등록: 예약취소
            $("#chatbox-trxbtns-cancelresrv")
                .off("click")
                .on("click", function () {
                    $.ajax({
                        url: "/transaction/resrv/cancel",
                        type: "POST",
                        data: {
                            goodsId: goodsId,
                            chatroomId: chatroomId,
                        },
                        dataType: "JSON",
                        success: function (r) {
                            getTrxStatus(opponentId, goodsId, chatroomId);
                        },
                    });
                });

            // 이벤트 등록: 판매완료
            $("#chatbox-trxbtns-comptrx")
                .off("click")
                .on("click", function () {
                    let popup = window.open(
                        "/transaction/trx?goodsId=" + goodsId,
                        "_blank",
                        "top=500,left=500,width=500px,height=500px"
                    );
                    $(popup).on("beforeunload", function () {
                        getTrxStatus(opponentId, goodsId, chatroomId);
                    });
                });

            // 이벤트 등록: 구매자 지정
            $("#chatbox-trxbtns-addbuyer")
                .off("click")
                .on("click", function () {
                    getTrxStatus(opponentId, goodsId, chatroomId);
                });

            // 이벤트 등록: 평가하기
            $("#chatbox-trxbtns-ratetrx")
                .off("click")
                .on("click", function () {
                    getTrxStatus(opponentId, goodsId, chatroomId);
                });
        },
    });
}

// 함수: 채팅방(채팅화면) 표시
function joinChatroom(chatroom) {
    let currentChatroomId = $(chatroom).attr("data-chatroomId");
    let currentGoodsId = $(chatroom).attr("data-goodsId");
    let currentOpponentId = $(chatroom).attr("data-opponentId");

    // 거래 버튼 초기화
    setExtraUIs(false);
    // 채팅 메세지 초기화
    $("#chatbox-view-chatmessages").html("");

    // 채팅창 제목표시줄 갱신
    $("#chatbox-view-title .chatbox-title-text").text(
        $(chatroom).find(".chatbox-chatroom-nickname").text() + "님과의 채팅"
    );
    // 상품 정보 갱신
    $("#chatbox-detail-goods").attr(
        "src",
        $(chatroom).find(".chatbox-chatroom-goods").attr("src")
    );
    $("#chatbox-detail-goodstitle").text($(chatroom).attr("data-goodsTitle"));
    $("#chatbox-detail-goodsprice").text(
        $(chatroom)
            .attr("data-goodsPrice")
            .replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원"
    );
    if ($(chatroom).attr("data-goodsSuggestible") === "true") {
        $("#chatbox-detail-suggestible")
            .text("가격제안가능")
            .css("border-color", "black")
            .css("color", "black");
    } else {
        $("#chatbox-detail-suggestible")
            .text("가격제안불가")
            .css("border-color", "#9b9b9b")
            .css("color", "#9b9b9b");
    }

    // 거래 정보 갱신
    getTrxStatus(currentOpponentId, currentGoodsId, currentChatroomId);

    // DB에서 채팅 기록 조회
    getChatmessages(currentChatroomId, 1, 30);

    // STOMP 채팅방 SUBSCRIBE
    let subscription = stompClient.subscribe(
        "/chat/chatroom/" + currentChatroomId,
        function (chatmessage) {
            putChatmessage(JSON.parse(chatmessage.body), false);
            $("#chatbox-view-alertnew").show();
        }
    );

    // 이벤트 등록: 전송 버튼 누르면 메세지 보내기
    $("#chatbox-send-sendbtn")
        .off("click")
        .on("click", function () {
            stompClient.send(
                "/chat/send/chatroom/" + currentChatroomId,
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

// 함수: 채팅방 목록이 표시된 상태로 만들기(접혀있으면 펼치고 채팅방이면 목록으로 돌아가기)
function directOpenChatroom() {
    if ($("#chatbox-main").css("display") == "none") {
        $("#chatbox-openbtn").trigger("click");
    } else if ($("#chatbox-view").css("display") != "none") {
        $("#chatview-close").trigger("click");
    }
}

// 함수: 채팅방으로 즉시 입장
function directJoinChatroom(chatroomId) {
    directOpenChatroom();
    setTimeout(function () {
        $(`div[data-chatroomId="` + chatroomId + `"]`).trigger("click");
    }, 100);
}

// 함수: 기존 대화내용을 화면에 표시
function getChatmessages(chatroomId, pageNumber, numberOfItems) {
    // AJAX: 기존 대화내용 조회
    $.ajax({
        url: "/chat/getchatmessages",
        type: "GET",
        data: {
            myId: chatboxMyId,
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

// 함수: 개별 메세지를 화면에 표시
function putChatmessage(chatmessage, isloading) {
    if (isloading) {
        // DB의 메세지 목록을 불러올 때는 앞쪽으로 추가
        if (chatmessage.senderId != chatboxMyId) {
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
        if (chatmessage.senderId != chatboxMyId) {
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
