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
			<h2>판매중</h2>
			<div class="goods-info">
				<div>
					<span>판매자 닉네임</span>
					<img class="member-rating" src="/static/images/sun_4.png" alt="">
					<span>지역</span>
					<span>🤍관심 </span>
				</div>
				<span class="goods-date">xx초전</span>
			</div>
		</article>
		<article class="carousel">
			<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
						class="active" aria-current="true" aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
						aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
						aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<!-- JS영역 -->
					<div class="carousel-item active">
						<img src="/static/images/hamster.png" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/static/images/hamster.png" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/static/images/hamster.png" class="d-block w-100" alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</article>
		<article class="goods-content-wrap">
			<textarea disabled="" id="goods-content" placeholder="상품글1"></textarea>
		</article>
		<article class="goods-action" id="js-goods-action">

		</article>
	</section>


	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/goods/goods-detail.js"></script>
</body>

</html>