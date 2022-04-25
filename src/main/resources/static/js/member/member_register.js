var ajax_check = "";

/* ajax로 닉네임 중복확인 */
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
				$("#nickappend").html('<p style = "color: black;">사용가능한 닉네임입니다.</p>');

				$("#change_url").attr("action", "/register/3");
				$("#change_url").attr("method", "post");
				$("#register_btn").attr("type", "submit");
//				$("#register_btn").unbind(); // 회원가입 가능
			} // if end 
			else {
				$("#nickappend").html('<p style = "color: red;">다른 사용자가 이미 사용중인 닉네임입니다.</p>');
			}
		} // success end 	
	}); // ajax end
});

$("#register_btn").on("click",function(){
	if(ajax_check == ""){ // 회원가입 불가
		alert("닉네임 중복확인부터 진행해주세요.");
	}
});