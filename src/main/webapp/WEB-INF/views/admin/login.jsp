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

<style>
.container {
	display: flex;
	justify-content: space-between;
	justify-content: center;
	margin: auto;
	width: 250px;
	flex-direction: column;
}

img{
	width: 50px;
}

.item{
	width: 200px;
}

</style>

<body>  
 	<div class = "container">
	 	<div class="item">
	 		<a href = "/oauth2/authorization/google"><img alt= "google" src = "/static/images/btn_google_light_focus_xxxhdpi.9.png">관리자 로그인</a>
	 	</div>
	 	<div class="item">
		 	<a href = "/oauth2/authorization/naver"><img alt= "naver" src = "/static/images/naver_icon.png">관리자 로그인</a> <!-- 클릭하면 application.yml에 authorization-uri으로 등록한 주소로 이동 --> 
		</div>
	 	<div class="item">
			<a href = "/oauth2/authorization/kakao"><img alt= "kakao" src = "/static/images/kakao_login_medium_wide.png">관리자 로그인</a>
		</div>
		<div class="item">
			<a href = "/main">일반 사용자 메인페이지 둘러보기</a>
		</div>			
 	</div>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/login.js"></script>
</body>
</html>