<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="/static/css/goods/goods-detail.css" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />

	<section class="container">
		<h1>게시글 제목</h1>
		<article>
			<h3>판매중</h2>
			<div class="goods-info">
				<span>판매자 닉네임</span>
				<img src="" alt="">
			</div>
		</article>
		<article class="carousel"></article>
		<article class="goods-content"></article>
		<article class="goods-action"></article>
	</section>


	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/goods/goods-detail.js"></script>
</body>

</html>