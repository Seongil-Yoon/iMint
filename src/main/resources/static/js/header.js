
function resizeArrowBox(){
	//음성
	const voiceBtn = document.querySelector('#search-voice');
	
    const voiceBtnLeft = voiceBtn.getBoundingClientRect().left;
    
    
    const voiceArrowBox = document.querySelector('#arrow-voice');
    
    voiceArrowBox.style.marginLeft = voiceBtnLeft - 38 + 'px';
    
    //텍스트
    const textBtn = document.querySelector('#search-text');
	
    const textBtnLeft = textBtn.getBoundingClientRect().left;
    
    
    const textArrowBox = document.querySelector('#arrow-text');
    
    textArrowBox.style.marginLeft = voiceBtnLeft - 5 + 'px';
    }

window.addEventListener('resize', resizeArrowBox);
window.addEventListener('load', resizeArrowBox);