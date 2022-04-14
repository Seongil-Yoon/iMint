<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<jsp:include page="../include/header.jsp" flush="false" />

<script type="text/javascript"> 
	var birthYear = prompt('출생년도를 입력하세요 ex.2001', ''); 
	var response = confirm("입력하신 출생년도를 확인해주세요: "+ birthYear); // 확인을 누르면 true를 리턴 / 취소를 누르면 false를 리턴
	if (response === true){
		window.onload = function(){
			document.getElementById('birthyear').setAttribute('value',birthYear)
		};
	}
	else {
		alert("가입 불가");
	}

</script>

 	<form action = "/register" method = "post">
 	
<!--  	출생년도에 따라서 성인, 아이 구분해서 js로 버튼 만들어야 한다  -->
 	
  		<div>출생년도<br> <!-- 인증이 없어서 그렇긴하지만.. -->
	 		<input type = text id = "birthyear" readonly = readonly>
 		</div>	 
 		<div>닉네임<br>
	 		<input type = text>
 		</div>
 		<div>이메일<br>
	 		<input type = text value = ${email } readonly = readonly>
 		</div>
 		<div>관심사<br>
	 		<input type = text>
 		</div>
 		
 	</form>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/register.js"></script>
</body>
</html>