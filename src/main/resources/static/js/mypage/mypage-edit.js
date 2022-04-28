var ajax_check = "";

/* ajax로 닉네임 중복확인 */
//보호자
$("#nickname-check-guard").on('click', function() {
	var mbNick = $("#nickname-update-guard").val();
	console.log("checking");
	console.log(mbNick);
	$.ajax({
		url: "/edit/nickname",
		type: 'get',
		data: {
			'nickcheck': $("#nickname-update-guard").val(),
			'mbId': $("#mbId").val()
		},
		dataType: "json",

		success: function(response) { /* 중복확인 결과  */
			if (response.result == "ok") {
				console.log("ok")
				ajax_check = "ok";
				$("#nickappend").html('<p style = "color: green;">사용가능한 닉네임입니다.</p>');
			} // if end 
			else if(response.result == "blank"){
				$("#nickappend").html('<p style = "color: red;">변경할 닉네임을 입력해 주세요.</p>');
			}
			
			else {$("#nickappend").html('<p style = "color: red;">다른 사용자가 이미 사용중인 닉네임입니다.</p>');
			}
		}, // success end 
		error:function(){
			alrert("에러");
		}	
	});// ajax end
});

//아이
$("#nickname-check-child").on('click', function() {
	var mbNick = $("#nickname-update-child").val();
	console.log("checking");
	console.log(mbNick);
	$.ajax({
		url: "/edit/nickname",
		type: 'get',
		data: {
			'nickcheck': $("#nickname-update-child").val(),
			'mbId': $("#mbId").val()
		},
		dataType: "json",

		success: function(response) { /* 중복확인 결과  */
			if (response.result == "ok") {
				console.log("ok")
				ajax_check = "ok";
				//$("#arrow-box-nick").text('사용가능한 닉네임입니다.');
				//$("#arrow-box-nick").css("display", "block");
				$("#nickappend").html('<p style = "color: green;">사용가능한 닉네임입니다.</p>');
				
			} // if end 
			else if(response.result == "blank"){
				//$("#arrow-box-nick").text('변경할 닉네임을 입력해 주세요.');
				//$("#arrow-box-nick").css("display", "block");
				$("#nickappend").html('<p style = "color: red;">변경할 닉네임을 입력해 주세요.</p>');
			}
			
			else {
				//$("#arrow-box-nick").text('이미 사용중인 닉네임입니다.');
				//$("#arrow-box-nick").css("display", "block");
				$("#nickappend").html('<p style = "color: red;">이미 사용중인 닉네임입니다.</p>');
				
			}
		}, // success end 
		error:function(){
			alrert("에러");
		}	
	});// ajax end
});