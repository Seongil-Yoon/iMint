//드롭다운에서 내 아이 선택 시 해당 아이의 이름이 타이틀에 나오도록 함
const childSelected = document.querySelectorAll('.dropdown-item');
var cnt = 1;

function selectChild(event){
	const selected = event.target.id;
	const selectedNick = event.target.text;
	const childTitle = document.getElementById('child-define');
	
	childTitle.innerHTML = "내 아이 " + selectedNick + "님의 차단 목록입니다";
}

childSelected.forEach(item => item.addEventListener('click', selectChild));


//드롭다운에서 내 아이 선택 시 해당 아이에 해당하는 내용이 보여지도록 함
function showChild(event){
	const selected = event.target.id;
	
	removeShowList();
	
	targetList = document.getElementsByClassName(selected);
	
	/*parentlist = $(listTarget).parents(".show");
	childlist= $(parentlist).find("div.item-list");
	*/
	
	for(i = 0; i < targetList.length; i++){
		targetList[i].classList.add('show-list');
	}
	
	childbtns = $(targetList).find("p.count");
	
	/*childbtns.each(function(){
		$(this).text(cnt);
		cnt += 1;
	})*/
	
	cnt = 1;
}

function removeShowList(){
	targetList = document.querySelectorAll('.item-list-guard');
	
	
	
	var i;
	
	for(i = 0; i < targetList.length; i++){
		targetList[i].classList.remove('show-list');
	}
}

childSelected.forEach(item => item.addEventListener('click', showChild));

//마지막 채팅 메세지가 길어질 경우 처리
const subjectChat = document.querySelectorAll('.item-person-chat-content');

for(i = 0; i < subjectChat.length; i++){
	text = subjectChat[i].innerHTML;
	if(text.length >= 13){
		text_ = text.substr(0, 13);
		subjectChat[i].innerHTML = text_ + "..";
	}
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