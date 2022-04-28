<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>iMint :: 회원가입 보호자계정 연동</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/member_basic.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/member_register_connect.css">
</head>

<body>
 	<h3>보호자 계정 연동하기</h3>	
 	<hr>
 	<div class = "container">
 	<form action = "/" method = "post">
 	<div class = "item">
 		<input type = text name = "mbLocationOrGuard" placeholder = "보호자 닉네임을 입력해주세요." >
 	</div>
 	<div class = "item">
 		<input type = text name = "guardPin" placeholder = "보호자 계정 마이페이지 내 8자리 Pin번호를 입력해주세요."> <!-- 보호자 닉네임 == Pin인지 확인하기  -->
 	</div>
 	<div class = "item">
 		<input type = submit class="btn btn-primary" value = "회원가입 완료">
 	</div>
 	<div class = "item">
		<span style = "color: red;">${err} </span>
	</div>
 	 	
	</form>
	</div>
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	
</body>
</html>