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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css"/>
	<!-- javascript -->     
	<script src="https://d3js.org/d3.v3.min.js"></script>     
	<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

</head>

<body>
	
		<div>
				
		<h3>회원 통계</h3>
		<hr>

		<table class="table table-hover" id = "table">
		<tr> 
		<th>동네</th>
		<th>가입회원</th>
		<th>탈퇴회원</th>

		</tr>
		<tbody id = "product_list">
		<!-- 등록된 회원 리스트 삽입  -->
		<c:forEach items="${memberstats }" var="adminDTO">
		<tr><td>${adminDTO.mbLocation }</td><td>${adminDTO.mbCntAll}</td><td>${adminDTO.mbWithdrawAll}</td></tr>
		</c:forEach>
		
		</tbody>
		</table>
		
		<div id = "chart"></div>
		
		<!-- <a href = "/admin/stats/member/download"><input type = "button" class="btn btn-primary" value = "excel"></a>				 -->
	</div>	
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/admin/admin_stats.js"></script>
</body>
</html>