<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>iMint :: 관리자 회원통계</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/member_basic.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/admin_member.css">
	<!-- c3.js -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css"/>
	<script src="https://d3js.org/d3.v3.min.js"></script>     
	<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

</head>

<body>
 	<!-- 아티클 시작 -->
	<div class="container-page">
		<h3>회원 통계</h3>
		<hr>
		<div class="container-aside-main">
		<jsp:include page="admin_asideBar.jsp" flush="false" />
		
		<div class = "container-main">
				
		<table class="table table-hover" id = "table">
		<tr> 
		<th>동네</th>
		<th>가입회원</th>
		<th>탈퇴회원</th>

		</tr>
		<tbody id = "member_list">

		<!-- 등록된 회원 리스트 삽입  -->
		<c:forEach items="${memberstats }" var="adminDTO">
		<tr>
			<td>
			<!-- DB에 저장된 Location이 null인 회원은 '위치 미설정 회원'  -->
			<c:if test = "${not empty adminDTO.mbLocation }" >${adminDTO.mbLocation }</c:if>
			<c:if test = "${empty adminDTO.mbLocation }" >위치 미설정 회원</c:if>
			</td>
			<td>${adminDTO.mbCntAll}</td>
			<td>${adminDTO.mbWithdrawAll}</td>
		</tr>
		</c:forEach>
		
		</tbody>
		</table>
		
		<!-- bar chart 그리는 위치  -->
		<div id = "chart"></div>
		</div>
	</div>
	</div>		
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/admin/admin_stats.js"></script>

</body>
</html>