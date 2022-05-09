<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
				<h6 class="lables-price">수입</h6>
			</div>
			<div class="link">
				<h6 class="lables-link">지출</h6>
			</div>
		</div>

		<!-- 항목별 내용 -->

		<c:forEach items="${allComplete}" var="dones" varStatus="status">
			<c:forEach items="${dones.value}" var="done" varStatus="count">
				<div class="item-list item-list-guard ${dones.key }">
					<div class="checkbox">
						<!-- <input type="checkbox" class="select-targets" name="selected" /> -->
						<p class="count">${count.count}</p>
					</div>
					<div class="status">
						<c:choose>
							<c:when test="${done.category eq 'sell'}">
								<p class="text" style="color:blue">판매</p>
							</c:when>
							<c:otherwise>
								<p class="text" style="color:#DD0000">구매</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="photo">
						<img class="photo" src="${done.goodsImagesPath}">
					</div>
					<div class="subject">
						<p class="text subject-text">${done.goodsTitle}</p>
					</div>
					<div class="interest">
						<p class="text">${done.wishes}</p>
					</div>
					<c:choose>
						<c:when test="${done.category eq 'sell'}">
							<div class="price">
								<p class="text text-price" style="color:blue">${done.goodsPrice}</p>
							</div>
							<div class="link">
								<p class="text text-link"></p>
							</div>
						</c:when>
						<c:otherwise>
							<div class="price">
								<p class="text text-price"></p>
							</div>
							<div class="link">
								<p class="text text-link" style="color:#DD0000">${done.goodsPrice}</p>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</c:forEach>

		</div>