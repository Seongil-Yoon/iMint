<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    	<!-- <img src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="30" height="24"> -->
    	아이민트로고
    </a>
    
    <!-- 위치 -->
    <span class="navbar-text location-text">
      ${userLocation}
    </span>
    
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
			<input type="search" class="form-control" aria-label="Text input with dropdown button"  placeholder="찾고 싶은 물품명을 입력해 주세요!">
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
   		<a class="btn btn-primary add-btn" href="/goods/write">상품 등록</a>
   	</form>
    </div>
	
	<ul class="navbar-nav justify-content-end">
	
	<c:choose>
		<c:when test="${userNickName == null}">
		<li class="nav-item">
	              <a class="nav-link" href="/">로그인</a>
	            </li>
	        <li class="nav-item">
	              <a class="nav-link" href="#">회원가입</a>
	        </li>
		</c:when>
		<c:otherwise>
			<li class="nav-item">
	              <a class="nav-link" href="#">로그아웃</a>
	            </li>
	        <li class="nav-item">
	              <a class="nav-link" href="#">마이페이지</a>
	        </li>
		</c:otherwise>
	</c:choose>
        </ul>
	
  </div>
</nav>
</nav>
