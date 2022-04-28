const submitBtn = document.querySelector("#goodsSubmit");
const uploadPopupBtn = document.querySelector("#upload-popup-btn");
const inputElement = document.querySelector('input[type="file"]');
const uploadDoneBtn = document.querySelector("#uploadDone");
let popupOverlay = document.querySelector(".popup-overlay");
let uploadPopup = document.querySelector("#uploadPopup");
let startAjax = undefined;

let suggestible = undefined;
let files = 0;
let thumbnailFile = undefined;
let fileBuffer = []; //formdData에 날릴 배열

let goodsDTO = {
    mbId: undefined,
    mbNick: undefined,
    goodsTitle: undefined,
    goodsContent: undefined,
    goodsPrice: undefined,
    goodsCategory: undefined,
    goodsSuggestible: undefined,
    goodsLocation: undefined
}
let formData = new FormData();

function goodsWrite() {

    goodsDTO = {
        sellerId: $("input[name='mbId']").val(),
        sellerNick: $("input[name='mbNick']").val(),
        goodsTitle: $("input[name='goodsTitle']").val(),
        goodsContent: $("textarea[name='goodsContent']").val(),
        goodsPrice: $("input[name='goodsPrice']").val(),
        goodsCategory: $("select[name='goodsCategory']").val(),
        goodsSuggestible: $("input[name='suggestible']:checked").val(),
        goodsLocation: $("input[name='goodsLocation']").val()
    }
    console.log(goodsDTO);

    formData.append("GoodsDTO", new Blob([JSON.stringify(goodsDTO)], {
        type: "application/json"
    }));

    (function () {
        // formData.append("files", thumbnailFile);
        for (i = 0; i < fileBuffer.length; i++) {
            formData.append("files", fileBuffer[i]);
        }
    })();

    startAjax = function () {
        $.ajax({
            url: "/goods/write",
            type: "post",
            data: formData,
            dataType: false,
            processData: false,
            contentType: false,
            success: function (result, textStatus, jqxHR) {
                if (jqxHR.status == 200 || jqxHR.status == 201) {
                    swal('', '상품을 등록 하였습니다', 'success');
                    //등록 성공하면 내가등록한 게시글화면으로 이동
                    setTimeout(function () {
                        location.href = `/goods/detail?goodsId=${result.goodsId}`;
                    }, 2000);
                }
            },
            error: function (error) {
                //서버오류 500  찾는 자료없음 404  권한없음  401
                if (error.status == 404) {
                    swal('찾는 자료가 없습니다', '', 'error');

                } else if (error.status == 401) {
                    swal('유효하지 않은 인증입니다', '', 'error');

                } else if (error.status == 403) {
                    swal('접근 권한이 없습니다', '', 'error');

                } else if (error.status == 500) {
                    swal('서버 오류 관리자에게 문의 하세요', '', 'error');
                }
            }
        });
    }

    if (!(goodsDTO.goodsTitle == "" || goodsDTO.goodsContent == "" || goodsDTO.goodsPrice == "")) {
        if (fileBuffer.length > 0) {
            startAjax();
        } else {
            swal({
                title: `사진을 한개도 올리시지 않았습니다`,
                text: "이대로 올릴까요?",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            }).then((e) => {
                if (e) {
                    startAjax();
                }
            });
        }
    } else {
        swal('입력되지 않은 항목이 있습니다', '', 'error');
    }

}


function fileUpload() {
    popupOverlay.style.display = "block";
    popupOverlay.style.opacity = 1;
    uploadPopup.style.display = 'block';

    function popupHidden() {
        popupOverlay.style.display = "none";
        popupOverlay.style.opacity = 0;
        uploadPopup.style.display = 'none';
    }

    uploadDoneBtn.addEventListener("click", popupHidden);
}

// Register the plugin with FilePond
function filePond() {

    FilePond.registerPlugin(
        // FilePondPluginFileMetadata,
        // FilePondPluginImageCrop,
        FilePondPluginImagePreview,
        FilePondPluginFileEncode
    );
    // Create the FilePond instance
    const pond = FilePond.create(inputElement, {
        allowMultiple: true,
        allowReorder: true
    });

    const filepondRoot = document.querySelector('.filepond--root');

    filepondRoot.addEventListener('FilePond:updatefiles', e => {
        fileBuffer.splice(0, fileBuffer.length);

        for (let i = 0; i < e.detail.items.length; i++) {
            fileBuffer[i] = dataURLtoFile(e.detail.items[i].getFileEncodeDataURL(), e.detail.items[i].filename);
        }
    });
    filepondRoot.addEventListener('FilePond:reorderfiles', e => {
        fileBuffer.splice(0, fileBuffer.length);

        for (let i = 0; i < e.detail.items.length; i++) {
            fileBuffer[i] = dataURLtoFile(e.detail.items[i].getFileEncodeDataURL(), e.detail.items[i].filename);
        }
    });
}
//base64 to File객체
const dataURLtoFile = (dataurl, fileName) => {

    let arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

    while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], fileName, {
        type: mime
    });
}


function main() {
    // $("#thumbnailFile").on("change", function () {
    //     thumbnailFile = this.files[0];
    //     console.log(thumbnailFile);
    //     $("#thumbnailFileName").val(`파일명 : ${thumbnailFile.name}`);
    // })

    filePond();
    uploadPopupBtn.addEventListener('click', fileUpload);
    submitBtn.addEventListener('click', goodsWrite); //비동기 폼전송 부분

   
}

main();