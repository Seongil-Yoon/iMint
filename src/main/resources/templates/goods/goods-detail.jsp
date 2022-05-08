<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>iMint :: 상품상세</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="/static/css/goods/goods-detail.css" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />

	<input style="display: none;" type="hidden" id="goodsId" value="${goods.goodsId}" />
	<input style="display: none;" type="hidden" id="sellerId" value="${goods.sellerId}" />
	<input style="display: none;" type="hidden" id="sellerNick" value="${goods.sellerNick}" />
	<input style="display: none;" type="hidden" id="buyerId" value="${member.mbId}" />
	<input style="display: none;" type="hidden" id="buyerNick" value="${member.mbNick}" />
	<input style="display: none;" type="hidden" id="goodsTitle" value="${goods.goodsTitle}" />
	<section class="container">
		<article class="row1">
			<h1>${goods.goodsTitle}</h1>
			<c:choose>
				<c:when test="${member.mbId == null}">
					<!-- 둘러보기 -->
				</c:when>
				<c:when test="${member.mbId == goods.sellerId}">
					<a id="modifyBtn" value="${goods.goodsId}">
						<img src="/static/images/write-icon.png" alt="">
					</a>
					<a id="deleteBtn" value="${goods.goodsId}">
						<img src="/static/images/delete-icon.png" alt="">
					</a>
				</c:when>
				<c:otherwise>
					<!-- 둘러보기 -->
				</c:otherwise>
			</c:choose>
		</article>
		<article class="row2">
			<c:choose>
				<c:when test="${goods.goodsStatus == 'wait'}">
					<span class="badge rounded-pill bg-success goods-status">판매중</span>
				</c:when>
				<c:when test="${goods.goodsStatus == 'resrv'}">
					<span class="badge rounded-pill bg-warning text-dark goods-status">예약중</span>
				</c:when>
				<c:when test="${goods.goodsStatus == 'comp'}">
					<span class="badge rounded-pill bg-secondary goods-status">판매완료</span>
				</c:when>
			</c:choose>
			<div class="goods-info">
				<div>
					<span>${goods.sellerNick}</span>
					<img class="member-rating" src="/static/images/sun_4.png" alt="">
					<span>${goods.goodsLocation}</span>
					<span>🤍관심 ${countWishes}</span>
				</div>
				<span class="goods-date" id="timeForToday">${goods.goodsCreateDate}</span>
			</div>
		</article>
		<article class="carousel">
			<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<!-- JS영역 -->
					<!-- <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0"
						class="active" aria-current="true" aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
						aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
						aria-label="Slide 3"></button> -->
				</div>
				<div class="carousel-inner">
					<!-- JS영역 -->
					<!-- <div class="carousel-item active">
						<img src="/static/images/hamster.png" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/static/images/hamster.png" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="/static/images/hamster.png" class="d-block w-100" alt="...">
					</div> -->
				</div>
				<button class="carousel-control-prev customCaroBtn" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon customPrevIcon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next customCaroBtn" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon customNextIcon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</article>
		<article class="goods-content-wrap">
			<textarea disabled="" id="goods-content" placeholder="${goods.goodsContent}"></textarea>
		</article>
		<article class="roundBox goods-action" id="js-goods-action">
			<!-- 일단 로그인됫다고 가정하고 페이지작성 -->
			<div>
				<span>카테고리 : ${goods.goodsCategory}</span>
				<span id="goodsPrice">가격 : ${goods.goodsPrice}원</span>
				<c:choose>
					<c:when test="${goods.goodsSuggestible == true}">
						<span>가격 흥정 가능</span>
					</c:when>
					<c:otherwise>
						<span>가격 흥정 불가능</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
			</div>
			<div>
				<c:choose>
					<c:when test="${member.mbId == null || member.mbRole eq 'GUARD'}">
						<!-- 둘러보기 -->
					</c:when>
					<c:when test="${member.mbId == goods.sellerId}">
						<a class="btn btn-primary" onclick="directOpenChatroom()">
							내 채팅 목록
						</a>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-primary" id="wishBtn">
							관심
						</button>
						<a class="btn btn-primary" id="chatBtn">채팅하기</a>
					</c:otherwise>
				</c:choose>
			</div>
		</article>
	</section>


	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script type="text/javascript" src="/static/js/goods/goods-detail.js"></script>
	<script type="text/javascript" src="/static/js/goods/carousel.js"></script>
	<script>
		carouselReady("${goods.goodsId}");
	</script>
</body>

</html>