<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<div id="chatbox-openbtn">채팅</div>
	<div id="chatbox-main">
		<div id="chatbox-list">
			<div id="chatbox-list-title" class="chatbox-title">
				<span class="title">아이민트 채팅</span><span id="chatbox-close" class="chatbox-closebtn">↓</span>
			</div>
			<div id="chatbox-list-chatrooms"></div>
		</div>
		<div id="chatbox-view">
			<div id="chatbox-view-title" class="chatbox-title">
				<span class="title"></span><span id="chatview-close" class="chatbox-closebtn">←</span>
			</div>
			<div id="chatbox-view-detail">
				<img id="chatbox-detail-goods" src="" />
				<p>
					<span id="chatbox-detail-goodstitle"></span><span id="chatbox-detail-goodsprice"></span>
				</p>
				<div id="chatbox-detail-goodsstatus"></div>
				<div id="chatbox-detail-suggestible"></div>
			</div>
			<div id="chatbox-view-trxbtns"></div>
			<div id="chatbox-view-chatmessages"></div>
			<div id="chatbox-view-send">
				<textarea></textarea>
				<div id="chatbox-send-sendbtn">↑</div>
			</div>
		</div>
	</div>