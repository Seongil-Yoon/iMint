<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>iMint :: 구매자 선택</title>
			<jsp:include page="../libs/libsStyles.jsp" flush="false" />
			<link rel="stylesheet" href="/static/css/main.css" />
		</head>

		<body>
			<!-- 채팅방 목록이 전달되지 않은 경우(판매자가 아니거나, 이미 거래완료 등 조건에 맞지 않는 경우) -->
			<c:if test="${ empty chatroomList }">
				<script type="text/javascript">
					alert("거래를 완료할 수 없습니다.");
					history.back();
				</script>
			</c:if>

			<section id="chatroomlist-section">
				<div id="chatroomlist-wrapper" class="roundBox">
				<h1>채팅 목록에서 구매자 선택</h1>
					<c:forEach items="${ chatroomList }" var="chatroom">
						<c:if test="${ chatroom.reserved }">
							<h2>예약자</h2>
								<div>
									<label>
										<input type="radio" name="buyerId" value="${ chatroom.buyerId }">
										<img src="${ chatroom.mbThumbnail }" width="50px" height="50px">
										${ chatroom.buyerNick }
									</label>
								</div>
							<h2>기타 채팅 목록</h2>
						</c:if>
					</c:forEach>
					<c:forEach items="${ chatroomList }" var="chatroom">
						<c:if test="${ !chatroom.reserved }">
							<div>
								<label>
									<input type="radio" name="buyerId" value="${ chatroom.buyerId }">
									<img src="${ chatroom.mbThumbnail }" width="50px" height="50px">
									${ chatroom.buyerNick }
								</label>
							</div>
						</c:if>
					</c:forEach>
				<div>
					<label>
						<input type="radio" name="buyerId" value="">
						지금은 선택하지 않기
					</label>
				</div>
					<input type="button" id="compbtn" class="btn-primary" value="구매자 선택">
				</div>
			</section>

			<jsp:include page="../libs/libsScript.jsp" flush="false" />
			<script type="text/javascript">
				$("#compbtn").click(function () {
					$.ajax({
						url: "/transaction/trx/complete",
						type: "POST",
						data: {
							buyerId: $("input[name=buyerId]:checked").val(),
							goodsId: "${ param.goodsId }"
						},
						dataType: "JSON",
						success: function (out) {
							if (out.result == "success") {
								alert("거래완료");
								window.close();
							} else {
								alert("거래실패");
							}
						}
					});
				});
			</script>
		</body>

		</html>