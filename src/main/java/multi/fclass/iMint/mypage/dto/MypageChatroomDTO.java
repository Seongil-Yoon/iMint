package multi.fclass.iMint.mypage.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class MypageChatroomDTO {

	private String category; // "buy" or "sell"
	private int id; // 채팅방 ID
	private int goodsId; // 판매글 ID
	private String goodsTitle; // 판매글 제목
	private int goodsPrice; // 판매 가격
	private boolean goodsSuggestible; // 가격제안 가능여부
	private String opponentId; // 대화상대 ID
	private String opponentNick; // 대화상대 닉네임
	@Getter(AccessLevel.NONE)
	private String opponentThumbnail; // 대화상대 썸네일 저장 경로
	private String goodsLocation; // 판매 지역
	@Getter(AccessLevel.NONE)
	private String goodsImagesPath; // 대표 이미지 저장 경로
	private String goodsImagesOriginname; // 대표 이미지 원래 이름
	private String senderId; // 메세지 발신자 ID
	private String message; // 마지막 메세지 내용
	private boolean isRead; // 메세지 읽음 여부
	private LocalDateTime lastUpdateDate; // 마지막 업데이트 일시(채팅방 생성일시 or 마지막 메세지 전송일시)

	public String getOpponentThumbnail() {
		if (opponentThumbnail == null || opponentThumbnail.equals("")) {
			return "/static/images/default-icon.jpeg";
		} else {
			return opponentThumbnail;
		}
	}

	public String getGoodsImagesPath() {
		if (goodsImagesPath == null || goodsImagesPath.equals("")) {
			return "/static/images/noimage.png";
		} else {
			String out = goodsImagesPath;
			try {
				out = URLDecoder.decode(out, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return out;
		}
	}

}
