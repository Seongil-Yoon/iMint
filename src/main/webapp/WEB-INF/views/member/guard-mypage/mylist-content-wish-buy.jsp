<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<!-- 버튼 -->
<!-- <div class="buttons">
	<h6 class="select-title">전체선택</h6>
	<input type="checkbox" class="selectAllbtn" name="selected" />
	<button type="button" id="edit" class="btn btn-secondary btn-sm">수정</button>
	<button type="button" id="delete" class="btn btn-secondary btn-sm">삭제</button>
</div> -->
<!-- 항목명 -->

<div class="item-lables">
	 <div class="checkbox">
		<h6 class="lables-checkbox">No.</h6>
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
		<h6 class="lables-price">가격</h6>
	</div>
	<div class="link">
		<h6 class="lables-link">바로가기</h6>
	</div>
</div>

<!-- 항목별 내용 -->
<c:forEach items="${allWish}" var="wishes" varStatus="status">
	<c:forEach items="${wishes.value}" var="wish" varStatus="count">
		<div class="item-list item-list-guard ${wishes.key}">
			<div class="checkbox">
				<!-- <input type="checkbox" class="select-targets" name="selected" /> -->
				<p class="count">${count.count }</p>
			</div> 
			<div class="status">
				<p class="text">
					<c:choose>
						<c:when test="${wish.category == 'wait'}">
				   			구매 가능
				   		</c:when>
						<c:when test="${wish.category == 'resrv'}">
				   			예약 중
				   		</c:when>
						<c:otherwise>
				   			거래 완료
				   		</c:otherwise>
					</c:choose>
				</p>
			</div>
			<div class="photo">
				<img class="photo" src="${wish.goodsImagesPath}">
			</div>
			<div class="subject">
				<p class="text subject-text">${wish.goodsTitle}</p>
			</div>
			<div class="interest">
				<p class="text">${wish.wishes}</p>
			</div>
			<div class="price">
				<p class="text text-price">${wish.goodsPrice}</p>
			</div>
			<div class="link">
				<button type="button" id="linkItem" class="btn btn-primary btn-sm">바로가기</button>
				
			</div>
		</div>
	</c:forEach>
</c:forEach>

