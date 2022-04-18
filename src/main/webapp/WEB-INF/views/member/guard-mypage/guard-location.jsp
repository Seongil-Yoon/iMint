<!-- 마이페이지-내동네설정
백엔드:양정민 프론트:전해연 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>@@아기당근@@ :: 내동네설정</title>
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<jsp:include page="../../include/header.jsp" flush="false" />
 	<aside></aside>
	
	
	보호자 마이페이지 내 동네 설정입니다.
	<br>
	<br>
	<h3 id = "guappend"></h3>	
	
	<div id="map" style="width:750px;height:350px;"></div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e37aa69d13b6cf73efe7b84d8b071e13"></script>

	<script>
		/* 현재 위치 받기  */
		var latitude;
		var longitude;
		
		navigator.geolocation.getCurrentPosition(function(pos) {
			latitude = pos.coords.latitude;
		    latitude = latitude.toFixed(5);
	    	longitude = pos.coords.longitude;
	    	longitude = longitude.toFixed(5);
	    	alert("현재 위치는 : " + latitude + ", "+ longitude); // 
	    	
		/* 카카오 지도 API */
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표 // 현재좌표로 바꾸기 
		        level: 5, // 지도의 확대 레벨
		        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
		    }; 

		// 지도를 생성한다 
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// 지도에 확대 축소 컨트롤을 생성한다
 		var zoomControl = new kakao.maps.ZoomControl();

		// 지도의 우측에 확대 축소 컨트롤을 추가한다
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		
		
	    /* 좌표로 구정보 얻기 : 카카오 API*/
	    $("#mylocation_btn").on('click', function(){ /* ajax로 요청한 뒤 파싱은 어떻게 하는가?  */

 			 $.ajax({
				url: 'https://dapi.kakao.com/v2/local/geo/coord2address.json?input_coord=WGS84&output_coord=WGS84&x=' + longitude +'&y=' + latitude,
			    headers : {'Authorization' : 'KakaoAK 81c7bda99c1d17edaf364c7a1fe1b80d'},
				type: 'GET',
					
				success: function(response) {
 						var contentStr = "";
 						contentStr += JSON.stringify(response.documents[0].address.region_2depth_name); /* 파싱 한다음에 JSON.stringify 하기.. */
 						var len = contentStr.length;
 						contentStr = contentStr.substring(1,len-1)
 						alert(contentStr);

 					$("#guappend").html("현재 " + contentStr + "에 있어요");
 					$("#guappend2").val(contentStr);
				},
			    error : function(e) {
			        console.log(e);
			        }			     	
			}); // ajax 	 
			
			
		}); // onclick


	}); // navigator
	
	</script>	

 	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e37aa69d13b6cf73efe7b84d8b071e13&libraries=LIBRARY"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e37aa69d13b6cf73efe7b84d8b071e13&libraries=services,clusterer,drawing"></script>	
 	
 	<button id = "mylocation_btn">내위치 조회</button>
 
 	<form action = "/register/complete" method = "post">
		<input type = hidden id = "guappend2" name = "mbLocation" >
	 	<button id = "confirm_btn">확인했어요</button>
	</form>
 
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<!-- <script src="/static/js/guard-main.js"></script> -->
</body>
</html>