let goodsId = undefined;

function startAjax(goodsId) {
    $.ajax({
        url: `/goods/detail-images?goodsId=${goodsId}`,
        type: "GET",
        success: function (result, jqxHR) {
            console.log(result);
        },
        error: function (error) {
            //서버오류 500  찾는 자료없음 404  권한없음  401
            if (error.status == 404) {
                swal('찾는 자료가 없습니다', '', 'error');
            } else if (error.status == 401) {
                swal('접근 권한이 없습니다', '', 'error');
            } else if (error.status == 500) {
                swal('서버 오류 관리자에게 문의 하세요', '', 'error');
            }
        }
    })
}

function carouselReady(goodsId) {
    startAjax(goodsId);
}
