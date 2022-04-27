let container = undefined,
	modal = undefined,
	containerWidth = undefined,
	goodsActionbar = undefined;

let goodsTitle = $("#goodsTitle").val();
let goodsId = $("#goodsId").val();
let myId = $("#buyerId").val();
let goodsPrice = $("#goodsPrice").html();
let crtLocation = $("#userLocation").val();

//on load html 이미지나 자바스크립트 링크가 다오고 실행됨
$(window).on('load', function () {
	if (crtLocation == undefined || crtLocation == "") {
		crtLocation = localStorage.getItem("crtLocation");
		$(".location-text").html(crtLocation);
		$("#userLocation").val(crtLocation);
	}
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

function deleteHandler() {
	$(document).on("click", "#deleteBtn", function (event) {
		goodsTitle = $("#goodsTitle").val();
		goodsId = $("#goodsId").val();
		swal({
				title: `${goodsTitle}을 삭제하시겠습니까?`,
				text: "",
				icon: "warning",
				buttons: true,
				dangerMode: true,
			})
			.then((e) => {
				if (e) { //true
					//이벤트 부모태그인 li의 value값 가져오기
					$.ajax({
						url: `/goods/delete?goodsId=${goodsId}`,
						type: "GET", //데이터 전달방식
						success: function (result, textStatus, jqxHR) {
							if (jqxHR.status == 200) {
								swal({
									title: "상품이 삭제되었습니다",
									text: "",
									icon: "info",
									type: "success",
									timer: 1.1 * 1000
								}).then(() => {
									location.href = "/main";
								})
							}
						},
						error: function (error) {
							//서버오류 500  찾는 자료없음 404  권한없음  401
							if (error.status == 404) {
								swal('찾는 자료가 없습니다', '', 'error');
							} else if (error.status == 401) {
								swal('삭제 권한이 없습니다', '', 'error');
							} else if (error.status == 500) {
								swal('서버 오류 관리자에게 문의 하세요', '', 'error');
							}
						}
					}) //end of ajax
				} else {
					// swal("취소하였습니다.");
				}
			});
	}); //end of click
}

function wishHandler() {
	$("#wishBtn").on("click", function () {
		$.ajax({
			url: `/wishlist/add?myId=${myId}&goodsId=${goodsId}`,
			type: "POST", //데이터 전달방식
			dataType: "json", //json 으로 받기
			success: function (response, textStatus, jqxHR) {
				if (response.result == "fail") {
					removeWish();
				} else {
					swal({
						title: "관심 등록되었습니다",
						text: "",
						icon: "info",
						type: "success",
						timer: 1.1 * 1000
					}).then(() => {
						location.href = "";
					})
				}
			},
			error: function (error) {
				//서버오류 500  찾는 자료없음 404  권한없음  401
				if (error.status == 404) {
					swal('찾는 자료가 없습니다', '', 'error');
				} else if (error.status == 401) {
					swal('삭제 권한이 없습니다', '', 'error');
				} else if (error.status == 500) {
					swal('서버 오류 관리자에게 문의 하세요', '', 'error');
				}
			}
		}) //end of ajax
	})

	function removeWish() {
		$.ajax({
			url: `/wishlist/remove?myId=${myId}&goodsId=${goodsId}`,
			type: "POST", //데이터 전달방식
			dataType: "json", //json 으로 받기
			success: function (result, textStatus, jqxHR) {
				swal({
					title: "관심 취소하였습니다",
					text: "",
					icon: "warning",
					type: "success",
					timer: 1.1 * 1000
				}).then(() => {
					location.href = "";
				})
			},
			error: function (error) {
				//서버오류 500  찾는 자료없음 404  권한없음  401
				if (error.status == 404) {
					swal('찾는 자료가 없습니다', '', 'error');
				} else if (error.status == 401) {
					swal('삭제 권한이 없습니다', '', 'error');
				} else if (error.status == 500) {
					swal('서버 오류 관리자에게 문의 하세요', '', 'error');
				}
			}
		}) //end of ajax
	}
}

function modifyHandler() {
	$("#modifyBtn").on("click", function () {
		swal({
			title: `${goodsTitle}을 수정하시겠습니까?`,
			text: "",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		}).then((e) => {
			if (e) {
				location.href = `/goods/modify?goodsId=${goodsId}`;
			} else {
				// swal("취소하였습니다.");
			}
		});
	})
}

//숫자 가격화 함수
function fomatPrice(strNum) {
	return strNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); // 세자리 콤마
}

function main() {
	$("#goodsPrice").html(fomatPrice(goodsPrice));
	modifyHandler();
	deleteHandler();
	wishHandler();
	let regDate = $("#timeForToday").text();
	$("#timeForToday").text(timeForToday(regDate));




}
main();