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

a{
	justify-content: center;
	width:250px;
}
 
.fadeinleft {
    opacity:0;
    margin-left:-300px;    
    max-width:100%;
    
    flex-direction: row;
}

.thick {
	 font-weight: bold;
}

img{
	width: 100px;
	float: left;
}

</style>

<body>
 	<div id="wrap" class = "highlight">
 	<div class = "container">
 
	 	<div class="item">
	 		<a href = "/oauth2/authorization/google" class="btn btn-primary">아이 가입/ 로그인</a>
	 	</div>
	 	<div class="item">
		 	<a href = "/oauth2/authorization/naver" class="btn btn-naver" style="background-color: #2DB400; color: white">NAVER 가입 / 로그인</a> <!-- 클릭하면 application.yml에 authorization-uri으로 등록한 주소로 이동 --> 
		</div>
	 	<div class="item">
			<a href = "/oauth2/authorization/kakao" class="btn btn-kakao" style="background-color: #F7E600; color: #3A1D1D">KAKAO 가입 / 로그인</a>
		</div>
		<div class="item">
			<a href = "/main" class="btn btn-primary">둘러보기</a>
		</div>	
		
				
 	</div>
	</div>
		<div class="fadeinleft">
			<img src = "/static/images/mint.png">
			<p>아이민트에서 아이들간의 <span class="thick">안전</span>한 지역 중고거래를 체험하세요.<br>
   				아이들끼리만 거래하고, 아이들끼리만 채팅할 수 있습니다.<br>
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
	
<!-- 	<script src="/static/js/login.js"></script> -->
</body>
</html>