<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>@@아기당근@@ :: 거래평가</title>
<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />
	<aside></aside>


	거래 평가 페이지 (임시)
	<br> ${ param.myId }
	<br> ${ param.goodsId }


	<jsp:include page="../include/footer.jsp" flush="false" />
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<!-- <script src="/static/js/mypage/rating.js"></script> -->
	<script type="text/javascript">
		$.ajax({
			url : "wishlist/remove",
			type : "POST",
			data : {
				"myId" : "${ param.myId }",
				"goodsId" : "${ param.goodsId }"
			},
			dataType : "JSON",
			success : function(out) {
				alert("check: " + out.check + "\nresult: " + out.result + "\nvalue: " + out.value);
			}
		});
	</script>
</body>
</html>