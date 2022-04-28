<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="multi.fclass.iMint.security.dao.ISecurityDAO" %>
<%@ page import="multi.fclass.iMint.member.dto.Role" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>iMint :: 회원가입</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/member_basic.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/member_register.css">
	
</head>

<body>
	<div class="container-all">
 	<h3>회원가입</h3>	
 	<hr>
	<br>
	<div class = "container">
 	<form id = "change_url">
 		<div class="item">닉네임 <input type = text name = "mbNick" id = "mbNick" value = ${memberDTO.mbNick } placeholder = ${memberDTO.mbNick } class = "memberInfo"> <input type="button" id = "nick_btn" class="btn btn-primary buttons" value = "중복확인"><p id = "nickappend"></p>
 		</div>
 		<div class="item">이메일 <input type = text name = "mbEmail" id = "mbEmail" value = ${memberDTO.mbEmail } class = "memberInfo"><input type="button" id = "email_btn" class="btn btn-primary buttons" value = "이메일 확인"><p id = "emailappend"></p>
 		</div>
 		<br>
        <c:if test="${memberDTO.mbRole eq 'UN_CHILD'}"> 
	 		<div class="item" id = "interest">관심사 <select name = "mbInterest" id = "mbInterest" class = "memberInfo"
					aria-label="Floating label select example">
				<option value="문구" selected>문구</option>
				<option value="완구">완구</option>
				<option value="도서">도서</option>
				<option value="교구">교구</option>
				<option value="의류">의류</option>
				<option value="가방,신발,잡화">가방,신발,잡화</option>
				<option value="기타">기타</option>
				</select> 		
	 		</div>
 		</c:if>
 		<br>
 		<input type = hidden name = "mbId" id = "mbId" value = ${memberDTO.mbId }>
 		<input type = hidden name = "mbRole" id = "mbRole" value = ${memberDTO.mbRole }>
 		<div class="register-container">
    		<input type = "button" id = "register_btn" value = "회원가입" class="btn btn-primary item buttons">
    	</div>
	</form>
	</div>
	</div>
	<br>
	<br>
	<jsp:include page="../include/footer.jsp" flush="false"/>
	<jsp:include page="../libs/libsScript.jsp" flush="false" /> 

	<script src="/static/js/member/member_register.js"></script>
	
</body>

</html>