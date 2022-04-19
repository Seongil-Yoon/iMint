let goodsId = undefined;

function startAjax(goodsId) {
    $.ajax({
        url: `/goods/detail-images?goodsId=${goodsId}`,
        type: "GET",
        success: function (result, jqxHR) {
            console.log(result);
            fileTocarousel(result);
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

function fileTocarousel(result) {
    for (var i = 0; i < result.length; i++) {
        let itemHtml = "";
        let indicaHtml = "";
        if (i == 0) {
            itemHtml += `
            <div class="carousel-item active">
                <img src=${result[i].goodsImagesPath} class="d-block w-100" alt="..">
            </div>
            `;
            indicaHtml += `
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to=${i} class="active" aria-current="true" aria-label=${i+1}>
            </button>
            `;
        } else {
            itemHtml += `
            <div class="carousel-item">
                <img src=${result[i].goodsImagesPath} class="d-block w-100" alt="..">
            </div>
            `;
            indicaHtml += `
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to=${i} aria-label=${i+1}>
            </button>
            `
        }
        $(".carousel-inner").append(itemHtml);
        $(".carousel-indicators").append(indicaHtml);
    }
}

function carouselReady(goodsId) {
    startAjax(goodsId);
}