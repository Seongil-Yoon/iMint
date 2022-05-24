let chatboxChildId = null;
let stompClient = null;
let currentSubscription = null;
let currentChatroomId = null;
let currentGoodsId = null;
let currentOpponentId = null;
let currentOpponentNick = null;
let currentPageNumber = null;
let lastOldMessageDate = null;
let lastNewMessageDate = null;

$(function () {
    chatboxInitializer();
    chatboxEventHandler();
});

// 함수: 보호자 회원일 때 내 아이 선택 상자 추가
function chatboxInitializer() {
    // 인증된 회원이면 채팅 버튼 표시하고 웹소켓 접속
    if (webSocketMyRole == "CHILD" || webSocketMyRole == "GUARD") {
        $("#chatbox-openbtn").show();
        connectWS(webSocketMyId);
        loadChatrooms();
        checkUnreadChat();

        if (webSocketMyRole == "GUARD") {
            $.ajax({
                url: "/ws/chat/getmychildren",
                type: "GET",
                dataType: "JSON",
                success: function (result) {
                    $("#chatbox-title-select").show();
                    if (result.length > 0) {
                        // 보호자 회원이면 아이 선택상자 추가
                        $("#chatbox-title-select")
                            .append(`<option selected>내 아이 선택</option>`)
                            .on("change", function () {
                                chatboxChildId = $(this).val();
                                if (chatboxChildId != "내 아이 선택") {
                                    loadChatrooms();
                                }
                            });

                        for (let i in result) {
                            $("#chatbox-title-select").append(
                                `<option value="` +
                                    result[i].mbId +
                                    `">` +
                                    result[i].mbNick +
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
    }
}

// 함수: 각종 버튼 이벤트 등록
function chatboxEventHandler() {
    // 이벤트 등록: 채팅 버튼 누르면 채팅 버튼 숨기고 채팅박스 표시
    $("#chatbox-openbtn").on("click", function () {
        $(this).hide();
        if (webSocketMyRole == "GUARD") {
            $("#notifybox-openbtn").hide();
        }
        $("#chatbox-main").show();
    });

    // 이벤트 등록: 채팅박스 닫기 버튼 누르면 채팅박스 숨기고 채팅 버튼 표시
    $("#chatbox-close").on("click", function () {
        $("#chatbox-main").hide();
        $("#chatbox-openbtn").show();
        checkUnreadChat();
        if (webSocketMyRole == "GUARD") {
            $("#notifybox-openbtn").show();
            checkUnreadNotify();
        }
    });

    // 이벤트 등록: 채팅방 닫기 버튼 누르면 채팅방 목록 표시
    $("#chatview-close").on("click", function () {
        currentSubscription.unsubscribe();
        lastOldMessageDate = null;
        lastNewMessageDate = null;
        $("div[data-chatroomId='" + currentChatroomId + "']").removeClass(
            "unread"
        );
        $("#chatbox-view").hide();
        $("#chatbox-list").show();
    });

    // 이벤트 등록: 전송 버튼 누르면 메세지 보내기
    $("#chatbox-send-sendbtn").on("click", function () {
        let message = $("#chatbox-view-send textarea").val();
        if (message.trim().length != 0) {
            if (messageFilter(message)) {
                sendMessage(message);
            }
        }
        $("#chatbox-view-send textarea").val("");
    });

    // 이벤트 등록: 메세지 입력창 엔터로 보내기(SHIFT+엔터는 줄바꿈)
    $("#chatbox-view-send textarea").keypress(function (event) {
        if (event.keyCode == 10 || event.keyCode == 13) {
            event.preventDefault();
            if (!event.shiftKey) {
                $("#chatbox-send-sendbtn").trigger("click");
            } else {
                $("#chatbox-view-send textarea").val(
                    $("#chatbox-view-send textarea").val() + "\n"
                );
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
    //              스크롤이 맨 위로 가면 추가 메세지 불러오기
    $("#chatbox-view-chatmessages").scroll(function () {
        if (
            Math.round(
                $("#chatbox-view-chatmessages").scrollTop() +
                    $("#chatbox-view-chatmessages").innerHeight()
            ) >= $("#chatbox-view-chatmessages").prop("scrollHeight")
        ) {
            $("#chatbox-view-alertnew").hide();
        } else if ($("#chatbox-view-chatmessages").scrollTop() <= 0) {
            if (currentPageNumber > 0) {
                currentPageNumber++;
                getChatmessages(false);
            }
        }
    });

    // 이벤트 등록: 회원 차단 버튼
    $("#chatview-option-block").on("click", function () {
        swal({
            title: "회원 차단",
            text:
                currentOpponentNick +
                "님을 차단하면\n\n1. " +
                currentOpponentNick +
                "님과 예약중인 모든 거래가 취소됩니다.\n2. " +
                currentOpponentNick +
                "님이 등록한 글을 보이지 않게 숨깁니다.\n3. " +
                currentOpponentNick +
                "님과 더 이상 대화를 나눌 수 없습니다.\n\n정말로 " +
                currentOpponentNick +
                "님을 차단하시겠습니까?",
            icon: "error",
            buttons: ["다시 생각해볼래요", "차단할래요"],
            dangerMode: true,
        }).then((confirm) => {
            if (confirm) {
                $.ajax({
                    url: "/block",
                    type: "POST",
                    data: {
                        blockMbId: currentOpponentId,
                    },
                    dataType: "JSON",
                    success: function (r) {
                        if (r.result == "block") {
                            swal(
                                currentOpponentNick +
                                    "님을 차단했습니다.\n\n마이페이지 > 나의 차단 목록에서 차단을 해제할 수 있습니다.",
                                {
                                    icon: "success",
                                }
                            );
                            $("#chatview-close").trigger("click");
                        } else {
                            swal("오류가 발생했습니다.", {
                                icon: "error",
                            });
                        }
                        getTrxStatus();
                    },
                });
            }
        });
    });

    // 이벤트 등록: 예약하기
    $("#chatbox-trxbtns-makeresrv").on("click", function () {
        swal({
            title: "거래 예약",
            text:
                "거래를 예약을 할 때에는\n상대방과 대화를 충분히 나눈 후에 결정해주세요.\n\n정말로 " +
                currentOpponentNick +
                "님과 거래를 예약하시겠습니까?",
            icon: "info",
            buttons: ["다시 생각해볼래요", "예약할래요"],
            dangerMode: false,
        }).then((confirm) => {
            if (confirm) {
                $.ajax({
                    url: "/transaction/resrv/make",
                    type: "POST",
                    data: {
                        goodsId: currentGoodsId,
                        chatroomId: currentChatroomId,
                    },
                    dataType: "JSON",
                    success: function (r) {
                        if (r.result == "success") {
                            swal("거래를 예약했습니다.", {
                                icon: "success",
                            });
                        } else {
                            swal("오류가 발생했습니다.", {
                                icon: "error",
                            });
                        }
                        getTrxStatus();
                    },
                });
            }
        });
    });

    // 이벤트 등록: 예약취소
    $("#chatbox-trxbtns-cancelresrv").on("click", function () {
        swal({
            title: "예약 취소",
            text:
                "예약을 취소할 때에는\n상대방과 대화를 충분히 나눈 후에 결정해주세요.\n\n정말로 " +
                currentOpponentNick +
                "님과의 예약을 취소하시겠습니까?",
            icon: "error",
            buttons: ["다시 생각해볼래요", "취소할래요"],
            dangerMode: true,
        }).then((confirm) => {
            if (confirm) {
                $.ajax({
                    url: "/transaction/resrv/cancel",
                    type: "POST",
                    data: {
                        goodsId: currentGoodsId,
                        chatroomId: currentChatroomId,
                    },
                    dataType: "JSON",
                    success: function (r) {
                        if (r.result == "success") {
                            swal("예약을 취소했습니다.", {
                                icon: "success",
                            });
                        } else {
                            swal("오류가 발생했습니다.", {
                                icon: "error",
                            });
                        }
                        getTrxStatus();
                    },
                });
            }
        });
    });

    // 이벤트 등록: 판매완료
    $("#chatbox-trxbtns-comptrx").on("click", function () {
        swal({
            title: "판매 완료",
            text:
                "판매를 완료하면 더 이상 취소할 수 없습니다.\n거래를 모두 마친 후에 결정해주세요.\n\n정말로 " +
                currentOpponentNick +
                "님과 거래하셨습니까?",
            icon: "warning",
            buttons: ["아직 안 했어요", "거래했어요"],
            dangerMode: true,
        }).then((confirm) => {
            if (confirm) {
                $.ajax({
                    url: "/transaction/trx/complete",
                    type: "POST",
                    data: {
                        buyerId: currentOpponentId,
                        goodsId: currentGoodsId,
                    },
                    dataType: "JSON",
                    success: function (r) {
                        if (r.result == "success") {
                            swal("판매를 완료했습니다.", {
                                icon: "success",
                            });
                        } else {
                            swal("오류가 발생했습니다.", {
                                icon: "error",
                            });
                        }
                        getTrxStatus();
                    },
                });
            }
        });
    });

    // 이벤트 등록: 평가하기
    $("#chatbox-trxbtns-ratetrx").on("click", function () {
        const ratingWindow = window.open(
            "/transaction/rating?goodsId=" + currentGoodsId,
            "ratingWindow",
            "top=" +
                (window.screen.height - 520) / 2 +
                ", left=" +
                (window.screen.width - 520) / 2 +
                ", width=520, height=520, popup"
        );
        $(ratingWindow.document).on("beforeunload", function () {
            getTrxStatus();
        });
    });
}

// 함수: 웹소켓에 연결 + 알림 채널 구독
async function connectWS(webSocketMyId) {
    await new Promise(function (resolve, reject) {
        let socket = new SockJS("/ws");
        stompClient = Stomp.over(socket);
        stompClient.connect({ "user-name": webSocketMyId }, function (frame) {
            resolve("success");
        });
    });

    stompClient.subscribe("/ws/notify", function (notify) {
        notify = JSON.parse(notify.body);
        if (notify.type == "chat") {
            let findChatroom = $(
                "[data-chatroomId=" + notify.content.chatroomId + "]"
            );
            if (findChatroom.length != 0) {
                findChatroom.addClass("unread");
                findChatroom
                    .find(".chatbox-chatroom-lastmessage")
                    .text(notify.content.message);
                $("#chatbox-list-chatrooms").prepend(findChatroom);
            } else {
                loadChatrooms();
            }
            checkUnreadChat();
        } else if (notify.type == "notification") {
            notificationHandler(notify.content);
        }
    });
}

// 함수: 메세지 전송
function sendMessage(message) {
    stompClient.send(
        "/ws/send/chat/" + currentChatroomId,
        {},
        JSON.stringify({
            message: message,
        })
    );
}

// 함수: 채팅방 목록 표시
function loadChatrooms() {
    // 채팅방 목록 초기화
    $("#chatbox-list-chatrooms").html("");

    // AJAX: 채팅방 목록 요청
    $.ajax({
        url: "/ws/chat/getchatrooms",
        type: "GET",
        data: {
            childId: chatboxChildId,
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
                    .append("<div></div>")
                    .append(
                        `<span class="chatbox-chatroom-lastmessage">` +
                            (result[i].message === null
                                ? ""
                                : result[i].message) +
                            `</span>`
                    )
                    .find("div")
                    .append(
                        `<span class="chatbox-chatroom-nickname">` +
                            result[i].opponentNick +
                            `</span>`
                    )
                    .append(
                        `<span class="chatbox-chatrooms-whois ` +
                            (result[i].category == "buy"
                                ? `seller">(판매자)</span>`
                                : `buyer">(구매자)</span>`)
                    );

                if (result[i].message == null) {
                    $("div[data-chatroomId='" + result[i].id + "']").css(
                        "display",
                        "none"
                    );
                    result[i].read == true;
                }

                if (
                    result[i].senderId != null &&
                    result[i].senderId != webSocketMyId &&
                    result[i].senderId != chatboxChildId &&
                    result[i].read == false
                ) {
                    $("div[data-chatroomId='" + result[i].id + "']").addClass(
                        "unread"
                    );
                }
            }

            // 이벤트 등록: 채팅방 목록 누르면 채팅 화면 표시
            $(".chatbox-chatrooms-chatroom").each(function () {
                $(this).on("click", function () {
                    $("#chatbox-view").show();
                    $("#chatbox-list").hide();
                    $(this).removeClass("unread");
                    joinChatroom(this);
                });
            });
        },
    });
}

// 함수: 읽지 않은 채팅 메세지 확인
function checkUnreadChat() {
    setTimeout(function () {
        let check = $(".chatbox-chatrooms-chatroom.unread");
        if (check.length > 0) {
            $("#chatbox-newalert").show();
        } else {
            $("#chatbox-newalert").hide();
        }
    }, 100);
}

// 함수: 거래 관련 버튼 영역 표시/숨기기
function setExtraUIs(showTrxbtns) {
    let defaultHeight =
        parseInt($(":root").css("--total-height")) -
        parseInt($(":root").css("--title-height")) -
        parseInt($(":root").css("--chatroom-height")) * 2;

    if (webSocketMyRole == "GUARD") {
        // 보호자 회원일 때 메세지 입력 상자 숨기기
        $("#chatbox-view-send").hide();
        $("#chatbox-view-trxbtns").html("");
        $("#chatbox-view-trxbtns").hide();
        $("#chatbox-view-chatmessages").height(defaultHeight + 79);
    } else {
        if (showTrxbtns) {
            $("#chatbox-view-chatmessages").height(defaultHeight - 30);
            $("#chatbox-view-trxbtns").show();
        } else {
            $("#chatbox-view-chatmessages").height(defaultHeight);
            $("#chatbox-view-trxbtns").hide();
        }
    }
}

// 함수: 거래 상태 조회
function getTrxStatus() {
    $.ajax({
        url: "/transaction/trx/check",
        type: "GET",
        data: {
            childId: chatboxChildId,
            opponentId: currentOpponentId,
            goodsId: currentGoodsId,
        },
        dataType: "JSON",
        success: function (trx) {
            $(".chatbox-detail-goodsstatus").each(function () {
                $(this).hide();
            });
            $(".chatbox-trxbtn").each(function () {
                $(this).hide();
            });
            $("#chatbox-goodsstatus-" + trx.check).show();
            if (trx.check.includes("wait")) {
                // 판매중(구매가능) 상태
                if (trx.check.includes("seller")) {
                    // 판매중
                    setExtraUIs(true);
                    $("#chatbox-trxbtns-makeresrv").show();
                    $("#chatbox-trxbtns-comptrx").show();
                } else {
                    // 구매가능
                    setExtraUIs(false);
                }
            } else if (trx.check.includes("resrv")) {
                if (
                    trx.check.includes("match") ||
                    trx.check.includes("buyer")
                ) {
                    // 예약완료 상태
                    if (trx.check.includes("seller")) {
                        // 예약완료 - 판매자:구매자 시점
                        setExtraUIs(true);
                        $("#chatbox-trxbtns-cancelresrv").show();
                        $("#chatbox-trxbtns-comptrx").show();
                    } else {
                        // 예약완료 - 예약자 시점
                        setExtraUIs(false);
                    }
                } else {
                    // 예약중 상태
                    setExtraUIs(false);
                }
            } else if (trx.check.includes("comp")) {
                // 판매완료(구매완료/구매불가)
                if (trx.check.includes("seller")) {
                    if (trx.check.includes("match")) {
                        // 판매완료 - 판매자:구매자 시점
                        setExtraUIs(true);
                        $("#chatbox-trxbtns-ratetrx").show();
                    } else {
                        // 판매완료 - 판매자:기타 시점
                        setExtraUIs(false);
                    }
                } else if (trx.check.includes("buyer")) {
                    // 구매완료 - 구매자:판매자 시점
                    setExtraUIs(true);
                    $("#chatbox-trxbtns-ratetrx").show();
                } else {
                    // 구매불가 - 기타 시점
                    setExtraUIs(false);
                }
            } else {
                // 오류발생
                setExtraUIs(false);
            }
        },
    });
}

// 함수: 채팅방(채팅화면) 표시
function joinChatroom(chatroom) {
    currentChatroomId = $(chatroom).attr("data-chatroomId");
    currentGoodsId = $(chatroom).attr("data-goodsId");
    currentOpponentId = $(chatroom).attr("data-opponentId");
    currentOpponentNick = $(chatroom).find(".chatbox-chatroom-nickname").text();
    currentPageNumber = 1;

    // 거래 버튼 초기화
    setExtraUIs(false);
    // 채팅 메세지 초기화
    $("#chatbox-view-chatmessages").html("");

    // 채팅창 제목표시줄 갱신
    $("#chatbox-view-title .chatbox-title-text").text(
        currentOpponentNick + "님과의 채팅"
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
    $(".chatbox-detail-suggestible").each(function () {
        $(this).hide();
    });
    $(
        "#chatbox-suggestible-" + $(chatroom).attr("data-goodsSuggestible")
    ).show();

    // 거래 정보 갱신
    getTrxStatus();

    // DB에서 채팅 기록 조회
    getChatmessages(true);

    // STOMP 채팅방 SUBSCRIBE
    currentSubscription = stompClient.subscribe(
        "/ws/chat/" + currentChatroomId,
        function (chatmessage) {
            if (
                Math.round(
                    $("#chatbox-view-chatmessages").scrollTop() +
                        $("#chatbox-view-chatmessages").innerHeight() * 2
                ) >= $("#chatbox-view-chatmessages").prop("scrollHeight")
            ) {
                putChatmessage(JSON.parse(chatmessage.body), false);
                $("#chatbox-view-alertnew").trigger("click");
                $("#chatbox-view-alertnew").hide();
            } else {
                putChatmessage(JSON.parse(chatmessage.body), false);
                $("#chatbox-view-alertnew").show();
            }
        }
    );
}

// 함수: 채팅방 목록이 표시된 상태로 만들기(접혀있으면 펼치고 채팅방이면 목록으로 돌아가기)
function directOpenChatroom(childId) {
    if ($("#chatbox-main").css("display") == "none") {
        $("#chatbox-openbtn").trigger("click");
    } else if ($("#chatbox-view").css("display") != "none") {
        $("#chatview-close").trigger("click");
    }
    if (childId != null) {
        setTimeout(function () {
            $("#chatbox-title-select").val(childId).trigger("change");
        }, 100);
    }
}

// 함수: 채팅방으로 즉시 입장
function directJoinChatroom(chatroomId, childId) {
    let timeout = 100;
    directOpenChatroom(chatroomId);
    if (childId != null) {
        timeout = 500;
    }
    setTimeout(function () {
        $(`div[data-chatroomId="` + chatroomId + `"]`).trigger("click");
    }, timeout);
}

// 함수: 기존 대화내용을 화면에 표시
function getChatmessages(isInitializing) {
    // AJAX: 기존 대화내용 조회
    $.ajax({
        url: "/ws/chat/getchatmessages",
        type: "GET",
        data: {
            childId: chatboxChildId,
            chatroomId: currentChatroomId,
            pageNumber: currentPageNumber,
        },
        dataType: "JSON",
        success: function (result) {
            if (result.length < 30) {
                currentPageNumber = -1;
            }
            let currentScrollHeight = $("#chatbox-view-chatmessages").prop(
                "scrollHeight"
            );

            // 메세지 목록 갱신
            for (let i = 0; i < result.length; i++) {
                putChatmessage(result[i], true);
            }

            if (currentPageNumber < 0) {
                $("#chatbox-view-chatmessages").prepend(
                    `<div class="chatbox-chatmessages-timerule"><hr><span>` +
                        lastOldMessageDate.toLocaleDateString() +
                        `</span><hr></div>`
                );
            }

            if (isInitializing) {
                // 스크롤 맨 아래로 이동
                $("#chatbox-view-chatmessages").scrollTop(
                    $("#chatbox-view-chatmessages").prop("scrollHeight")
                );
            } else {
                // 스크롤 위치 유지
                $("#chatbox-view-chatmessages").scrollTop(
                    $("#chatbox-view-chatmessages").prop("scrollHeight") -
                        currentScrollHeight
                );
            }
        },
    });
}

// 함수: 개별 메세지를 화면에 표시
function putChatmessage(chatmessage, isLoading) {
    let newMessageDate = new Date(
        new Date(chatmessage.sendDate).toLocaleDateString()
    );
    if (lastOldMessageDate == null) {
        lastOldMessageDate = newMessageDate;
    }
    if (lastNewMessageDate == null) {
        lastNewMessageDate = newMessageDate;
    }

    if (isLoading) {
        // 가장 오래된 메세지와 날짜가 다르면 가로선 삽입
        if (newMessageDate < lastOldMessageDate) {
            $("#chatbox-view-chatmessages").prepend(
                `<div class="chatbox-chatmessages-timerule"><hr><span>` +
                    lastOldMessageDate.toLocaleDateString() +
                    `</span><hr></div>`
            );
            lastOldMessageDate = newMessageDate;
        }

        // DB의 메세지 목록을 불러올 때는 앞쪽으로 추가
        if (
            chatmessage.senderId != webSocketMyId &&
            chatmessage.senderId != chatboxChildId
        ) {
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
        // 가장 최근 메세지와 날짜가 다르면 가로선 삽입
        if (newMessageDate > lastNewMessageDate) {
            $("#chatbox-view-chatmessages").append(
                `<div class="chatbox-chatmessages-timerule"><hr><span>` +
                    newMessageDate.toLocaleDateString() +
                    `</span><hr></div>`
            );
            lastNewMessageDate = newMessageDate;
        }

        // 웹소켓을 통해 메세지를 받았을 때는 뒤쪽으로 추가
        if (
            chatmessage.senderId != webSocketMyId &&
            chatmessage.senderId != chatboxChildId
        ) {
            $("#chatbox-view-chatmessages").append(
                `<div data-messageId="` +
                    chatmessage.id +
                    `" class="chatbox-chatmessages-chatmessage left"></div>`
            );

            // 상대가 새 메세지를 보내면 내가 보낸 기존 메세지는 읽음으로 표시
            $(
                ".chatbox-chatmessages-chatmessage.right .chatbox-chatinfo-isread"
            ).each(function () {
                $(this).text("읽음");
            });
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
                chatmessage.sendDate.substr(11, 5) +
                `</div>`
        )
        .append(
            `<div class="chatbox-chatinfo-isread">` +
                ((chatmessage.senderId == webSocketMyId ||
                    chatmessage.senderId == chatboxChildId) &&
                chatmessage.read
                    ? "읽음"
                    : "") +
                `</div>`
        );
}

// 함수: 메세지에 금지어가 포함되어있는지 확인
function messageFilter(message) {
    let testcase = [
        { type: "주민등록번호", regex: /\d{6}[,\.\-\s]?[1-4]\d{6}/ },
        { type: "주민등록번호", regex: /^[1-4]\d{6}$/ },
        {
            type: "전화번호",
            regex: /[(]?[0]([2]|\d{2})[)]?([,\.\-\s]?)\d{3,4}(\2)\d{4}/,
        },
        { type: "나이", regex: /\d{1,4}(년생|세|살)/ },
        {
            // 주소 정규표현식: 당근마켓 블로그(https://medium.com/daangn/%EC%A3%BC%EC%86%8C-%EC%9D%B8%EC%8B%9D%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%82%BD%EC%A7%88%EC%9D%98-%EA%B8%B0%EB%A1%9D-df2d8f82d25)
            type: "주소",
            regex: /([가-힣A-Za-z·\d~\-\.]{2,}(로|길).[\d]+)|([가-힣A-Za-z·\d~\-\.]+(읍|동)\s)[\d]+/,
        },
        { type: "인증번호", regex: /^[\d]{6}$/ },
        {
            type: "학교/학년",
            regex: /[가-힣A-Za-z·\d~\-\.]{2,}(초|교).([·\d~\-\.]|학년)+/,
        },
    ];

    let testresult = [];

    for (let i in testcase) {
        if (message.search(testcase[i].regex) != -1) {
            testresult.push(testcase[i].type);
        }
    }

    if (testresult.length > 0) {
        $("#chatbox-view-chatmessages").append(
            `<div class="chatbox-chatmessages-chatmessage center"><div class="chatbox-chatmessage-message">` +
                `경고!<br>` +
                `보내려는 메세지에 민감한 정보가 포함되어있는 것 같습니다. 정말 보내도 되는 정보인지 보호자와 상의하세요.<br><br>` +
                `포함된 정보: [` +
                testresult +
                `]</div></div>`
        );
        $("#chatbox-view-chatmessages").scrollTop(
            $("#chatbox-view-chatmessages").prop("scrollHeight")
        );
        return false;
    } else {
        return true;
    }
}
