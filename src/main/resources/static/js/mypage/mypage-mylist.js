// 상단탭 선택시 baby-mylist.jsp 내에서 다른 컨테이너가 나오도록 함

const tabItems = document.querySelectorAll('.tab-item');
const tabContentItems = document.querySelectorAll('.tab-content-item');
//Select tab content item
function selectItem(e) {
    removeShow();
    removeSelected();
    // Grab content item from DOM
    const tabContentItem = document.querySelector(`#${this.id}-content`);
    const tabItem = document.querySelector(`#${this.id}`);
    // Add show class
    tabContentItem.classList.add('show');
    tabItem.classList.add('selected');
}

function removeShow(){
    tabContentItems.forEach(item => item.classList.remove('show'))
}

function removeSelected(){
    tabItems.forEach(item => item.classList.remove('selected'))
}

tabItems.forEach(item => item.addEventListener('click', selectItem));


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


