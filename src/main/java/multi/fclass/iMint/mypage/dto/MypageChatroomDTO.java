package multi.fclass.iMint.mypage.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class MypageChatroomDTO {

	private int id; // 채팅방 ID
	private int goodsId; // 판매글 ID
	private String opponentId; // 대화상대 ID
	private String opponentNick; // 대화상대 닉네임
	private String opponentThumbnail; // 대화상대 썸네일 저장 경로
	private String goodsLocation; // 판매 지역
	private String goodsImagesPath; // 대표 이미지 저장 경로
	private String goodsImagesOriginname; // 대표 이미지 원래 이름
	private String message; // 마지막 메세지 내용
	private LocalDateTime lastUpdateDate; // 마지막 업데이트 일시(채팅방 생성일시 or 마지막 메세지 전송일시)

	private String goodsTitle;
	private int goodsPrice;
}
