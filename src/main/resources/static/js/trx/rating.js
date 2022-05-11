$(function () {
    popupResizer();
    statusInitializer();
});

// 팝업 창 크기 조절
function popupResizer() {
    window.resizeTo(
        $("#rating-wrap").outerWidth(true) +
            (window.outerWidth - window.innerWidth),
        $("#rating-wrap").outerHeight(true) +
            (window.outerHeight - window.innerHeight)
    );
}

// 평가할 권한이 없으면 오류 메시지 출력
function statusInitializer() {
    if (!ratingStatus.includes("rated")) {
        swal({
            title: "오류 발생",
            text: "잘못된 접근입니다.",
            icon: "error",
            button: "닫기",
        }).then(() => {
            window.close();
        });
    } else {
        eventHandler();
    }
}

// 버튼에 이벤트 등록
function eventHandler() {
    // 닫기 버튼
    $("#rating-close").on("click", function () {
        window.close();
    });

    // 평가 완료 버튼
    if (ratingStatus == "!rated") {
        $("#rating-submit").on("click", function () {
            console.log(trxId);
            console.log($("input[name=ratingScore]:checked").val());
            $.ajax({
                url: "/transaction/rating/make",
                type: "POST",
                data: {
                    trxId: trxId,
                    ratingScore: $("input[name=ratingScore]:checked").val(),
                },
                dataType: "JSON",
                success: function (out) {
                    if (out.result == "success") {
                        swal({
                            title: "평가 완료",
                            text: "평가해주셔서 감사합니다!",
                            icon: "success",
                            button: "닫기",
                        }).then(() => {
                            window.close();
                        });
                    } else {
                        swal({
                            title: "오류 발생",
                            text: "오류가 발생했습니다.\n나중에 다시 시도해주세요.",
                            icon: "error",
                            button: "닫기",
                        }).then(() => {
                            window.close();
                        });
                    }
                },
            });
        });
    }
}
