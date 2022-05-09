<!-- 마이페이지-내동네설정
백엔드:양정민 프론트:전해연 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>iMint :: 내동네설정</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_location.css">
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<div id="container-page-all">
    <c:if test="${memberDTO.mbRole eq 'GUARD'}"> 
 	<jsp:include page="../../include/header.jsp" flush="false" />
 	<!-- 아티클 시작 -->
	<div class="container-page">
	<div class="container-aside-main">
	<!-- 어사이드 시작 -->
	<div class="container-aside">
	<h2 class="title">마이페이지</h2>
 	<jsp:include page="guard-asideBar.jsp" flush="false" />
 	<!-- 어사이드 끝 -->
 	</div>
 	<!-- 아티클 끝(원래 전체를 감싸고 있었는데 c:if태그가 중간에 끼어들어서 에러발생으로 임시로 div태그를 미리 닫았습니다(정민) -->
 	
 	
 	</c:if>
 	
    <div class="container-main">
	<h3 id = "guappend"></h3>	
	
	<br><br>
	
	<div id="map" style="width:750px;height:350px;"></div>
	<br><br>
	 	<div class="buttons">
		 
		 	<form id = "confirm_form">
				<input type = hidden id = "guappend2" name = "mbLocationOrGuard" >
				<input type = hidden id = "mbRole" name = "mbRole" value = ${memberDTO.mbRole }>
			 	<input type = "submit" id = "confirm_btn" class="btn btn-primary" value = "확인했어요">
			</form>
		</div>
	
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
	    	
		/* 카카오 지도 API */
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표 // 현재좌표
		        level: 5, // 지도의 확대 레벨
		        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
		    }; 

		// 지도를 생성한다 
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// 지도에 확대 축소 컨트롤을 생성한다
 		var zoomControl = new kakao.maps.ZoomControl();

		// 지도의 우측에 확대 축소 컨트롤을 추가한다
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
 			 $.ajax({
				url: 'https://dapi.kakao.com/v2/local/geo/coord2address.json?input_coord=WGS84&output_coord=WGS84&x=' + longitude +'&y=' + latitude,
			    headers : {'Authorization' : 'KakaoAK 81c7bda99c1d17edaf364c7a1fe1b80d'},
				type: 'GET',
					
				success: function(response) {
 						var contentStr = "";
 						
 						var firLocation = JSON.stringify(response.documents[0].address.region_1depth_name); /* 파싱 한다음에 JSON.stringify */
 						var len = firLocation.length;
 						contentStr = firLocation.substring(1,len-1);

 						var secLocation = JSON.stringify(response.documents[0].address.region_2depth_name);
 						var len = secLocation.length;
 						contentStr += " " + secLocation.substring(1,len-1);

 					$("#guappend").html("현재 " + contentStr + "에 있어요");
 					$("#guappend2").val(contentStr);
 						if($("#mbRole").val() == "GUARD"){
	 						$("#confirm_form").attr("action", "/mypage/location"); /* submit */
	 						$("#confirm_form").attr("method", "post");
 						}
 						else{
 							
	 						$("#confirm_form").attr("action", "/");
	 						$("#confirm_form").attr("method", "post");
 						}
				}, // success
			    error : function(e) {
			        console.log(e);
			        }			     	
			}); // ajax 	 

	}); // navigator
	
	</script>	

 	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e37aa69d13b6cf73efe7b84d8b071e13&libraries=LIBRARY"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e37aa69d13b6cf73efe7b84d8b071e13&libraries=services,clusterer,drawing"></script>	
	</div>
 		
	<c:if test="${memberDTO.mbRole eq 'GUARD'}"> 	
	</div>
	</div>
	</c:if>
</div>
 
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	
</body>
</html>