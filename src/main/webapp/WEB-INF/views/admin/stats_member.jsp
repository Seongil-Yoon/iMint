<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
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
		<c:forEach items="${memberlist }" var="memberDTO">
		<tr><td class = "table_cnt"><input type = "checkbox" name = "member_check" ></td><td class = "table_cnt"></td><td>${memberDTO.mbNick }</td><td>${memberDTO.mbEmail}</td><td>${memberDTO.mbRole}</td><td>${memberDTO.mbGuard }</td><td>${memberDTO.mbJoinDate}</td></tr>
		</c:forEach>
		
		</tbody>

		</table>
						
	</div>	
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/admin/stats_member.js"></script>
</body>
</html>