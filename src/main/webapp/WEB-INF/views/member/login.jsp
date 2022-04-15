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

/* #wrap {
	margin: auto;
	justify-content: center;
	width: 300px;
	flex-direction: column;
	
} */

</style>

<body>  <!--추가할 부분) 로그인되면 버튼 삭제  -->
 	<jsp:include page="../include/header.jsp" flush="false" />
 	
 	<div class = "container">
	 	<div class="item">
	 		<a href = "/oauth2/authorization/google"><img alt= "google" src = "/static/images/btn_google_light_focus_xxxhdpi.9.png">가입 / 로그인하기</a>
	 	</div>
	 	<div class="item">
		 	<a href = "/oauth2/authorization/naver"><img alt= "naver" src = "/static/images/naver_icon.png">가입 / 로그인하기</a> <!-- 클릭하면 application.yml에 authorization-uri으로 등록한 주소로 이동 --> 
		</div>
	 	<div class="item">
			<a href = "/oauth2/authorization/kakao"><img alt= "kakao" src = "/static/images/kakao_login_medium_wide.png">가입 / 로그인하기</a>
		</div>
		<div class="item">
			<a href = "/main">둘러보기</a>
		</div>			
 	</div>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/login.js"></script>
</body>
</html>