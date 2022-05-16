// 차단
 
$("#block_btn").on('click', function() {
	$.ajax({
		url: "/block",
		type: "post",
		data: {
			"blockMbId": blockMbId
		},
		dataType: "json",

		success: function(response) { /* 결과 */		
			if(response.result === "block") {
				alert("차단 완료하였습니다.");
			}
		} // success end 
	}); // ajax end 
})

// 차단해제 
$("#unblock_btn").on('click', function() {
	$.ajax({
		url: "/unblock",
		type: "post",
		data: {
			"unblockMbId": unblockMbId
		},
		dataType: "json",

		success: function(response) { /* 결과 */		
			if(response.result === "unblock") {
				alert("차단해제 완료하였습니다.");
			}
		} // success end 
	}); // ajax end 
})