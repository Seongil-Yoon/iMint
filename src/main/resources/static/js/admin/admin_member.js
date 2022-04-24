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