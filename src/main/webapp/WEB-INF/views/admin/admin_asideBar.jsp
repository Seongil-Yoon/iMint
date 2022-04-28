<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<!-- 어사이드바 컨테이너 -->
    <div class="aside-container">
        <div class="aside-tab-item">
        <a class="content" href="/admin/member">
                <span class="tab-text">회원 관리</span>
        </a>
        </div>
        <div class="aside-tab-item">
        <a class="content" href="/admin/stats/member">
                <span class="tab-text">회원 통계</span>
        </a>
        <div class="aside-tab-item">        
        <a class="content" href="/logout">
                <span class="tab-text">로그아웃</span>
        </a>
        </div>       
        </div>
    </div>
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
</body>
</html>