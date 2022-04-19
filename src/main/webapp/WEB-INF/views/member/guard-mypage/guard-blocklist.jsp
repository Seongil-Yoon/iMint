<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이민트 :: 마이페이지</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_block.css">
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
	 	<jsp:include page="guard-asideBar.jsp" flush="false" />
	 	</div>
	 	<!-- 어사이드 끝 -->
	 	
	 	<!-- 아티클 시작 -->
	<div class="container-main">
		<div class="title-text">
		   <h2>내아이 ~의 차단 목록입니다.</h2>
		</div>
        <div class="container">
            <div class="tab-content-item">
                <!-- <div class="tab-4-content-inner"> -->
                <!-- 버튼 -->
                    <!-- <div class="buttons">
                        <h6 class="select-title">전체선택</h6>
                        <input type="checkbox" class="selectAllbtn" name="selected"/>
                        <button type="button" id="edit" class="btn btn-secondary btn-sm">수정</button>
                        <button type="button" id="delete" class="btn btn-secondary btn-sm">삭제</button>
                    </div> -->
                    <!-- 항목명 -->
                        <div class="item-lables-chat">
                            <!-- <div class="checkbox">
                                <h6 class="lables-checkbox">선택</h6>
                            </div> -->
                            <div class="photo">
                                <h6 class="lables-person">차단 상대</h6>
                            </div>
                            <div class="interest">
                                <!-- <h6 class="lables-interest">관심 수</h6> -->
                            </div>
                            <div class="link">
                                <!-- <h6 id="link-price-out" class="lables-link">바로가기</h6> -->
                            </div>
                            <div class="block">
                                <h6 class="lables-block">차단 해제</h6>
                            </div>
                        </div>
                        <!-- 항목별 내용 -->
                        <div class="item-list-chat">
                            <!-- <div class="checkbox">
                                <input type="checkbox" name="selected"/>
                            </div> -->
                            <div class="person">
                                <p class="person-photo">사진(예정)</p>
                                <div class="person-detail">
                                    <p class="item-person-nickname">상대방 닉네임</p>
                                    <p class="item-person-chat">마지막 채팅</p>
                                    <div class="item-detail">
                                        <p class="item-subject">상품명</p>
                                        <p class="item-price">가격</p>
                                    </div>
                                </div>
                            </div>
                            <div class="interest">
                                <!-- <p class="text">관심 2</p> -->
                            </div>
                            <div class="link">
                                <button type="button" id="edit" class="btn btn-primary btn-sm">차단 해제</button>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/baby-main.js"></script>
</body>
</html>