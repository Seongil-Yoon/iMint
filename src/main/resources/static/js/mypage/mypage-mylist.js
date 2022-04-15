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
