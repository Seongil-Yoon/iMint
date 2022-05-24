$(function () {
    notifyboxInitializer();
    notifyboxEventHandler();
});

// 함수: 보호자 회원일 때 알림 기능 활성화
function notifyboxInitializer() {
    if (webSocketMyRole == "GUARD") {
        $("#notifybox-openbtn").show();
        // 알림 목록 새로 불러오기
        loadNotifications();
        checkUnreadNotify();
        if ("Notification" in window) {
            Notification.requestPermission();
        }
    }
}

// 함수: 각종 버튼 이벤트 등록
function notifyboxEventHandler() {
    // 이벤트 등록: 알림 버튼 누르면 알림 버튼 숨기고 알림박스 표시
    $("#notifybox-openbtn").on("click", function () {
        $(this).hide();
        $("#chatbox-openbtn").hide();
        $("#notifybox-main").show();
    });

    // 이벤트 등록: 알림박스 닫기 버튼 누르면 알림박스 숨기고 알림 버튼 표시
    $("#notifybox-close").on("click", function () {
        $("#notifybox-main").hide();
        $("#chatbox-openbtn").show();
        checkUnreadChat();
        if (webSocketMyRole == "GUARD") {
            $("#notifybox-openbtn").show();
            checkUnreadNotify();
        }
    });

    // 이벤트 등록: 알림 모두 읽음 표시 버튼
    $("#notifybox-option-readall").on("click", function () {
        $.ajax({
            url: "/ws/notify/readall",
            type: "POST",
            dataType: "JSON",
            success: function () {
                loadNotifications();
            },
        });
    });

    // 이벤트 등록: 알림 모두 삭제 버튼
    $("#notifybox-option-deleteall").on("click", function () {
        swal({
            title: "알림 모두 삭제",
            text: "모든 알림을 삭제하시겠습니까?",
            icon: "error",
            buttons: ["취소", "모두 삭제"],
            dangerMode: true,
        }).then((confirm) => {
            if (confirm) {
                $.ajax({
                    url: "/ws/notify/deleteall",
                    type: "POST",
                    dataType: "JSON",
                    success: function () {
                        loadNotifications();
                    },
                });
            }
        });
    });
}

// 함수: 알림 수신시 알림 목록에 추가 + 데스크톱 알림
function notificationHandler(notify) {
    prependNotification(notify);
    checkUnreadNotify();
    let options = {
        body: notify.message,
        icon: "/static/images/hamster.png",
    };
    if ("Notification" in window) {
        new Notification("iMint :: 내 아이의 활동 알림", options);
    }
}

// 함수: 알림 목록 표시
function loadNotifications() {
    // 알림 목록 초기화
    $("#notifybox-list-notifications").html("");

    // AJAX: 채팅방 목록 요청
    $.ajax({
        url: "/ws/notify/getnotifications",
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            // 채팅방 목록 갱신
            for (let i in result) {
                prependNotification(result[i]);
            }
        },
    });
}

// 함수: 읽지 않은 알림 메세지 확인
function checkUnreadNotify() {
    setTimeout(function () {
        let check = $(".notifybox-notifications-notification.unread");
        if (check.length > 0) {
            $("#notifybox-newalert").show();
        } else {
            $("#notifybox-newalert").hide();
        }
    }, 100);
}

// 함수: 새 알림 추가
function prependNotification(notify) {
    $("#notifybox-list-notifications").prepend(
        `<div class="notifybox-notifications-notification" data-notificationId="` +
            notify.id +
            `" data-category="` +
            notify.category +
            `"></div>`
    );

    if (notify.read == false) {
        $("div[data-notificationId='" + notify.id + "']").addClass("unread");
    }

    $("div[data-notificationId='" + notify.id + "']")
        .append(`<div class="notifybox-notification-message"><p></p></div>`)
        .append(
            `<div class="notifybox-notification-option deletebtn" onclick="deleteNotification(${notify.id})">❌</div>`
        )
        .append(
            `<div class="notifybox-notification-option readbtn" onclick="readNotification(${notify.id})">✔️</div>`
        )
        .find("p")
        .text(notify.message);
}

// 함수: 알림 읽음 표시
function readNotification(notificationId) {
    $.ajax({
        url: "/ws/notify/read",
        type: "POST",
        data: {
            notificationId: notificationId,
        },
        dataType: "JSON",
        success: function (response) {
            if (response.result) {
                $(
                    "div[data-notificationId='" + notificationId + "']"
                ).removeClass("unread");
            }
        },
    });
}

// 함수: 알림 삭제
function deleteNotification(notificationId) {
    $.ajax({
        url: "/ws/notify/delete",
        type: "POST",
        data: {
            notificationId: notificationId,
        },
        dataType: "JSON",
        success: function (response) {
            if (response.result) {
                $("div[data-notificationId='" + notificationId + "']").remove();
            }
        },
    });
}
