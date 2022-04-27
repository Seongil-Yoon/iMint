<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>iMint :: 예약자 선택</title>
			<jsp:include page="../libs/libsStyles.jsp" flush="false" />
			<link rel="stylesheet" href="/static/css/main.css" />
		</head>

		<body>
			<!-- 채팅방 목록이 전달되지 않은 경우(판매자가 아니거나, 이미 예약중 등 조건에 맞지 않는 경우) -->
			<c:if test="${ empty chatroomList }">
				<script type="text/javascript">
					alert("예약할 수 없습니다.");
					history.back();
				</script>
			</c:if>

			<section id="chatroomlist-section">
				<div id="chatroomlist-wrapper" class="roundBox">
					<h1>채팅 목록에서 예약자 선택</h1>
					<c:forEach items="${ chatroomList }" var="chatroom">
						<div>
							<label>
								<input type="radio" name="chatroomId" value="${ chatroom.chatroomId }">
								<img src="${ chatroom.mbThumbnail }" width="50px" height="50px">
								${ chatroom.buyerNick }
							</label>
						</div>
					</c:forEach>
					<input type="button" id="resrvbtn" class="btn-primary" value="예약자 선택">
				</div>
			</section>

			<jsp:include page="../libs/libsScript.jsp" flush="false" />
			<script type="text/javascript">
				$("#resrvbtn").click(function () {
					$.ajax({
						url: "/transaction/resrv/make",
						type: "POST",
						data: {
							goodsId: "${ param.goodsId }",
							chatroomId: $("input[name=chatroomId]:checked").val()
						},
						dataType: "JSON",
						success: function (out) {
							if (out.result == "success") {
								alert("예약 완료");
							} else {
								alert("예약 실패");
							}
						}
					});
				});
			</script>
		</body>

		</html>