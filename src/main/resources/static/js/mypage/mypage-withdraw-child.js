const withdrawBtnChild = document.querySelector('#withdraw-complete-child');
console.log(withdrawBtnChild);
const withdrawFormChild = document.querySelector('#withdraw-form-child');

function withdrawChild(){
	swal({
  title: "정말 탈퇴할까요?",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
    swal("탈퇴되었습니다.", {
      icon: "success",
    });
    withdrawFormChild.action = "/mypage/withdraw";
    withdrawFormChild.method = "POST";
    withdrawFormChild.submit();
  } 
});
}

withdrawBtnChild.addEventListener('click', withdrawChild);