// 상단탭 선택시 baby-mylist.jsp 내에서 다른 컨테이너가 나오도록 함


const tabItems = document.querySelectorAll('.tab-item');
const tabContentItems = document.querySelectorAll('.tab-content-item');
//Select tab content item
function selectItem(e) {
    removeShow();
    removeSelected();
    removeSelected2();
    // Grab content item from DOM
    const tabContentItem = document.querySelector(`#${this.id}-content`);
    const tabItem = document.querySelector(`#${this.id}`);
    // Add show class
    tabContentItem.classList.add('show');
    tabItem.classList.add('selected-tab');
}

function removeShow(){
    tabContentItems.forEach(item => item.classList.remove('show'))
}

function removeSelected(){
    tabItems.forEach(item => item.classList.remove('selected-tab'))
}

function removeSelected2(){
    tabItems.forEach(item => item.classList.remove('selected'))
}



tabItems.forEach(item => item.addEventListener('click', selectItem));

/*
//상단탭 거래완료 목록 선택시 include된 mylist-content.jsp에서 다른 내용이 보여지도록 함

const tab3 = document.querySelector('#tab-3');
	
function changeTab3(e){
	parentDiv = $('.lables-price').parents(".show");
	
	//거래완료 목록의 '수입' 항목명 변경
	childlable1 = $(parentDiv).find("h6.lables-price");
	childlable1.text("수입");
	childlable1.attr("class","lables-price-in");
	
	//거래완료 목록의 '수입' 항목 내 내용 변경
	childitem1 = $(parentDiv).find("p.text-price");
	childitem1.text("3000원");
	childitem1.css("margin-bottom", "0.25rem");
	childitem1.attr("class","items-price-in");
	
	//거래완료 목록의 '지출' 항목명 변경
	childlable2 = $(parentDiv).find("h6.lables-link");
	childlable2.text("지출");
	childlable2.attr("class","lables-price-out");
	
	//거래완료 목록의 '지출' 항목 내 내용 변경
	childitem2 = $(parentDiv).find("p.text-link");
	childitem2.text("");
	childitem2.css("margin-bottom", "0.25rem");
	childitem2.attr("class","items-price-out");
}

tab3.addEventListener('click', changeTab3);
*/

//전체선택 선택 시 모든 선택 버튼이 선택되도록 함
const btnSelected = document.querySelectorAll('.selectAllbtn');

function selectAll(event)  {
	const btnTarget = event.target;
	
	parentbtn = $(btnTarget).parents(".show");
	childbtns = $(parentbtn).find("input.select-targets");

  	if($(btnTarget).is(":checked") == true){
		childbtns.each(function(index){ 
	    	$(this).prop("checked", true);
	    });
	} else {
		childbtns.each(function(index){ 
	    	$(this).prop("checked", false);
	    });
	}
}

btnSelected.forEach(item => item.addEventListener('click', selectAll));

const childSelect = document.querySelector("#select-child");
childSelect.addEventListener("change", showChild);

//드롭다운에서 내 아이 선택 시 해당 아이에 해당하는 내용이 보여지도록 함
function showChild(event) {
    removeShowList();

    targetList = document.getElementsByClassName(event.target.value);
    for (i = 0; i < targetList.length; i++) {
        targetList[i].classList.add("show-list");
    }
}

function removeShowList(){
	targetList = document.querySelectorAll('.item-list-guard');
	
	for(i = 0; i < targetList.length; i++){
		targetList[i].classList.remove('show-list');
	}
}

//항목명이 길어질 경우 처리
const subject = document.querySelectorAll('.subject-text');

for(i = 0; i < subject.length; i++){
	text = subject[i].innerHTML;
	if(text.length >= 5){
		text_ = text.substr(0, 5);
		subject[i].innerHTML = text_ + "..";
	}
}

//마지막 채팅 메세지가 길어질 경우 처리
const subjectChat = document.querySelectorAll('.item-person-chat-content');

for(i = 0; i < subjectChat.length; i++){
	text = subjectChat[i].innerHTML;
	if(text.length >= 13){
		text_ = text.substr(0, 13);
		subjectChat[i].innerHTML = text_ + "..";
	}
}
