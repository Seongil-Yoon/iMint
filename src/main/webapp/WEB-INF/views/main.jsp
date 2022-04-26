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
		<div class="${goodsCategory}" id="id-finder"></div>
		<article class="goods-category">
			<label id="tab-1" class="tab-item selected">
				<input type="radio" name="goodsCategory" id="" value="all" checked>
				<span class="hide-sm">전체</span>
			</label>
			<label id="tab-2" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="문구">
				<span class="hide-sm">문구</span>
			</label>
			<label id="tab-3" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="완구">
				<span class="hide-sm">완구</span>
			</label>
			<label id="tab-4" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="도서">
				<span class="hide-sm">도서</span>
			</label>
			<label id="tab-5" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="교구">
				<span class="hide-sm">교구</span>
			</label>
			<label id="tab-6" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="의류">
				<span class="hide-sm">의류</span>
			</label>
			<label id="tab-7" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="가방,신발,잡화">
				<span class="hide-sm">가방,신발,잡화</span>
			</label>
			<label id="tab-8" class="tab-item">
				<input type="radio" name="goodsCategory" id="" value="기타">
				<span class="hide-sm">기타</span>
			</label>
		</article>
		<ul id="goodsList">
			<!-- JS영역 -->
		</ul>
	</section>


	<jsp:include page="libs/libsScript.jsp" flush="false" />
	<script type="text/javascript" src="/static/js/main.js"></script>
</body>

</html>