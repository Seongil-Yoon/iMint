//레벨 변환

var level_default = parseInt(document.getElementById('leveling').innerText);
const levelbtn = document.getElementById('leveling');

if(level_default <= 0){
	var level = 0;
	} else if(level_default <= 100){
		level = level_default / 10;
	} else {
		level = 10;
	}

levelbtn.innerText = "레벨 " + level;