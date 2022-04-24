<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>iMint :: 채팅 목록(임시)</title>
<jsp:include page="../libs/libsStyles.jsp" flush="false" />
<link rel="stylesheet" href="/static/css/main.css" />
</head>

<body>
	<jsp:include page="../include/header.jsp" flush="false" />

	<table>
		<c:forEach items="${ ChatroomList }" var="Chatroom">
			<tr>
				<td><img src="/static/${ Chatroom.opponentThumbnail }"></td>
				<td>${ Chatroom.opponentNick }</td>
				<td>${ Chatroom.goodsLocation }</td>
				<td>${ Chatroom.message }</td>
				<td>${ Chatroom.lastUpdateDate }</td>
				<td><a href="/chat/chatroom?id=${ Chatroom.id }">이동</a></td>
				<td><a href="/goods/detail?goodsId=${ Chatroom.goodsId }"><img src="${ Chatroom.goodsImagesPath }"></a></td>
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="../libs/libsScript.jsp" flush="false" />
</body>

</html>