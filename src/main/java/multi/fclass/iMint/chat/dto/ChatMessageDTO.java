package multi.fclass.iMint.chat.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class ChatMessageDTO {

	// 채팅 메세지ID
	private Integer chatMessageId;

	// 채팅방ID
	private Integer chatRoomId;

	// 메세지 전송 시간
	private Timestamp chatMessageCreateDate;

	// 채팅 메세지
	private String chatMessageContent;

	public ChatMessageDTO() {
	}

	public ChatMessageDTO(Integer chatMessageId, Integer chatRoomId, Timestamp chatMessageCreateDate,
			String chatMessageContent) {
		this.chatMessageId = chatMessageId;
		this.chatRoomId = chatRoomId;
		this.chatMessageCreateDate = chatMessageCreateDate;
		this.chatMessageContent = chatMessageContent;
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

	@Override
	public String toString() {
		return "ChatMessageDTO [chatMessageId=" + chatMessageId + ", chatRoomId=" + chatRoomId
				+ ", chatMessageCreateDate=" + chatMessageCreateDate + ", chatMessageContent=" + chatMessageContent
				+ "]";
	}

}
