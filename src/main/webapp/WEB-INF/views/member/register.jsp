<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="multi.fclass.iMint.security.dao.ISecurityDAO" %>
<%@ page import="multi.fclass.iMint.member.dto.Role" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<jsp:include page="../include/header.jsp" flush="false" />

 	<h3>회원가입(SNS연동)</h3>	
 	<hr>
 	<h6>${err}</h6>
	
	<br>
 	<form id = "change_url" action="/register" method = "post">
 		<div>닉네임<br>
	 		<input type = text name = "mbNick" id = "mbNick" value = ${memberDTO.mbNick }>
 		</div>
 		<div>이메일<br>
	 		<input type = text name = "mbEmail" id = "mbEmail" value = ${memberDTO.mbEmail }>
 		</div>
 		<div id = "interest">관심사<br> <!-- 드롭박스로 바꾸기  -->
	 		<input type = text name = "mbInterest" id = "mbInterest">
 		</div>
 		
 		<input type = hidden name = "mbId" id = "mbId" value = ${memberDTO.mbId }>
 		<input type = hidden name = "mbRole" id = "mbRole" value = ${memberDTO.mbRole }>
    	<button id = "register_btn">회원가입</button>
	</form>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />

	<script src="/static/js/register.js"></script>
	
	
</body>

</html>