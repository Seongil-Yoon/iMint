<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>@@아기당근@@ :: 마이페이지</title>
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<jsp:include page="../../include/header.jsp" flush="false" />
 	<aside></aside>
	
	
	
	<a class="content" href="#">나의 아이민트</a><br>
	<a class="content" href="#">나의 차단목록</a><br>
	<a class="content" href="#">회원정보 수정</a><br>
	<a class="content" href="/mypage/withdraw">회원 탈퇴</a>
	
	
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/baby-main.js"></script>
</body>
</html>