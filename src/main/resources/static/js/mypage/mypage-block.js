const childSelect = document.querySelector("#select-child");
if (childSelect != null) {
    childSelect.addEventListener("change", showChild);
}
//드롭다운에서 내 아이 선택 시 해당 아이에 해당하는 내용이 보여지도록 함
function showChild(event) {
    removeShowList();

    targetList = document.getElementsByClassName(event.target.value);
    for (i = 0; i < targetList.length; i++) {
        targetList[i].classList.add("show-list");
    }
}

function removeShowList() {
    targetList = document.querySelectorAll(".item-list-guard");

    for (i = 0; i < targetList.length; i++) {
        targetList[i].classList.remove("show-list");
    }
}

//마지막 채팅 메세지가 길어질 경우 처리
const subjectChat = document.querySelectorAll(".item-block-date");

for (i = 0; i < subjectChat.length; i++) {
    subjectChat[i].innerHTML = subjectChat[i].innerHTML
        .substring(0, 16)
        .replace("T", " ");
}

// 차단해제
$(".unblock_btn").each(function () {
    $(this).on("click", function (e) {
        console.log("ON");
        unblockMbId = e.target.id;
        unblockMbNick = e.target.value;
        swal({
            title: "차단 해제",
            text:
                unblockMbNick +
                "님의 차단을 해제하면\n\n1. " +
                unblockMbNick +
                "님이 등록한 글을 다시 보이게 합니다.\n2. " +
                unblockMbNick +
                "님과 다시 대화를 나눌 수 있게 됩니다.\n\n정말로 " +
                unblockMbNick +
                "님의 차단을 해제하시겠습니까?",
            icon: "error",
            buttons: ["다시 생각해볼래요", "해제할래요"],
            dangerMode: true,
        }).then((confirm) => {
            if (confirm) {
                $.ajax({
                    url: "/unblock",
                    type: "post",
                    data: {
                        unblockMbId: unblockMbId,
                    },
                    dataType: "json",
                    success: function (response) {
                        /* 결과 */
                        if (response.result === "unblock") {
                            swal(unblockMbNick + "님의 차단을 해제했습니다.", {
                                icon: "success",
                            }).then(() => {
                                location.reload();
                            });
                        }
                    }, // success end
                }); // ajax end
            }
        });
    });
});
