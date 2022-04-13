package multi.fclass.iMint.mypage.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author GhostFairy
 *
 */
public class SellingListDTO {

	private String category; // "wait" or "resrv"
	private int goodsId; // 판매글 ID
	private String goodsTitle; // 판매글 제목
	private int goodsPrice; // 판매 가격
	private String goodsLocation; // 판매 지역
	private String goodsImagesPath; // 대표 이미지 저장 경로
	private String goodsImagesOriginname; // 대표 이미지 원래 이름
	private Date goodsCreateDate; // 판매글 등록일시

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

	public Date getGoodsCreateDate() {
		return goodsCreateDate;
	}

	public void setGoodsCreateDate(Timestamp goodsCreateDate) {
		this.goodsCreateDate = new Date(goodsCreateDate.getTime());
	}

}
