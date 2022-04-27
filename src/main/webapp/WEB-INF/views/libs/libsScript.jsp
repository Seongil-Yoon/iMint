<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 외부 라이브러리 -->
<!-- jQuery WebJars -->
<script src="/webjars/jquery/jquery.min.js"></script>

<!-- Bootstrap WebJars -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<!-- sweetalert CDN libs -->
<script src="/static/libs/sweetalert.min.js"></script>

<!-- 공통 스크립트 -->

<!-- 헤더 스크립트 -->
<script src="/static/js/header.js"></script>
<script src="/static/js/header-search.js"></script>

<!-- 채팅 스크립트 -->
<script>
	let chatboxMyId = "${sessionScope.memberDTO.mbId}";
	let chatboxMyRole = "${sessionScope.memberDTO.mbRole}";
</script>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script src="/static/js/chat/chatbox.js"></script>