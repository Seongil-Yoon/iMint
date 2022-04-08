package multi.fclass.carrot.goods.dto;

import org.springframework.stereotype.Component;

@Component
public class GoodsImagesDTO {

	// 상품이미지ID
	private Integer goodsImagesId;

	// 상품ID
	private Integer goodsId;

	// 이미지파일경로
	private String goodsImagesPath;

	public GoodsImagesDTO() {
	}

	public GoodsImagesDTO(Integer goodsImagesId, Integer goodsId, String goodsImagesPath) {
		this.goodsImagesId = goodsImagesId;
		this.goodsId = goodsId;
		this.goodsImagesPath = goodsImagesPath;
	}

	public Integer getGoodsImagesId() {
		return goodsImagesId;
	}

	public void setGoodsImagesId(Integer goodsImagesId) {
		this.goodsImagesId = goodsImagesId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImagesPath() {
		return goodsImagesPath;
	}

	public void setGoodsImagesPath(String goodsImagesPath) {
		this.goodsImagesPath = goodsImagesPath;
	}

	@Override
	public String toString() {
		return "GoodsImagesDTO [goodsImagesId=" + goodsImagesId + ", goodsId=" + goodsId + ", goodsImagesPath="
				+ goodsImagesPath + "]";
	}

}