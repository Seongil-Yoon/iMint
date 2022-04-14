let container = undefined,
	modal = undefined,
	containerWidth = undefined,
	goodsActionbar = undefined;


//on load html 이미지나 자바스크립트 링크가 다오고 실행됨
$(window).on('load', function () {
	container = document.querySelector(".container");
	modal = new bootstrap.Modal(container);
	containerWidth = modal._element.clientWidth;
	$("#js-goods-action").css('width', containerWidth - 14)
	$(window).resize(function () { //창크기변화 감지 이벤트
		//반응형으로 변하는 컨테이너 크기를 액션바의 width로 값할당
		containerWidth = modal._element.clientWidth;
		$("#js-goods-action").css('width', containerWidth - 14);
	})
});

//시간차이 계산 함수
function timeForToday(value) {
	const today = new Date();
	const timeValue = new Date(value);
	// const timeValue = new Date(value[0], value[1] - 1, value[2], value[3], value[4], value[5], 0);

	//시간은 1970-01-01을 기준으로 한 에포크시간.
	const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
	if (betweenTime < 1) return '방금전 작성';
	if (betweenTime < 60) {
		return `${betweenTime}분전 작성`;
	}

	const betweenTimeHour = Math.floor(betweenTime / 60);
	if (betweenTimeHour < 24) {
		return `${betweenTimeHour}시간전 작성`;
	}

	const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
	if (betweenTimeDay < 365) {
		if (betweenTimeDay == 0) {
			return `오늘 작성`;
		}
		return `${betweenTimeDay}일전 작성`;
	}

	return `${Math.floor(betweenTimeDay / 365)}년전 작성`;
}

function main() {
	let regDate = $("#timeForToday").text();
	$("#timeForToday").text(timeForToday(regDate));

	


}
main();