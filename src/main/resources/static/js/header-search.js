const voiceDoneAndSearch = document.querySelector("#voiceDoneAndSearch");
let voicePopupOverlay = document.querySelector(".voicepopup-overlay");
let voicePopup = document.querySelector("#voicePopup");

function goodsSearch() {
    const goodsCategoryVal = "all";
    $("#search-text").on("click", function (e) {
        let searchOption = $("select[name='searchOption']").val();
        let keyword = $("input[name='keyword']").val();
        location.href = `/main?goodsCategory=${goodsCategoryVal}&searchOption=${searchOption}&keyword=${keyword}`
    })
}



function voiceQues() {
    let recordBtn = document.getElementById("record");
    let stopBtn = document.getElementById("stop");
    let sound = document.getElementById("sound");
    let constraints = undefined;
    let mediaRecorder = undefined;
    
    recordBtn.style.borderColor = "#CDF0EA";
    recordBtn.style.color = "#CDF0EA";

    if (navigator.mediaDevices) { //true이면 녹음기, 카메라 지원 브라우저
        console.log("지원가능");
        constraints = {
            audio: true
        }
    }
    let chunks = []; //녹음데이터 임시저장
    navigator.mediaDevices.getUserMedia(constraints)
        .then(function (stream) {
            mediaRecorder = new MediaRecorder(stream);
            recordBtn.onclick = function () {
                mediaRecorder.start();
                recordBtn.style.color = "";
                recordBtn.style.background = "";
            }
            stopBtn.onclick = function () {
                mediaRecorder.stop();
                recordBtn.style.color = "";
                recordBtn.style.background = "";
            }


            //녹음 시작상태 후 chunks에 데이터 저장
            mediaRecorder.ondataavailable = function (e) {
                chunks.push(e.data);
            }
            //녹음 정지상태
            mediaRecorder.onstop = function (e) {
                //audio태그 추가
                let audio = document.createElement("audio");
                audio.setAttribute("controls", "");
                audio.controls = true;
                try {
                    if (sound.querySelector("audio") !== null) {
                        sound.removeChild(audio);
                    } else {
                        sound.appendChild(audio);
                    }
                } catch (error) {}

                console.log(chunks);
                //녹음 데이터 가져오기
                let blob = new Blob(chunks, {
                    type: "audio/mp3"
                });
                let mp3url = URL.createObjectURL(blob);
                audio.src = mp3url;

                //다음 녹음 위해 chunks 초기화
                chunks = [];

                //파일명 - 현재시각 + .mp3 => (CSRservice)text
                // ajax로 스프링에 mp3파일 전송(multipart/form-data)
                let formData = new FormData(); // <form>과 동일
                formData.append("file1", blob, "a.mp3");
                //<input type=file name="file1">
                $.ajax({ //파일 업로드
                    url: "/mp3upload",
                    data: formData,
                    type: "post",
                    processData: false, //파일만 보내겟다
                    contentType: false, //파일만 보내겟다
                    success: function (result) {
                        console.log(result);
                        if (result != undefined) {
                            $.ajax({ //mp3파일이 txt로 변환된 파일을 다운로드 
                                url: "/chatbotstt",
                                data: {
                                    "filename": result
                                },
                                type: "get",
                                dataType: "json",
                                success: function (server) {
                                    console.log(server.text);
                                    $("#voiceResult").val(server.text);
                                }
                            }); //end of inner ajax
                        }
                    }
                }); //end of outter ajax
            } //end of onstop

        })
        .catch(function (err) {
            console.log("녹음기 문제 감지", err);
        });
}

function voicePopupView() {
    voicePopupOverlay.style.display = "block";
    voicePopupOverlay.style.opacity = 1;
    voicePopup.style.display = 'block';

    function popupHidden() {
        const goodsCategoryVal = "all";
        let searchOption = $("select[name='searchOption']").val();
        // let keyword = $("input[name='keyword']").val();
        let keyword = $("#voiceResult").val();
        location.href = `/main?goodsCategory=${goodsCategoryVal}&searchOption=${searchOption}&keyword=${keyword}`

        voicePopupOverlay.style.display = "none";
        voicePopupOverlay.style.opacity = 0;
        voicePopup.style.display = 'none';
    }

    voiceDoneAndSearch.addEventListener("click", popupHidden);
}

function goodsVoiceSearch() {
    // STT응답되면 goodsSearch() 호출
    $("#search-voice").on("click", function (e) {
        voicePopupView();
        voiceQues();
    });

}

function main() {

    goodsSearch();
    goodsVoiceSearch();
}
main();