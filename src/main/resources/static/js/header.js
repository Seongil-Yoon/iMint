
function resizeArrowBox(){
	//음성
	const voiceBtn = document.querySelector('#search-voice');
	console.log(voiceBtn);
    const voiceBtnLeft = voiceBtn.getBoundingClientRect().left;
    console.log(voiceBtnLeft);
    
    const voiceArrowBox = document.querySelector('#arrow-voice');
    console.log(voiceArrowBox);
    voiceArrowBox.style.marginLeft = voiceBtnLeft - 38 + 'px';
    
    //텍스트
    const textBtn = document.querySelector('#search-text');
	console.log(voiceBtn);
    const textBtnLeft = textBtn.getBoundingClientRect().left;
    console.log(textBtnLeft);
    
    const textArrowBox = document.querySelector('#arrow-text');
    console.log(voiceArrowBox);
    textArrowBox.style.marginLeft = voiceBtnLeft - 5 + 'px';
    }

window.addEventListener('resize', resizeArrowBox);
window.addEventListener('load', resizeArrowBox);