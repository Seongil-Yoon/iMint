var ajax_check = "";

/* ajax로 닉네임 중복확인 */
$("#nickname-check").on('click', function() {
	var mbNick = $("#nickname-update").val();
	console.log("checking");
	console.log(mbNick);
	$.ajax({
		url: "/edit/nickname",
		type: 'get',
		data: {
			'nickcheck': $("#nickname-update").val(),
			'mbId': $("#mbId").val()
		},
		dataType: "json",

		success: function(response) { /* 중복확인 결과  */
			if (response.result == "ok") {
				console.log("ok")
				ajax_check = "ok";
				$("#nickappend").html('<p style = "color: green;">사용가능한 닉네임입니다.</p>');
			} // if end 
			else if (response.result == "blank") {
				$("#nickappend").html('<p style = "color: red;">변경할 닉네임을 입력해 주세요.</p>');
			}

			else {
				$("#nickappend").html('<p style = "color: red;">다른 사용자가 이미 사용중인 닉네임입니다.</p>');
			}
		}, // success end 
		error: function() {
			alrert("에러");
		}
	});// ajax end
});

$("#thumbnail_delete_btn").on('click', function() {
	$.ajax({
		url: "/mypage/edit/delete/thumbnail",
		type: 'post',
		data: {
			'mbId': $("#mbId").val()
		},
		dataType: "json",

		success: function(response) {
			if (response.result == "success") {
				$("#thumbnailappend").html('<p style = "color: green;">프로필 사진을 삭제했습니다.</p>');
			} else {
				$("#thumbnailappend").html('<p style = "color: red;">프로필 사진을 삭제하지 못 했습니다.</p>');
			}
		} // success
	}); // ajax
});