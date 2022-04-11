package multi.fclass.iMint.mypage.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GhostFairy
 *
 */
public class CompleteListDTO {

	// SQL로 생성
	private String category; // "buy" or "sell"

	// goods 테이블에서 조회
	private int goodsId; // 판매글 ID

	private String goodsTitle; // 판매글 제목

	private int goodsPrice; // 판매 가격

	private String goodsLocation; // 판매 지역

	// goods_images 테이블에서 조회
	private String goodsImagesPath; // 대표 이미지 저장 경로

	private String goodsImagesOriginname; // 대표 이미지 원래 이름

	// wishlist 테이블에서 조회
	private int wishes; // 관심 등록 수

	// transaction 테이블에서 조회
	private Date trxCompleteDate; // 거래 완료일시

	// rating 테이블에서 조회
	private int ratingId; // 평가하기 ID

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsLocation() {
		return goodsLocation;
	}

	public void setGoodsLocation(String goodsLocation) {
		this.goodsLocation = goodsLocation;
	}

	public String getGoodsImagesPath() {
		return goodsImagesPath;
	}

	public void setGoodsImagesPath(String goodsImagesPath) {
		this.goodsImagesPath = goodsImagesPath;
	}

	public String getGoodsImagesOriginname() {
		return goodsImagesOriginname;
	}

	public void setGoodsImagesOriginname(String goodsImagesOriginname) {
		this.goodsImagesOriginname = goodsImagesOriginname;
	}

	public int getWishes() {
		return wishes;
	}

	public void setWishes(int wishes) {
		this.wishes = wishes;
	}

	public Date getTrxCompleteDate() {
		return trxCompleteDate;
	}

	public void setTrxCompleteDate(Timestamp trxCompleteDate) {
		this.trxCompleteDate = new Date(trxCompleteDate.getTime());
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

}
