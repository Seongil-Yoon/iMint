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
#wrap {
	display: flex;
	margin: auto;
	justify-content: center; /* 수평 정렬  */
	align-items: center; /* 수직 정렬  */
	min-height: 100vh;
	
}

.container {
	display: flex;
	justify-content: space-between;
	justify-content: center;
	margin: auto;
	width: 250px;
	flex-direction: column;
}

/* .item{
	width: 250px;
} */

a{
	justify-content: center;
	width:250px;
}

</style>

<body>
 	<div id="wrap">
 	<div class = "container">
 	
	 	<div class="item">
			iMint로고
		</div>
	 	<div class="item">
	 		<a href = "/oauth2/authorization/google" class="btn btn-primary">아이 가입/ 로그인</a>
	 	</div>
	 	<div class="item">
		 	<a href = "/oauth2/authorization/naver" class="btn btn-naver" style="background-color: #2DB400; color: white">NAVER 가입 / 로그인</a> <!-- 클릭하면 application.yml에 authorization-uri으로 등록한 주소로 이동 --> 
		</div>
		    <!-- <p class="text">iMint</p> -->
	 	<div class="item">
			<a href = "/oauth2/authorization/kakao" class="btn btn-kakao" style="background-color: #F7E600; color: #3A1D1D">KAKAO 가입 / 로그인</a>
		</div>
		<div class="item">
			<a href = "/main" class="btn btn-primary">둘러보기</a>
		</div>			
 	</div>
	</div>
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/login.js"></script>
</body>
</html>