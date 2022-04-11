package multi.fclass.iMint.mypage.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GhostFairy
 *
 */
public class CompleteListDTO {

	// SQL로 생성
	private String category;

	// goods 테이블에서 조회
	private int goodsId;

	private String goodsTitle;

	private int goodsPrice;

	private String goodsLocation;

	// goods_images 테이블에서 조회
	private String goodsImagesPath; // 대표 이미지 하나만 조회

	// wishlist 테이블에서 조회
	private int wishes;

	// transaction 테이블에서 조회
	private Date trxCompleteDate;

	// rating 테이블에서 조회
	private int ratingId;

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
