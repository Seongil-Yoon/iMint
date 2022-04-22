<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 버튼 -->
<div class="buttons">
	<h6 class="select-title">전체선택</h6>
	<input type="checkbox" class="selectAllbtn" name="selected" />
	<button type="button" id="edit" class="btn btn-secondary btn-sm">수정</button>
	<button type="button" id="delete" class="btn btn-secondary btn-sm">삭제</button>
</div>
<!-- 항목명 -->

<div class="item-lables">
	<div class="checkbox">
		<h6 class="lables-checkbox">선택</h6>
	</div>
	<div class="status">
		<h6 class="lables-status">구분</h6>
	</div>
	<div class="photo">
		<h6 class="lables-photo">사진</h6>
	</div>
	<div class="subject">
		<h6 class="lables-subject">거래명</h6>
	</div>
	<div class="interest">
		<h6 class="lables-interest">관심 수</h6>
	</div>
	<div class="price">
		<h6 class="lables-price">수입</h6>
	</div>
	<div class="link">
		<h6 class="lables-link">지출</h6>
	</div>
</div>

<!-- 항목별 내용 -->
<div class="item-list">
	<c:forEach items="${allComplete}" var="dones" varStatus="status">
		<c:forEach items="${dones.value}" var="done">
			<div class="checkbox">
				<input type="checkbox" class="select-targets" name="selected" />
			</div>
			<div class="status">
				<p class="text">예약중</p>
			</div>
			<div class="photo">
				<img class="photo" src="${done.goodsImagesPath}">
			</div>
			<div class="subject">
				<p class="text">${done.goodsTitle}</p>
			</div>
			<div class="interest">
				<p class="text">관심 ${done.wishes}</p>
			</div>
			<div class="price">
				<p class="text text-price">${done.goodsPrice}</p>
			</div>
			<div class="link">
				<p class="text text-link">바로가기</p>
			</div>
		</c:forEach>
	</c:forEach>
</div>
