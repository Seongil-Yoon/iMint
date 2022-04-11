package multi.fclass.iMint.goods.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class GoodsDTO {

	// 상품ID
	private Integer goodsId;

	// 판매자ID
	private String mbId;

	// 글제목
	private String goodsTitle;

	// 글내용
	private String goodsContent;

	// 가격
	private Long goodsPrice;

	// 카테고리
	private String goodsCategory;

	// 흥정가능여부
	private Boolean goodsSuggestible;

	// 거래지역
	private String goodsLocation;

	// 판매등록일자
	private LocalDateTime goodsCreateDate;

	// 거래상태
	private String goodsStatus;

	// 상품 삭제 여부
	private Boolean goodsIsdelete;

	public GoodsDTO() {
	}

	public GoodsDTO(Integer goodsId, String mbId, String goodsTitle, String goodsContent, Long goodsPrice,
			String goodsCategory, Boolean goodsSuggestible, String goodsLocation, LocalDateTime goodsCreateDate,
			String goodsStatus, Boolean goodsIsdelete) {
		this.goodsId = goodsId;
		this.mbId = mbId;
		this.goodsTitle = goodsTitle;
		this.goodsContent = goodsContent;
		this.goodsPrice = goodsPrice;
		this.goodsCategory = goodsCategory;
		this.goodsSuggestible = goodsSuggestible;
		this.goodsLocation = goodsLocation;
		this.goodsCreateDate = goodsCreateDate;
		this.goodsStatus = goodsStatus;
		this.goodsIsdelete = goodsIsdelete;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public String getGoodsContent() {
		return goodsContent;
	}

	public void setGoodsContent(String goodsContent) {
		this.goodsContent = goodsContent;
	}

	public Long getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public Boolean getGoodsSuggestible() {
		return goodsSuggestible;
	}

	public void setGoodsSuggestible(Boolean goodsSuggestible) {
		this.goodsSuggestible = goodsSuggestible;
	}

	public String getGoodsLocation() {
		return goodsLocation;
	}

	public void setGoodsLocation(String goodsLocation) {
		this.goodsLocation = goodsLocation;
	}

	public LocalDateTime getGoodsCreateDate() {
		return goodsCreateDate;
	}

	public void setGoodsCreateDate(LocalDateTime goodsCreateDate) {
		this.goodsCreateDate = goodsCreateDate;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Boolean getGoodsIsdelete() {
		return goodsIsdelete;
	}

	public void setGoodsIsdelete(Boolean goodsIsdelete) {
		this.goodsIsdelete = goodsIsdelete;
	}

	@Override
	public String toString() {
		return "GoodsDTO [goodsId=" + goodsId + ", mbId=" + mbId + ", goodsTitle=" + goodsTitle + ", goodsContent="
				+ goodsContent + ", goodsPrice=" + goodsPrice + ", goodsCategory=" + goodsCategory
				+ ", goodsSuggestible=" + goodsSuggestible + ", goodsLocation=" + goodsLocation + ", goodsCreateDate="
				+ goodsCreateDate + ", goodsStatus=" + goodsStatus + ", goodsIsdelete=" + goodsIsdelete + "]";
	}

}