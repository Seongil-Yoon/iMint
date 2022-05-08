<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>iMint :: 마이페이지</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_asidebar.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mypage_mylist.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mypage/mylist-content.css">
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
	 	
	 	<!-- 아티클 시작 -->
	 	<div class="container-main">
		 	<div class="title-text">
			   <h2>${userNick}님의 거래 목록입니다.</h2>
			</div>
	 			<!-- 상단 탭 -->
	        <section class="tabs">
	            <div class="container">
	                <div id="tab-1" class="tab-item selected-tab selected">
	                    <p class="hide-sm">관심/구매예약</p>
	                </div>
	                <div id="tab-2" class="tab-item">
	                    <p class="hide-sm">판매목록</p>
	                </div>
	                <div id="tab-3" class="tab-item">
	                    <p class="hide-sm">거래완료목록</p>
	                </div>
	                <div id="tab-4" class="tab-item">
	                    <p class="hide-sm">채팅목록</p>
	                </div>
	            </div>
	        </section>
	        <!-- 탭별 내용 -->
	        <section class="tab-content">
	            <div class="container">
	                <!-- 관심/구매예약 내용 -->
	                <div id="tab-1-content" class="tab-content-item show">
	                    <div class="tab-1-content-inner">
	                        <jsp:include page="mylist-content-wish-buy.jsp" flush="false"/>
	                    </div>
	                </div>
	                <!-- 판매목록 내용 -->
	                <div id="tab-2-content" class="tab-content-item">
	                    <div class="tab-2-content-inner">
	                        <jsp:include page="mylist-content-selling.jsp" flush="false"/>
	                    </div>
	                </div>
	                <!-- 거래완료목록 내용 -->
	                <div id="tab-3-content" class="tab-content-item">
	                    <div class="tab-3-content-inner">
	                    	 <jsp:include page="mylist-content-complete.jsp" flush="false"/>
	                    </div>
	                </div>
	                <!-- 채팅목록 내용 -->
	                <div id="tab-4-content" class="tab-content-item">
	                    <div class="tab-4-content-inner">
	                    <!-- 버튼 -->
							<!-- <div class="buttons">
	                            <h6 class="select-title">전체선택</h6>
	                            <input type="checkbox" class="selectAllbtn" name="selected"/>
	                            <button type="button" id="block" class="btn btn-secondary btn-sm">차단</button>
	                            <button type="button" id="chatroom-delete" class="btn btn-secondary btn-sm">삭제</button>
                        	</div> -->
	                        <!-- 항목명 -->
		                        <div class="item-lables-chat">
		                        
		                            <div class="checkbox">
		                                <h6 class="lables-checkbox">No.</h6>
		                            </div>
		                            <div class="photo">
		                                <h6 class="lables-person">채팅 상대</h6>
		                            </div>
		                            <div class="interest">
		                                <!-- <h6 class="lables-interest">관심 수</h6> -->
		                            </div>
		                            <div class="link">
		                                <h6 id="link-price-out" class="lables-link">바로가기</h6>
		                            </div>
		                        </div>
		                        <!-- 항목별 내용 -->
		                        
		                        <c:choose>
		                       	<c:when test="${userChat.size() == 0}">
		                       	
		                       	<br><p>진행 중인 채팅이 없어요. 지금 시작해 보세요!</p>
		                       	</c:when>
		                       	<c:otherwise>
		                        <c:forEach items="${userChat}" var="chat" varStatus="count">
		                        <div class="item-list-chat">
		                            <div class="checkbox">
		                                <!-- <input type="checkbox" name="selected"/> -->
		                                <p>${count.count }</p>
		                            </div>
		                            <div class="person">
		                            	<div class="photo-container">
		                            	<img src="${chat.opponentThumbnail}" class="person-photo">
		                                </div>
		                                <div class="person-detail">
		                                    <p class="item-person-nickname">${chat.opponentNick}</p>
		                                    <p class="item-person-chat">${chat.message}</p>
		                                    <div class="item-detail">
		                                        <p class="item-subject subject-text">${chat.goodsTitle}</p>
		                                        <p class="item-price">${chat.goodsPrice}</p>
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="interest">
		                                <!-- <p class="text">관심 2</p> -->
		                            </div>
		                            <div class="link">
		                                <button type="button" class="btn btn-primary btn-sm" onclick="directJoinChatroom(<c:out value='${chat.id}' />)">바로가기</button>
		                            </div>
		                          </div>
		                            </c:forEach>
		                            </c:otherwise>
		                            </c:choose>
		                            
		                        
		                    </div>
		                </div>
		            </div>
		        </section>
	    	</div>
	    </div>
    </div>
	<jsp:include page="../../include/footer.jsp" flush="false"/>
	<jsp:include page="../../libs/libsScript.jsp" flush="false" />
	<script src="/static/js/mypage/mypage-aside.js"></script>
	<script src="/static/js/mypage/mypage-mylist.js"></script>
</body>
</html>