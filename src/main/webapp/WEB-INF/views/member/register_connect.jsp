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
 	
 	<h3>보호자 계정 연동하기</h3>	
 	<hr>
 
 	<form action = "/" method = "post">
 		<input type = text name = "mbLocationOrGuard" placeholder = "보호자 닉네임을 입력해주세요">
 		<input type = text name = "guardPin" placeholder = "보호자 계정의 8자리 인증번호를 입력해주세요.(보호자 계정의 마이페이지에서 확인할 수 있어요)"> <!-- 보호자 닉네임 == Pin인지 확인하기  -->
 		<button>회원가입 완료하기</button>	
	</form>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/register_connect.js"></script>
	
</body>
</html>