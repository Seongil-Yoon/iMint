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
    subjectChat[i].innerHTML = subjectChat[i].innerHTML.substring(0, 16).replace("T", " ");
}

// 차단해제 
$(".unblock_btn").on('click', function(e) {
	unblockMbId = e.target.id;
	swal({
            title: "회원 차단",
            text: "차단을 해제할까요?",
            icon: "error",
            buttons: ["다시 생각해볼래요", "차단을 해제할래요"],
            dangerMode: true,
        }).then((confirm) => {
            if (confirm) {
				$.ajax({
					url: "/unblock",
					type: "post",
					data: {
						"unblockMbId": unblockMbId
					},
					dataType: "json",
			
					success: function(response) { /* 결과 */		
						if(response.result === "unblock") {
							alert("차단을 해제했어요.");
						}
					} // success end 
				}); // ajax end 
			}
        });
})