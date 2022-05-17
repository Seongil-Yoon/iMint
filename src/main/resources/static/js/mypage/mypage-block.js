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
