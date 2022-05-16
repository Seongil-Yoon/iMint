package multi.fclass.iMint.block.dto;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author Haeyeon Jeon
 *
 */

@Getter
public class BlockListDTO {
	private int id; // 채팅방 ID
	private String opponentId; // 대화상대 ID
	private String opponentNick; // 대화상대 닉네임
	@Getter(AccessLevel.NONE)
	private String opponentThumbnail; // 대화상대 썸네일 저장 경로
	private String message; // 마지막 메세지 내용
	private LocalDateTime lastUpdateDate; // 마지막 업데이트 일시(채팅방 생성일시 or 마지막 메세지 전송일시)
	
	public String getOpponentThumbnail() {
		if (opponentThumbnail == null || opponentThumbnail.equals("")) {
			return "/static/images/default-icon.jpeg";
		} else {
			return opponentThumbnail;
		}
	}
}
