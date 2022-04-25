<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 헤더 css,js 불러오기는 libs.jsp파일 -->
<!--/*여기는 맨 위에 있는 바 부분*/ -->
<div class="header-space"></div>
<nav class="carrot_header">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
		
			<!-- 로고 -->
			<a class="navbar-brand" href="/main">
				<!-- <img src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="30" height="24"> --> 아이민트로고
			</a>
			
			<!-- 반응형 드롭다운 버튼 -->
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
    		</button>
    		
    		
			<div class="collapse navbar-collapse" id="navbar">
			<!-- 위치 -->
			<input style="display: none;" type="hidden" id="userLocation" value="${userLocation}" />
			<span class="navbar-text location-text navbar-collapse"> ${userLocation} </span>
			
			<div class="collapse navbar-collapse justify-content-center" id="navbarScroll">
			

			

				<!-- 검색창 -->

	
      <form class="d-flex search" action="/search?" method="GET">
      <div class="input-group">
        <select class="form-select" id="js-searchOption" name="searchOption">
          <option value="all" selected>전체</option>
          <option value="titleContent">제목+내용</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
          <option value="writer">글쓴이</option>
        </select>
        <input type="search" class="form-control" aria-label="Text input with dropdown button"  placeholder="무엇을 찾아 볼까요?">
      </div>


					<button class="btn btn-primary search-btn" type="submit">
						<img class="searchIcon" src="/static/images/mic.png" alt="">
					</button>
					<button class="btn btn-primary search-btn" type="submit">
						<img class="searchIcon" src="/static/images/search.png" alt="">
					</button>
				</form>

				<!-- 상품 등록 -->
				<form class="">
					<a class="btn btn-primary add-btn" href="/goods/write">✏️ 상품 등록하기</a>
				</form>
		</div>
			
			<!-- <div class="justify-content-end"> -->
			<ul class="navbar-nav justify-content-end ">
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
	</nav>
</nav>