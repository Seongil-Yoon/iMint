<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 헤더 css,js 불러오기는 libs.jsp파일 -->
<!--/*여기는 맨 위에 있는 바 부분*/ -->
<nav class="carrot_header">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<!-- 로고 -->
			<a class="navbar-brand" href="/main"> <!-- <img src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="30" height="24"> --> 아이민트로고
			</a>

			<!-- 위치 -->
			<span class="navbar-text location-text"> ${userLocation} </span>

			<div class="collapse navbar-collapse justify-content-center" id="navbarScroll">

				<!-- 검색창 -->
				<form class="d-flex search">
					<input class="form-control me-2" type="search" placeholder="찾고 싶은 물품명을 입력해 주세요!" aria-label="Search">
					<button class="btn btn-primary search-btn" type="submit">음성 검색</button>
					<button class="btn btn-primary search-btn" type="submit">텍스트 검색</button>
				</form>

				<!-- 상품 등록 -->
				<form class="">
					<a class="btn btn-primary add-btn" href="/goods/write">상품 등록</a>
				</form>
			</div>

			<ul class="navbar-nav justify-content-end">
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
	</nav>
</nav>
