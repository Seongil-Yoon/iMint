package multi.fclass.iMint.chat.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GhostFairy
 *
 */
@Getter
@Setter
public class ChatMessageDTO {

	private int id; // 메세지 ID
	private int chatroomId; // 채팅방 ID
	private String senderId; // 메세지 전송 회원 ID
	private String senderNick;
	private String message; // 메세지 내용
	private boolean isRead; // 메세지 읽음여부
	private LocalDateTime sendDate; // 메세지 전송일시
	private boolean isDeleted; // 메세지 삭제여부

}
