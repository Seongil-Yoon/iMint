var ajax_check = "";

// ajax로 닉네임 중복확인
$("#nick_btn").on('click', function() {
	var mbNick = $("#mbNick").val();
	$.ajax({
		url: "/register/nickname",
		type: 'get',
		data: {
			'nickcheck': $("#mbNick").val(),
			'mbId': $("#mbId").val()
		},
		dataType: "json",

		success: function(response) { /* 중복확인 결과  */
			if (response.result == "ok") {
				ajax_check = "ok";
				$("#nickappend").html('<p style = "color: green;">사용가능한 닉네임입니다.</p>');
			}
			else if(response.result == "blank"){
				$("#nickappend").html('<p style = "color: red;">변경할 닉네임을 입력해 주세요.</p>');
			}
			else {
				$("#nickappend").html('<p style = "color: red;">다른 사용자가 이미 사용중인 닉네임입니다.</p>');
			}
		} // success end 	
	}); // ajax end
});

var email_check = "";
// 이메일 유효성 검사 
$("#email_btn").on("click", function(){
		var email = $("#mbEmail").val();
		var exptext = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

			if(exptext.test(email)===false){
			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			
				$("#emailappend").html('<p style = "color: red;">정확한 이메일을 입력해주세요.</p>');
				$("#mbEmail").focus();
			}
			else{
				$("#emailappend").html('<p style = "color: green;">유효한 이메일입니다.</p>');
				email_check = "ok";
				if(ajax_check !== "" && email_check !== ""){
					$("#change_url").attr("action", "/register/3");
					$("#change_url").attr("method", "post");
					$("#register_btn").attr("type", "submit");
				}
			}
});	

// 닉네임, 이메일 확인 안되었으면 회원가입 불가 
$("#register_btn").on("click",function(){
	if(ajax_check == "" || email_check == ""){ // 회원가입 불가
		alert("닉네임 중복확인, 이메일 확인부터 진행해주세요.");
	}
});