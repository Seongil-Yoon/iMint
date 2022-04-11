package multi.fclass.iMint.wishlist.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class WishlistDTO {

	// 관심ID
	private Integer wishlistId;

	// 회원ID
	private String mbId;

	// 상품ID
	private Integer goodsId;

	// 관심등록일자
	private LocalDateTime wishlistCreateDate;

	// 관심철회여부
	private Boolean wishlistWithdraw;

	public WishlistDTO() {
	}

	public WishlistDTO(Integer wishlistId, String mbId, Integer goodsId, LocalDateTime wishlistCreateDate,
			Boolean wishlistWithdraw) {
		this.wishlistId = wishlistId;
		this.mbId = mbId;
		this.goodsId = goodsId;
		this.wishlistCreateDate = wishlistCreateDate;
		this.wishlistWithdraw = wishlistWithdraw;
	}

	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public LocalDateTime getWishlistCreateDate() {
		return wishlistCreateDate;
	}

	public void setWishlistCreateDate(LocalDateTime wishlistCreateDate) {
		this.wishlistCreateDate = wishlistCreateDate;
	}

	public Boolean getWishlistWithdraw() {
		return wishlistWithdraw;
	}

	public void setWishlistWithdraw(Boolean wishlistWithdraw) {
		this.wishlistWithdraw = wishlistWithdraw;
	}

	@Override
	public String toString() {
		return "Wishlist [wishlistId=" + wishlistId + ", mbId=" + mbId + ", goodsId=" + goodsId
				+ ", wishlistCreateDate=" + wishlistCreateDate + ", wishlistWithdraw=" + wishlistWithdraw + "]";
	}

}
