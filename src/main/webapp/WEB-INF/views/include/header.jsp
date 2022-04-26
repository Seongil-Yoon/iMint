<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 헤더 css,js 불러오기는 libs.jsp파일 -->
<!--/*여기는 맨 위에 있는 바 부분*/ -->
<input style="display: none;" type="hidden" id="paramGoodsCategory" value="${goodsCategory}" />
<c:if test="${empty searchOption}">
	<input style="display: none;" type="hidden" id="searchOption" value="all" />
</c:if>
<c:if test="${not empty searchOption}">
	<input style="display: none;" type="hidden" id="searchOption" value="${searchOption}" />
</c:if>
<input style="display: none;" type="hidden" id="keyword" value="${keyword}" />
<div class="header-space"></div>
<nav class="carrot_header">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<!-- 로고 -->
			<a class="navbar-brand" href="/main">
				<img class="imint-logo" src="/static/images/iMint_Black.png" alt="">
			</a>

			<!-- 반응형 드롭다운 버튼 -->


			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar"
				aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbar">
				<!-- 위치 -->
				<input style="display: none;" type="hidden" id="userLocation" value="${userLocation}" />
				<span class="navbar-text location-text collapse navbar-collapse"> ${userLocation} </span>
				<div class="collapse navbar-collapse " id="navbarScroll">

					<!-- 검색창 -->

					<form class="d-flex search" method="GET">
						<div class="input-group">
							<select class="form-select" id="js-searchOption" name="searchOption">
								<option value="all" selected>전체</option>
								<!-- <option value="titleContent">제목+내용</option> -->
								<option value="goods_title">제목</option>
								<option value="goods_content">내용</option>
								<option value="seller_nick">글쓴이</option>
							</select>
							<input type="search" class="form-control" id="keyword" name="keyword"
								aria-label="Text input with dropdown button" value="" placeholder="무엇을 찾아 볼까요?">
						</div>
						<button type="button" class="btn btn-primary search-btn" type="submit" id="search-voice">
							<img class="searchIcon" src="/static/images/mic.png" alt="">
						</button>
						<p class="arrow_box" id="arrow-voice">음성으로 찾아 보세요!</p>
						<button type="button" class="btn btn-primary search-btn" type="submit" id="search-text">
							<img class="searchIcon" src="/static/images/search.png" alt="">
						</button>
						<p class="arrow_box" id="arrow-text">텍스트로 찾아 보세요!</p>
					</form>

					<!-- 상품 등록 -->
					<form class="">
						<sec:authorize access="isAuthenticated()">
							<c:if test="${mbRole eq 'CHILD'}">
								<a class="btn btn-primary add-btn" href="/goods/write">✏️ 상품 등록하기</a>
							</c:if>
						</sec:authorize>
					</form>
				</div>

				<!-- <div class="justify-content-end"> -->
				<ul class="navbar-nav ">
					<sec:authorize access="isAnonymous()">
						<li class="nav-item"><a class="nav-link" href="/">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/">회원가입</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link" href="/mypage">마이페이지</a></li>
					</sec:authorize>
				</ul>

			</div>
		</div>
		<!-- </div> -->
	</nav>
</nav>