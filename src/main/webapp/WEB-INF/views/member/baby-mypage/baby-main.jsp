<!-- 마이페이지 기능 중 회원 정보 삭제 & 회원 탈퇴 기능의 백엔드는 양정민님 파트입니다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>iMint :: 마이페이지</title>
	<link rel="stylesheet" href="static/css/mypage/mypage_main.css">
	<link rel="stylesheet" href="static/css/mypage/mypage_asidebar.css">
	<jsp:include page="../../libs/libsStyles.jsp" flush="false" />
</head>

<body>
	<!-- 헤더 시작 -->
 	<jsp:include page="../../include/header.jsp" flush="false" />
 	
 	
 	
 	<!-- 아티클 시작 -->
	<div class="container-page">
	
	<div class="container-aside-main">
	<!-- 어사이드 시작 -->
	<div class="container-aside">
	<h2 class="title">마이페이지</h2>
 	<jsp:include page="baby-asideBar.jsp" flush="false" />
 	</div>
 	<!-- 어사이드 끝 -->
 	
    <div class="container-main">
    <div class="welcome-message-container">
    	<h2 class="welcome-message"><img class="welcome-hamster" src="/static/images/hamster.png"> ${userNickName}님, 안녕하세요!</h2>
       
        </div>
        <!-- 보호자 로그인시 숨김 -->
        <h3 class="transaction-summary">총 판매금액은 ${totalSell}원이고, 구매금액은 ${totalBuy}원입니다.</h3>
        <!-- 거래 관련 -->
        <div class="container-transaction">
            <div class="transaction">
                <h5 class="tag">관심/구매예약</h5>
                <br>
                ${userWish.size()} 건
                <a class="btn btn-primary" href="/mypage/mylist">자세히</a>
                <!-- 관심/구매예약 요약 표  -->
            </div>
            <div class="transaction">
                <h5 class="tag">판매목록</h5>
                <br>
                ${userSell.size()} 건
                <a class="btn btn-primary" href="/mypage/mylist">자세히</a>
                <!-- 판매목록 요약 표  -->
            </div>
            <div class="transaction">
                <h5 class="tag">거래완료</h5>
                <br>
                ${userComplete.size()} 건
                <a class="btn btn-primary" href="/mypage/mylist">자세히</a>
                <!-- 거래완료 요약 표  -->
            </div>
        </div>
        <!-- 보호자 로그인시 숨김 해제 -->
        <!-- 회원 상태 -->
        <div class="container-summary">
        	<h5 class="text-summary">${userNickName}님은 아이 회원입니다.</h5>
        		<div class="container-summary-text">
	            
	           	
		            <img src="${userPhoto}" class="user-photo">
		            
		            <div class="container-summary-details">
		            <p>이메일: ${userEmail}</p>
		            <p>관심사: ${userInterest}</p>
		            </div>
		            
           		</div>
            <a class="btn btn-primary" href="/mypage/edit">자세히</a>
        </div>
        <div class="container-other-settings">
            <div class="settings connection">
                <h5 class="settings-title text-connection">연결된 보호자</h5>
                <p class="text-connection">${userGuard.mbNick}님</p>
            </div>
            <div class="settings location">
                <h5 class="settings-title text-location">현재 동네 설정</h5>
                <p class="text-location">${userGuard.mbLocation}</p>
            </div>
        </div>
        <!-- 아이 로그인시 숨김 -->
        <!-- <div class="container-authentication">
            <h5 class="text-authentication">내아이를 연결하시겠어요? <br> 닉네임과 아래 인증번호를 아이 계정에서 입력하여 주세요.</h5>
            <h5 class="text-authentication">인증 번호: ******</h5>
        </div> -->
        <!-- 아이 로그인시 숨김 해제-->
    </div>
    </div>
    </div>
    
    <!-- 푸터 시작 -->
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<jsp:include page="../../include/footer.jsp" flush="false"/>
</body>
</html>