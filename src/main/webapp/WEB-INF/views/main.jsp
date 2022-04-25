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
		<div class="goods-category">
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<label for="">
				<input type="radio" name="" id="">
			</label>
			<!-- <span>전체</span>
			<span>문구</span>
			<span>완구</span>
			<span>도서</span>
			<span>교구</span>
			<span>의류</span>
			<span>기방/신발/잡화</span>
			<span>기타</span> -->
		</div>
		<ul id="goodsList">
			<!-- JS영역 -->
		</ul>
	</section>


	<jsp:include page="libs/libsScript.jsp" flush="false" />
	<script type="text/javascript" src="/static/js/main.js"></script>
</body>

</html>