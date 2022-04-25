<!-- 마이페이지-회원정보 수정 
백엔드:양정민 프론트:전해연 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이민트 :: 회원정보수정</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_edit.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
 	<!-- 헤더 시작 -->
 	<jsp:include page="../../include/header.jsp" flush="false" />
 	
 	<!-- 아티클 시작 -->
	<div class="container-page">
		<h1 class="title">마이페이지</h1>
		<div class="container-aside-main">
		
			<!-- 어사이드 시작 -->
			<div class="container-aside">
		 		<jsp:include page="baby-asideBar.jsp" flush="false" />
		 	</div>
		 	<!-- 어사이드 끝 -->
		 	
		     <!-- 수정 항목 컨테이너 -->
		    <div class="container-main">
		        <div class="title-text">
		            <h2>회원 정보를 수정하시겠어요?</h2>
		        </div>
		        
		        <form action="/mypage/edit" method="post"  enctype = "multipart/form-data">
		        <!-- 프로필 사진 수정 (1행)-->
		        <div class="container-context container-photo">
		            <div class="lables-photo">
		                <h5 class="lables-text">프로필 사진 변경</h5>
		            </div>
		            <div class="buttons buttons-photo">
		                <input name = "thumbnail" type="file" id="photo-update-child">
		            	<input name = "mbId" type="hidden" id="mbId">
		            	<input type= "button" class="btn btn-primary" name = "thumbnail_delete_btn" id="thumbnail_delete_btn" value = "프로필 사진 삭제">
		            </div>
		        </div>
		        
		        <!-- 닉네임 수정 (2행)-->
		        <div class="container-context container-nickname">
		            <div class="lables-nickname">
		                <h5 class="lables-text">닉네임 변경</h5>
		            </div>
		            <div class="buttons buttons-nickname">
		                    <input name = "nickname" class="buttons-text" type="text" id="nickname-update-child">
		                    <input type="submit" class="btn btn-primary" value="확인" id="nickname-check-child"/>
		            </div>
		        </div>
		        
		        <!-- 관심사 수정  (3행)-->
		        <div class="container-context container-interest">
		            <div class="lables-interest">
		                <h5 class="lables-text">관심사 변경</h5>
		            </div>
		            <div class="buttons buttons-interest">
		                    <input name = "interest" type="text" class="buttons-text" id="interest-update-child">
		            </div>
		        </div>
		        
		        <!-- 변경하기 버튼 -->
		            <br>
		            <br>
		            <input type="submit" class="btn btn-primary" value="회원정보 수정하기" id="update-complete-child"/>
		        </form>
		    </div>
		</div>
		</div>
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/baby-main.js"></script>
		<script>
	$("#thumbnail_delete_btn").on('click', function(){
		$.ajax({
			url: "/mypage/edit/delete/thumbnail",
			type: 'post',
			data: {'mbId' : $("#mbId").val()
				}, 
			dataType: "json",
				
				success: function(response) {
					alert(JSON.stringify(response.result));
				} // success
		}); // ajax
	}); 	

	</script>
</body>
</html>