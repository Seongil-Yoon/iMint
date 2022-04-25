<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>@@아기당근@@ :: 마이페이지</title>
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<!-- 어사이드바 컨테이너 -->
    <div class="aside-container">
    	<a class="content" href="/mypage/location">
            <div class="aside-tab-item">
                <p class="tab-text">내 동네 설정</p>
            </div>
        </a><br>
        <a class="content" href="/mypage/mylist">
            <div class="aside-tab-item">
                <p class="tab-text">내 아이목록</p>
            </div>
        </a><br>
        <a class="content" href="/mypage/edit">
            <div class="aside-tab-item">
                <p class="tab-text">회원정보 수정</p>
            </div>
        </a><br>
        <a class="content" href="/mypage/withdraw">
            <div class="aside-tab-item">
                <p class="tab-text">회원 탈퇴</p>
            </div>
        </a>
    </div>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/baby-main.js"></script>
</body>
</html>