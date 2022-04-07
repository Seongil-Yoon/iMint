package multi.fclass.carrot.chat.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class ChatMessageDTO {

	// 채팅 메세지ID
	private Integer chatMessageId;

	// 채팅방ID
	private Integer chatRoomId;

	// 메세지 전송 회원ID
	private String mbId;

	// 메세지 전송 시간
	private Timestamp chatMessageCreateDate;

	// 채팅 메세지
	private String chatMessageContent;

	// 채팅 메세지 삭제 여부
	private Boolean chatMessageIsdelete;

	public ChatMessageDTO() {
	}

	public ChatMessageDTO(Integer chatMessageId, Integer chatRoomId, String mbId, Timestamp chatMessageCreateDate,
			String chatMessageContent, Boolean chatMessageIsdelete) {
		this.chatMessageId = chatMessageId;
		this.chatRoomId = chatRoomId;
		this.mbId = mbId;
		this.chatMessageCreateDate = chatMessageCreateDate;
		this.chatMessageContent = chatMessageContent;
		this.chatMessageIsdelete = chatMessageIsdelete;
	}

	public Integer getChatMessageId() {
		return chatMessageId;
	}

	public void setChatMessageId(Integer chatMessageId) {
		this.chatMessageId = chatMessageId;
	}

	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public Timestamp getChatMessageCreateDate() {
		return chatMessageCreateDate;
	}

	public void setChatMessageCreateDate(Timestamp chatMessageCreateDate) {
		this.chatMessageCreateDate = chatMessageCreateDate;
	}

	public String getChatMessageContent() {
		return chatMessageContent;
	}

	public void setChatMessageContent(String chatMessageContent) {
		this.chatMessageContent = chatMessageContent;
	}

	public Boolean getChatMessageIsdelete() {
		return chatMessageIsdelete;
	}

	public void setChatMessageIsdelete(Boolean chatMessageIsdelete) {
		this.chatMessageIsdelete = chatMessageIsdelete;
	}

	@Override
	public String toString() {
		return "ChatMessageDTO [chatMessageId=" + chatMessageId + ", chatRoomId=" + chatRoomId + ", mbId=" + mbId
				+ ", chatMessageCreateDate=" + chatMessageCreateDate + ", chatMessageContent=" + chatMessageContent
				+ ", chatMessageIsdelete=" + chatMessageIsdelete + "]";
	}

}
