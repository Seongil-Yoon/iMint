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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">	
</head>


<body>


	<hr>

	<div class = "container">

 	<!-- 아티클 시작 -->
	<div class="container-page">
		<h3>회원 리스트</h3>
		<div class="container-aside-main">
		<jsp:include page="admin_asideBar.jsp" flush="false" />
		</div>
		
		<div class = "container-main">
		<table class="table table-hover" id = "table">
		<tr> 
		<!-- HTML 태그에 속성으로 이벤트 리스너를 등록 -->
		<th><input type = "checkbox" name = "member_check" value="0" 
									onclick='selectAll(this)'></th>
		<th>No.</th>
		<th>닉네임</th>
		<th>이메일</th>
		<th>권한</th>
		<th>연동계정</th>
		<th>가입일</th>

		</tr>
		<tbody id = "member_list">
		<!-- 등록된 회원 리스트 삽입  -->
		<c:forEach items="${memberlist }" var="memberDTO">
		<tr><td class = "table_cnt"><input type = "checkbox" name = "member_check" ></td><td>${memberDTO.mbNo }</td><td>${memberDTO.mbNick }</td><td>${memberDTO.mbEmail}</td><td>${memberDTO.mbRole}</td><td>${memberDTO.mbGuard }</td><td>${memberDTO.mbJoinDate}</td></tr>
		</c:forEach>
		
		</tbody>

		</table>
						
		</div>	

	</div>
	</div>
	
	<jsp:include page="../include/footer.jsp" flush="false"/>
	<jsp:include page="../libs/libsScript.jsp" flush="false" />

	<script src="/static/js/admin/admin_member.js"></script>
		
</body>
</html>