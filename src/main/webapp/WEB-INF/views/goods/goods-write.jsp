<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="/static/css/goods/goods-write.css" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />

	<section class="container">
		<form>
			<div class="row1">
				<div class="form-floating">
					<input class="form-control" type="text" value="${member.mbLocation}"
						aria-label="readonly input example" readonly name="goodsLocation">
					<label for="floatingInputValue">현재위치</label>
				</div>
				<div class="roundBox" id="thumbnail"></div>
				<div class="roundBox" id="otherImages"></div>
			</div>
			<div class="row2">
				<div class="form-floating">
					<input type="text" class="form-control" id="floatingInputValue" placeholder="" value="">
					<label for="floatingInputValue">제목 입력하기</label>
				</div>
				<div class="form-floating">
					<select class="form-select" id="floatingSelect" aria-label="Floating label select example">
						<option value="문구" selected>문구</option>
						<option value="완구">완구</option>
						<option value="도서">도서</option>
						<option value="교구">교구</option>
						<option value="의류">의류</option>
						<option value="가방/신발/잡화">가방/신발/잡화</option>
						<option value="기타">기타</option>
					</select>
					<label for="floatingSelect">상품 카테고리</label>
				</div>
			</div>
			<div class="row3">
				<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check" name="suggestible" id="suggestFalse" autocomplete="off"
						checked>
					<label class="btn btn-outline-primary" for="suggestFalse">가격 흥정 불가능</label>
					<input type="radio" class="btn-check" name="suggestible" id="suggestTrue" autocomplete="off">
					<label class="btn btn-outline-primary" for="suggestTrue">가격 흥정 가능</label>
				</div>
				<div class="form-floating">
					<input type="number" class="form-control" id="floatingInputValue" placeholder="원" value="">
					<label for="floatingInputValue">가격 입력하기</label>
				</div>
			</div>
			<div class="row4">
				<div class="form-floating">
					<textarea class="form-control" placeholder="100자 이내" id="floatingTextarea"></textarea>
					<label for="floatingTextarea">글 내용 입럭하기(100자 이내)</label>
				</div>
			</div>
			<div class="row5"></div>
		</form>
	</section>

	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/goods/goods-write.js"></script>
</body>

</html>