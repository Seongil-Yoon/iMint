<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>iMint :: 메인페이지</title>
	<jsp:include page="libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="/static/css/main.css" />
</head>

<body>
	<jsp:include page="include/header.jsp" flush="false" />

	<section class="container">
		<ul id="goodsList">
			<!-- JS영역 -->
			<li class="goods-detail">
				<a href="">
					<div class="goods-image">
						<img src="/static/images/aaaa.jpg">
					</div>
					<div class="goods-info">
						<div>
							<span class="goods-title">디월트 배터리</span>
						</div>
						<div>
							<span class="goods-price">50000원</span>
							<span class="goods-status">판매중</span>
						</div>
						<div>
							<span class="goods-location">대구시 대명동</span>
						</div>
						<div>
							<span class="goods-wishCount">관심 : 38</span>
							<span class="goods-writeDate">5분전</span>
						</div>
					</div>
				</a>
			</li>
		</ul>
	</section>


	<jsp:include page="libs/libsScript.jsp" flush="false" />
	<script src="/static/js/main.js"></script>
</body>

</html>