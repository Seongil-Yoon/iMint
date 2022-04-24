<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/member_basic.css">
	
</head>

<body>
	
		<div>
				
		<h3>회원 통계</h3>
		<hr>

		<table class="table table-hover" id = "table">
		<tr> 
		<th>동네</th>
		<th>탈퇴회원</th>
		<th>가입회원</th>

		</tr>
		<tbody id = "product_list">
		<!-- 등록된 회원 리스트 삽입  -->
		<c:forEach items="${memberstats }" var="adminDTO">
		<tr><td>${adminDTO.mbLocation }</td><td>${adminDTO.mbWithdrawAll}</td><td>${adminDTO.mbCntAll}</td></tr>
		</c:forEach>
		
		</tbody>

		</table>
		
		<a href = "/admin/stats/member/download"><input type = "button" class="btn btn-primary" value = "excel"></a>				
	</div>	
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/admin/stats_member.js"></script>
</body>
</html>