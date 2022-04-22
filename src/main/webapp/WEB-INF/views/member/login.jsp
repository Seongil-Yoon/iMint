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

body{
	width:600px;
	margin: auto;
	justify-content: center; /* 수평 정렬  */
	align-items: center; /* 수직 정렬  */
	min-height: 100vh;	
	
	background-color: black;
	/* opacity: 0.8; */
}

body::after{ /* 가상 선택자  */
	width: 100%;
  	height: 100%;
  	content: "";
	background: url('/static/images/mint_background.jpg');
	background-repeat: repeat-y; /* 적용 x */
  	background-attachment: fixed;
  	background-position: center;
	position: absolute;
  	top: 0;
  	left: 0;
  	z-index: -1;
  	opacity: 0.7;
  	/* filter: grayscale(100%); */
}

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

.item{
	padding: 4px;
	
}


a{
	justify-content: center;
	width:250px;
}
 
.fadeinleft {
    opacity:0;
    margin-left:-300px;    
    max-width:100%;
    
    flex-direction: row;
    
   	width: 600px;
}

.thick {
	 font-weight: bold;
}

img{
	width: 100px;
	float: left;
}

h3 {
	color: #3387a8;
}

h2, p{
	color: white;
}


#moving { 
display: flex; 
align-items: center; 
justify-content: center; 
width: 50px; 
height: 50px; 
animation: motion 2s infinite;

/* 애니메이션 적용 */ }

@keyframes motion { 

0% {transform: rotate( -15deg );}
25% {transform: rotate( -10eg );}
50% {transform: rotate( 0deg );}
75% {transform: rotate( 10deg );}
85% {transform: rotate( 0deg );}
100% {transform: rotate( -10deg );}
} 
 
</style>

<body>
	
 	<div id="wrap" class = "highlight">
 	<div class = "container">

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
			<p style="color: white" class="lh-lg">아이민트에서 아이들간의 <span class="thick">안전</span>한 지역 중고거래를 체험하세요.<br>
   				아이들끼리만 거래하고, 아이들끼리만 채팅할 수 있습니다.<br></p>
   			<br>
   			<br>

   			<img src = "/static/images/mint.png">	
			<h3>
			연동
			</h3>
   			<p style="color: white" class="lh-lg">	
    		아이들이 어떤 <span class="thick">활동</span>을 하고 있는지 <span class="thick">궁금</span>하신가요?<br>
    		연동된 보호자 계정으로 아이들과 함께 연습할 수 있습니다.
    		</p>
		</div>	
	
	<jsp:include page="../libs/libsScript.jsp" flush="false" />
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		
	    $(window).scroll( function(){
	        $('.fadeinleft').each( function(i){
	            
	            var bottom_of_element = $(this).offset().top + $(this).outerHeight();
	            var bottom_of_window = $(window).scrollTop() + $(window).height();
	            
	            if( bottom_of_window > bottom_of_element ){
	                $(this).animate({'opacity':'1','margin-left':'0px'},2000);
	            }
	            
	        }); 
	    });
	});

	</script>
	
</body>
</html>