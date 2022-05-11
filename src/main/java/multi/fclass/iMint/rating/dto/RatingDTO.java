package multi.fclass.iMint.rating.dto;

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
public class RatingDTO {

	private int trxId; // 거래 ID
	private Integer ratingId; // 평가 ID
	private String raterId; // 평가자 ID
	private Integer ratingScore; // 평가점수
	private LocalDateTime ratingCreateDate; // 평가일시
	private int goodsId; // 판매글 ID
	private String goodsTitle; // 판매글 제목
	@Getter(AccessLevel.NONE)
	private String goodsImagesPath; // 대표 이미지 저장 경로
	private String opponentId; // 거래상대 ID
	private String opponentNick; // 거래상대 닉네임

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
