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
public class MypageDTO {

	private String category; // "buy" or "sell"
	private String sellerNick; // 판매자 닉네임
	private int goodsId; // 판매글 ID
	private String goodsTitle; // 판매글 제목
	private int goodsPrice; // 판매 가격
	private String goodsLocation; // 판매 지역
	private LocalDateTime goodsCreateDate; // 판매글 등록일시
	@Getter(AccessLevel.NONE)
	private String goodsImagesPath; // 대표 이미지 저장 경로
	private String goodsImagesOriginname; // 대표 이미지 원래 이름
	private int wishes; // 관심등록수
	private LocalDateTime lastUpdateDate; // 마지막 업데이트 일시(관심등록일, 예약일, 거래완료일 등)

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
