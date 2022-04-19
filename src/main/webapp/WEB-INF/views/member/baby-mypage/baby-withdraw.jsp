<!-- 마이페이지-회원탈퇴 
백엔드:양정민 프론트:전해연 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이민트 :: 회원 탈퇴</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_withdraw.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<!-- 헤더 시작 -->
 	<jsp:include page="../../include/header.jsp" flush="false" />
 	<!-- 아티클 시작 -->
	<div class="container-page">
		<h1 class="title">마이페이지</h1>
		<div class="container-aside-main">
			<!-- 어사이드 시작 -->
			<div class="container-aside">
		 		<jsp:include page="baby-asideBar.jsp" flush="false" />
		 	</div>
		 	<!-- 어사이드 끝 -->
		 	
		    <div class="container-main">
				<div class="text">
					<h2>회원을 탈퇴하시겠어요? 😢</h2>
					<h2>되돌리기 어려우니 신중하게 생각해 주세요.</h2>
				</div>
				<div class="buttons">
					<!-- <form action="#">
						<input class="buttons-text"type="password" placeholder="비밀번호를 입력해 주세요.">
						<input type="submit" class="btn btn-primary" value="확인"/>
					</form> -->
					<form action="/mypage/withdraw" method="post">
						<br>
						<input type="submit" class="btn btn-danger" value="아이민트 탈퇴하기" id="withdraw-complete-child"/>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/baby-main.js"></script>
</body>
</html>