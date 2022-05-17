// 체크박스 전체선택, 전체 해제 함수
function selectAll(selectAll) {
	const checkboxes = document.getElementsByName('member_check');

	alert(checkboxes.length - 1 + "개 선택 되었습니다.");

	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked;
	})
};

// 체크박스 선택된 것들의 id를 배열에 담는 함수
function getChecked() {
	const checkedmembers = document.querySelectorAll('input[name="member_check"]:checked');
	let checked_ids = [];
	checkedmembers.forEach((id) => {
		if (id.value != 0) {
			checked_ids.push(id.value);
		}
	})
	return checked_ids;
}

// 관리자가 선택한 회원 강퇴시키기(비동기)
$("#ban_btn").on('click', function() {

	var ban_members = [];
	
	// input checkbox 에서 체크한 갯수 확인해서 0명이면 비동기 x.
	if($('input:checkbox[name=member_check]:checked').length == 0){
		alert("아무도 선택되지 않았습니다.");
	}
	// 선택한 회원이 1명 이상일 때만 비동기로 배열 전달
	else{
		$('input:checkbox[name=member_check]').each(function (index) {
			if($(this).is(":checked") == true){
				ban_members.push($(this).val()); // 체크박스에 선택된 mbId를 배열로 담기 
				
				$.ajax({ 
					url: "/admin/member/ban",
					type: "post",
				 	// 자바스크립트 배열을 ,로 연결된 String으로 json으로 자바에 전달
					data:{
						"ban_members": ban_members.join(',')
					},
					success: function(result) {
							alert("강제탈퇴 완료했습니다.");
					}
				}); // ajax end 
		    }; // if end 
		});
	}
});