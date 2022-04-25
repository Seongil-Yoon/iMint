$(document).ready(function() {

//	var firArr = []; // 이중배열
	var location_arr = [];
	var regist_arr = [];
	var withdraw_arr = [];
	
	var location;
	var withdraw;
	var regist;
	
	var cnt = 0;
	
	$.ajax({
		url: "/admin/stats/member",
		type: 'post',

		success: function(response) {
			location =  JSON.stringify(response.location); // json에서 받아온 list 파싱
			location_arr = location.split(',');
			
			withdraw = JSON.stringify(response.withdraw);
			withdraw_arr = withdraw.split(',');
			
			regist = JSON.stringify(response.regist);
			regist_arr = regist.split(',');
			
		for(var i = 0; i< location_arr.length; i++){
			if( i == 0) {
				if( location_arr[0] == '[null'){
					location_arr[0] = "위치 미설정 회원";
				}
				regist_arr[i] = regist_arr[i].substr(2, regist_arr[i].length-3);
				withdraw_arr[i] = withdraw_arr[i].substr(2, withdraw_arr[i].length-3);
			}
			else if(i == location_arr.length - 1){ // 마지막이면 한 번더 문자열 자르기
				location_arr[i] = location_arr[i].substr(1, location_arr[i].length-3);
				regist_arr[i] = regist_arr[i].substr(1, regist_arr[i].length-3);
				withdraw_arr[i] = withdraw_arr[i].substr(1, withdraw_arr[i].length-3);

			}
			else{
				regist_arr[i] = regist_arr[i].substr(1, regist_arr[i].length-2);
				withdraw_arr[i] = withdraw_arr[i].substr(1, withdraw_arr[i].length-2);
				location_arr[i] = location_arr[i].substr(1, location_arr[i].length-2);
			}
		}
		} // success
	}); // ajax
	
setTimeout(function () {
	var chart = c3.generate({
	bindto: "#chart",
    data: {
	    x : 'x',
			columns: [
				['x', location_arr[0], location_arr[1], location_arr[2]], 				
				['registration', parseInt(regist_arr[0]), parseInt(regist_arr[1]), parseInt(regist_arr[2])],
				['withdraw', parseInt(withdraw_arr[0]), parseInt(withdraw_arr[1]), parseInt(withdraw_arr[2])],
		],
        groups: [
            ['registration', 'withdraw']
        ],
        type: 'bar'
    },    
    axis: {
        x: {
            type: 'category' // this needed to load string x value
    	    }
    	}
    
    }); // chart	
}, 1300);
}); // document