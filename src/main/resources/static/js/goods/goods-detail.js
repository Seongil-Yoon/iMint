let container = undefined,
	modal = undefined,
	containerWidth = undefined,
	goodsActionbar = undefined;


//on load html 이미지나 자바스크립트 링크가 다오고 실행됨
$(window).on('load', function () {
	container = document.querySelector(".container");
	modal = new bootstrap.Modal(container);
	containerWidth = modal._element.clientWidth; //
	$(window).resize(function () { //창크기변화 감지 이벤트
		//반응형으로 변하는 컨테이너 크기를 액션바의 width로 값할당
		containerWidth = modal._element.clientWidth;
		$("#js-goods-action").css('width', containerWidth);
	})
});

function main() {
	console.log(containerWidth);
}
main();