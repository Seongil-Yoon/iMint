const withdrawBtn = document.querySelector('#withdraw-complete-guardian');
const withdrawForm = document.querySelector('#withdraw-form');

function withdrawGuard(){
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
    withdrawForm.action = "/mypage/withdraw";
    withdrawForm.method = "POST";
    withdrawForm.submit();
  } 
});
}

withdrawBtn.addEventListener('click', withdrawGuard);