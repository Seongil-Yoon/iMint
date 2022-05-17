package multi.fclass.iMint.mypage.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author GhostFairy
 *
 */

@Getter
public class MypageBlockDTO {

	private int id; // 차단 ID
	private String opponentId; // 차단상대 ID
	private String opponentNick; // 차단상대 닉네임
	@Getter(AccessLevel.NONE)
	private String opponentThumbnail; // 차단상대 썸네일 저장 경로
	private LocalDateTime blockDate; // 차단일시

	public String getOpponentThumbnail() {
		if (opponentThumbnail == null || opponentThumbnail.equals("")) {
			return "/static/images/default-icon.jpeg";
		} else {
			return opponentThumbnail;
		}
	}

}
