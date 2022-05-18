//레벨 변환

var level_default = parseInt(document.getElementById('leveling').innerText) - 15;
const levelbtn = document.getElementById('leveling');

console.log(levelbtn);

if(level_default <= 0){
	var level = 0;
	} else if(level_default <= 100){
		level = level_default / 10
		if(level < 3){
			levelbtn.style.backgroundColor = 'rgb(192, 192, 192)';
			
		} else if(level < 5){
			levelbtn.style.backgroundColor = 'rgba(202,255,133,1)';
	
		} else if(level < 7){
			levelbtn.style.backgroundColor = 'rgba(240,235,113,1)';
			
		} else if(level < 9){
			levelbtn.style.backgroundColor = 'rgba(166,193,238,1)';
			
		} else if(level < 10){
			levelbtn.style.backgroundColor = 'rgba(251,194,235,1)';
		}
		
	} else {
		level = "MAX";
		levelbtn.style.background = 'linear-gradient(90deg, rgba(251,194,235,1) 0%, rgba(251,194,235,1) 0%, rgba(166,193,238,1) 100%)';
	}

levelbtn.innerText = "레벨 " + level;