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

	// goods_images 테이블에서 조회
	private String goodsImagesPath; // 대표 이미지 하나만 조회

	// transaction 테이블에서 조회
	private Date trxCompleteDate;

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

	public String getGoodsImagesPath() {
		return goodsImagesPath;
	}

	public void setGoodsImagesPath(String goodsImagesPath) {
		this.goodsImagesPath = goodsImagesPath;
	}

	public Date getTrxCompleteDate() {
		return trxCompleteDate;
	}

	public void setTrxCompleteDate(Timestamp trxCompleteDate) {
		this.trxCompleteDate = new Date(trxCompleteDate.getTime());
	}

}
