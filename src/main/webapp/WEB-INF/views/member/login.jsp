<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<jsp:include page="../libs/libsStyles.jsp" flush="false" />
	<link rel="stylesheet" href="/static/css/member/member_login.css"/>
</head>

<body>
	
 	<div id="wrap" class = "highlight">
 	<div class = "container">
	 	<div class="item">
	 		${register}
	 	</div>
  	 	<div class="item">
  	 		<img id = "moving" src = "/static/images/mint.png">
	 		<h2 style = "text-align: center; width:225px;">iMint</h2>
	 	</div>
	 	<div class="item">
	 		<a href = "/oauth2/authorization/google" class="btn btn-dark opacity-75" style="color: white" >아이 가입 | 로그인(BETA)</a>
	 	</div>
	 	<div class="item">
		 	<a href = "/oauth2/authorization/naver" class="btn btn-dark opacity-75" style="color: #2DB400">NAVER 가입 | 로그인</a> <!-- 클릭하면 application.yml에 authorization-uri으로 등록한 주소로 이동 --> <!-- #2DB400 --> 
		</div>
	 	<div class="item">
			<a href = "/oauth2/authorization/kakao" class="btn btn-dark opacity-75" style="color: #F7E600">KAKAO 가입 | 로그인</a> <!-- #F7E600, #3A1D1D -->
		</div>
		<div class="item">
			<a href = "/main" class="btn btn-dark opacity-75" style="color:  white">둘러보기</a>
		</div>	
 	</div>
	</div>

	<br>
	<br>
		<div class="fadeinleft" class="shadow p-3 mb-5 bg-body rounded">
			<img src = "/static/images/mint.png">
			<h3>
			안전
			</h3>
			<p class="lh-lg">아이민트에서 아이들간의 <span class="thick">안전</span>한 지역 중고거래를 체험하세요.<br>
   				아이들끼리만 거래하고, 아이들끼리만 채팅할 수 있습니다.<br></p>
   			<br>
   			<br>

   			<img src = "/static/images/mint.png">	
			<h3>
			연동
			</h3>
   			<p class="lh-lg">	
    		아이들이 어떤 <span class="thick">활동</span>을 하고 있는지 <span class="thick">궁금</span>하신가요?<br>
    		연동된 보호자 계정으로 아이들과 함께 연습할 수 있습니다.
    		</p>
		</div>	
	<br>
	<br>
	
	<jsp:include page="../include/footer.jsp" flush="false"/>
	
 	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/member/member_login.js"></script>

</body>
</html>