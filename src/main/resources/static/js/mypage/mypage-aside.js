const asideItems = document.querySelectorAll('.aside-tab-item');
console.log(asideItems);
//Select tab content item
function selectAside() {
	console.log("확인");
    removeSelected();
    // Grab content item from DOM
    
    

    const tabItem = document.querySelector(`#${this.id}`);

    tabItem.classList.add('selected');
}

function removeSelected(){
    asideItems.forEach(item => item.classList.remove('selected'))
}

window.onload = selectAside;