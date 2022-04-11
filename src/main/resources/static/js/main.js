let goodsId = 20000; //게시글 6개씩 불러 오기 위해 look_num 값넣는 변수 초기값 은  2000000000
let mainScrollTime = true; //스크롤 중복 방지 변수
let end = true //게시글 없을 경우 데이터 가져오지 않는 변수

//on load html 이미지나 자바스크립트 링크가 다오고 실행됨
$(window).on('load', function () {
	start(); //처음 4개 출력
	$(window).scroll(function () { //스크롤 감지 이벤트

		let scroll = $(document).scrollTop(); //현재 스크롤 값
		let documentHeight = $(document).height(); //문서 전체높이
		let windowHeight = window.innerHeight; //윈도우 높이
		//윈도우 높이에 스크롤값을 계속더해서 문서 전체 길이에서 100 px 앞에 스크롤이 왔을때 데이터 불러옴
		if ((windowHeight + scroll) >= documentHeight - 100) {
			if (mainScrollTime == true && end == true) {
				start();
			}
		}
	})
});


function start() {
	//무한 스크롤 중복 방지
	mainScrollTime = false;

	$.ajax({
		url: "/goods-list/" + goodsId,
		type: "GET",
		dataType: "json", //json 으로 받기
		success: function (result) {

			for (var i = 0; i < result.length; i++) {
				let html = "";
				html `
					
				`;

				$("#goodsList").append(html);
			}

			//다음 게시글 6개 가져 오기 위해 마지막 게시글 기본키 값 넘겨줌
			goodsId = result[result.length - 1].goodsId;

			setTimeout(function () {
				mainScrollTime = true;
			}, 400); //스크롤 이벤트 0.2초뒤 실행 중복방지 위해

		},
		error: function (error) {
			//서버오류 500  권한없음 401  찾는내용없음 400
			if (error.status == 500) {
				swal('서버오류', '', 'error');
			} else if (error.status == 404) {
				end = false;
				//가져올 게시글이 없어서 더이상 데이터를 가져오지 않게 바꿈
			}
		}
	});
}