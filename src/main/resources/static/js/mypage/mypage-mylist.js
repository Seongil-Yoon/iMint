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

function changeLink(e){
	const ItemToChange = document.querySelector('#link-price-out-h6');
	//const ItemToAdd = document.querySelector('#link-price-out');
	ItemToChange.parentNode.removeChild("h6");
	ItemToAdd.innerHTML = "<h6 id='link-price-out-h6'>지출</h6>";
}

/*function removeLink(){
	tabContentItems.forEach(item => item.classList.remove('link'))
}*/

tab3.addEventListener('click', changeLink);
//tab3.forEach(item => item.addEventListener('click', changeLink));