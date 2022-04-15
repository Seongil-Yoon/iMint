<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="multi.fclass.iMint.security.dao.IUserDAO" %>
<%@ page import="multi.fclass.iMint.security.dto.Role" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<jsp:include page="../include/header.jsp" flush="false" />

 	<form action = "/register/guard" method = "post">
 	
<!--  	출생년도에 따라서 성인, 아이 구분해서 js로 버튼 만들어야 한다  -->
 	
 		<div>닉네임<br>
	 		<input type = text id = "mbNick" value = ${user.mbNick }>
 		</div>
 		<div>이메일<br>
	 		<input type = text id = "mbEmail" value = ${user.mbEmail }>
 		</div>
 		<div>관심사<br> <!-- 드롭박스로 바꾸기  -->
	 		<input type = text id = "mbInterest">
 		</div>
 		
 		<input type = hidden id = "mbId" value = ${user.mbId }>
 		<input type = hidden id = "mbRole" value = ${user.mbRole }>

<!--  		보호자, 아이 로직이 달라 주의  -->
 		<button id = "location_btn">내 동네 설정</button>
 		
 		
 	</form>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/register.js"></script>
</body>
	<!-- 임시  -->

<script type="text/javascript">
	
	var mbId = $("#mbId").val();
	if (mbId == "ROLE_uncerti_CHILD"){ // 아이인 경우
		$("#location_btn").attr("id", "connect_btn"); // 회원가입 버튼 말고 연동으로 버튼 변경 
		$("#connect_btn").html("보호자 계정 연동");
		
	}
	
	$("#connect_btn").on('click', function(){
		 $.ajax({
			url: "/register/connect", 
			data: {
				'mbRole':$("#mbRole").val(), // val 가 어떻게 나올지 확인 필요 
				'mbId':$("#mbId").val(),
				'mbNick':$("#mbNick").val(), 
				'mbEmail':$("#mbEmail").val(),
				'mbInterest':$("#mbInterest").val()
				},
			type: 'post',
			dataType: 'json',
				
			success: function(a) { 
				alert(a.mbEmail); 
				}
			}); 		
	)};
	
</script>


<script>
$(document).ready( function(){ 
	
	$("#location_btn").on('click', function(){
/* 		if($("#mbRole").val() == "ROLE_uncerti_GAURD") { // 보호자 
 */			$.ajax({
				url: "/mypage/location", 
				data: {
					'mbRole':$("#mbRole").val(), // val 가 어떻게 나올지 확인 필요 
					'mbId':$("#mbId").val(),
					'mbNick':$("#mbNick").val(), 
					'mbEmail':$("#mbEmail").val(),
					'mbInterest':$("#mbInterest").val()
					},
				type: 'post',
				dataType: 'json',
				
				success: function(a) { 
					alert(a.mbEmail); 
				}
			}); 
/* 		} // if end  */

	});
});

</script>


</html>