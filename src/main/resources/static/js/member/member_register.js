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
//			alert(JSON.stringify(response.nickcheck));
			if (response.result == "ok") {
				$("#nickappend").html('<p style = color: black>사용가능한 닉네임입니다.</p>');
//				alert("사용 가능한 닉네임입니다.");

				$("#change_url").attr("action", "/register/3");
				$("#change_url").attr("method", "post");
				$("#register_btn").unbind(); // 회원가입 가능
			} // if end 
			else {
				$("#nickappend").html('<p style = color: red>다른 사용자가 이미 사용중인 닉네임입니다.</p>');
				$("#register_btn").preventDefault(); // 회원가입 불가 
			} // else end
		} // success end 	
	}); // ajax end
});
