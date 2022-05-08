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
	<h1>접근 거부 페이지(403): 미인증 보호자, 아이를 회원가입 페이지로</h1>

	<script>
	location.href = "/register";
	</script>

	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/index.js"></script>
</body>
</html>
<!-- https://offbyone.tistory.com/367 -->