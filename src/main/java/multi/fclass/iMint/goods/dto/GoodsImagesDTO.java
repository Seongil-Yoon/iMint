package multi.fclass.iMint.goods.dto;

import org.springframework.stereotype.Component;

@Component
public class GoodsImagesDTO {

	// 상품이미지ID
	private Integer goodsImagesId;

	// 상품ID
	private Integer goodsId;

	// 이미지파일경로
	private String goodsImagesPath;

	// 대표이미지여부
	private Boolean goodsImagesThumbnail;

	// 파일원본이름
	private String goodsImagesOriginname;

	// 이미지 삭제여부
	private Boolean goodsImagesIsdelete;

	public GoodsImagesDTO() {
	}

	public GoodsImagesDTO(Integer goodsImagesId, Integer goodsId, String goodsImagesPath, Boolean goodsImagesThumbnail,
			String goodsImagesOriginname, Boolean goodsImagesIsdelete) {
		this.goodsImagesId = goodsImagesId;
		this.goodsId = goodsId;
		this.goodsImagesPath = goodsImagesPath;
		this.goodsImagesThumbnail = goodsImagesThumbnail;
		this.goodsImagesOriginname = goodsImagesOriginname;
		this.goodsImagesIsdelete = goodsImagesIsdelete;
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

	public Boolean getGoodsImagesThumbnail() {
		return goodsImagesThumbnail;
	}

	public void setGoodsImagesThumbnail(Boolean goodsImagesThumbnail) {
		this.goodsImagesThumbnail = goodsImagesThumbnail;
	}

	public String getGoodsImagesOriginname() {
		return goodsImagesOriginname;
	}

	public void setGoodsImagesOriginname(String goodsImagesOriginname) {
		this.goodsImagesOriginname = goodsImagesOriginname;
	}

	public Boolean getGoodsImagesIsdelete() {
		return goodsImagesIsdelete;
	}

	public void setGoodsImagesIsdelete(Boolean goodsImagesIsdelete) {
		this.goodsImagesIsdelete = goodsImagesIsdelete;
	}

	@Override
	public String toString() {
		return "GoodsImagesDTO [goodsImagesId=" + goodsImagesId + ", goodsId=" + goodsId + ", goodsImagesPath="
				+ goodsImagesPath + ", goodsImagesThumbnail=" + goodsImagesThumbnail + ", goodsImagesOriginname="
				+ goodsImagesOriginname + ", goodsImagesIsdelete=" + goodsImagesIsdelete + "]";
	}

}