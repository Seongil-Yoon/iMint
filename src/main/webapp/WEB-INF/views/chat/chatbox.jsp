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
				<img id="chatbox-detail-goods" src="https://dummyimage.com/150" />
				<p>
					<span id="chatbox-detail-goodstitle">상품제목상품제목상품제목상품제목상품제목상품제목상품제목</span> <span
						id="chatbox-detail-goodsprice">123,456원</span>
				</p>
				<div id="chatbox-detail-goodsstatus">예약완료</div>
				<div id="chatbox-detail-suggestible">가격제안불가</div>
			</div>
			<div id="chatbox-view-trxbtns">
				<div id="chatbox-trxbtns-comptrx" class="chatbox-trxbtn">예약취소</div>
				<div id="chatbox-trxbtns-ccresrv" class="chatbox-trxbtn">거래완료</div>
			</div>
			<div id="chatbox-view-chatmessages">



				<div class="chatbox-chatmessages-chatmessage left">
					<div class="chatbox-chatmessage-message">예이</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:22</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>



				
				<div class="chatbox-chatmessages-chatmessage right">
					<div class="chatbox-chatmessage-message">예헤이</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:23</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
				<div class="chatbox-chatmessages-chatmessage left">
					<div class="chatbox-chatmessage-message">길게 쓰면 어떻게 되는지 궁금한데 뭐라고 써야 길게 써서 넘어갈지 모르겠으니까 대충 쓰고 복붙해야겠다
					</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:23</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
				<div class="chatbox-chatmessages-chatmessage right">
					<div class="chatbox-chatmessage-message">이러면 이제 겹치는거지</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:24</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
				<div class="chatbox-chatmessages-chatmessage right">
					<div class="chatbox-chatmessage-message">이제 안 겹치게 고쳤으니 안 겹치는거지</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:30</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
				<div class="chatbox-chatmessages-chatmessage left">
					<div class="chatbox-chatmessage-message">이제 채팅 내용이 많아서 넘치는거 확인하고 싶다</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:35</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
				<div class="chatbox-chatmessages-chatmessage left">
					<div class="chatbox-chatmessage-message">같은 사람이 여러번 썼을 때 margin 안 하는 건 좀 귀찮을 것 같은데 가능한가</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:35</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
				<div class="chatbox-chatmessages-chatmessage right">
					<div class="chatbox-chatmessage-message">그럼 하지 말자 ㅎㅎ</div>
					<div class="chatbox-chatmessage-chatinfo">
						<div class="chatbox-chatinfo-sendtime">오전 2:37</div>
						<div class="chatbox-chatinfo-isread">읽음</div>
					</div>
				</div>
			</div>
			<div id="chatbox-view-send">
				<textarea></textarea>
				<div id="chatbox-send-sendbtn">↑</div>
			</div>
		</div>
	</div>