<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="/static/css/goods/goods-modify.css" />
	<jsp:include page="goods-libsStyles.jsp" flush="false" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />

	<section class="container">
		<form>
			<input style="display: none;" type="hidden" id="goodsId" value="${goods.goodsId}" />
			<input style="display: none;" type="hidden" name="sellerId" value="${goods.sellerId}" />
			<input style="display: none;" type="hidden" name="sellerNick" value="${goods.sellerNick}" />
			<input style="display: none;" type="hidden" name="goodsCategory" value="${goods.goodsCategory}" />
			<input style="display: none;" type="hidden" name="goodsSuggestible" value="${goods.goodsSuggestible}" />
			<article class="upload-popup" id="uploadPopup">
				<input type="file" multiple name="goodsImages" id="goodsImages"
					accept=".jpg, .jpeg, .png, .svg, .gif" />
				<!-- 파일폰드 영역 -->
				<button type="button" class="btn btn-primary" id="uploadDone">
					완료
				</button>
			</article>
			<div class="popup-overlay" tabindex="-1" style="opacity: 0; display: none;"></div>
			<div class="row1">
				<div class="form-floating location">
					<input class="form-control" type="text" value="${member.mbLocation}" name="goodsLocation"
						aria-label="readonly input example" readonly name="goodsLocation">
					<label for="floatingInputValue">현재위치</label>
				</div>
				<div class="roundBox" id="thumbnail">
					<div>
						<div>
							<label for="thumbnailFile">상품 대표사진 올리기</label>
							<label for="thumbnailFile">정면 사진을 권장해요</label>
						</div>
						<label class="thumbnailFile" for="thumbnailFile">
							<img src="/static/images/fileupload.png">
						</label>
					</div>
					<input class="form-control" type="text" id="thumbnailFileName" value="파일명 : "
						aria-label="readonly input example" readonly>
					<!-- <label id="thumbnailFileName">파일명 : </label> -->
					<input type="file" name="thumbnailFile" id="thumbnailFile">
				</div>
				<div class="roundBox" id="otherImages">
					<button type="button" class="btn-plus" aria-label="Close" id="upload-popup-btn">
					</button>
				</div>
			</div>
			<div class="row2">
				<div class="form-floating title">
					<input type="text" class="form-control" id="floatingInputValue" placeholder="" name="goodsTitle"
						required value="${goods.goodsTitle}">
					<label for="floatingInputValue">제목 입력하기</label>
				</div>
				<div class="form-floating category">
					<select class="form-select" id="floatingSelect" name="goodsCategory"
						aria-label="Floating label select example">
						<option value="문구" selected>문구</option>
						<option value="완구">완구</option>
						<option value="도서">도서</option>
						<option value="교구">교구</option>
						<option value="의류">의류</option>
						<option value="가방,신발,잡화">가방,신발,잡화</option>
						<option value="기타">기타</option>
					</select>
					<label for="floatingSelect">상품 카테고리</label>
				</div>
			</div>
			<div class="row3">
				<div class="btn-group suggestible" role="group" aria-label="Basic radio toggle button group">
					<input type="radio" class="btn-check" name="suggestible" id="suggestFalse" autocomplete="off"
						value="false" checked>
					<label class="btn btn-outline-primary" for="suggestFalse">가격 흥정 불가능</label>
					<input type="radio" class="btn-check" name="suggestible" id="suggestTrue" autocomplete="off"
						value="true">
					<label class="btn btn-outline-primary" for="suggestTrue">가격 흥정 가능</label>
				</div>
				<div class="form-floating price">
					<input type="number" class="form-control" id="floatingInputValue" placeholder="원" name="goodsPrice"
						required value="${goods.goodsPrice}">
					<label for="floatingInputValue">가격 입력하기</label>
				</div>
			</div>
			<div class="row4">
				<div class="form-floating content">
					<textarea class="form-control" placeholder="100자 이내" id="floatingTextarea" name="goodsContent" 
						required>${goods.goodsContent}</textarea>
					<label for="floatingTextarea">글 내용 입력하기(100자 이내)</label>
				</div>
			</div>
			<div class="row5">
				<div></div>
				<button type="button" class="tab-item goodsSubmit" id="goodsSubmit">
					<p>수정하기</p>
			</div>
			</div>
		</form>
	</section>

	<jsp:include page="goods-libsScript.jsp" flush="false" />
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/goods/goods-modify.js"></script>
</body>

</html>