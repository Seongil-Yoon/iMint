package multi.fclass.iMint.goods.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class GoodsDTO {

	// 상품ID
	private Integer goodsId;

	// 판매자ID
	private String sellerId;

	// 닉네임
	private String sellerNick;

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

	@Builder
	public GoodsDTO(Integer goodsId, String sellerId, String sellerNick, String goodsTitle, String goodsContent,
			Long goodsPrice, String goodsCategory, Boolean goodsSuggestible, String goodsLocation,
			LocalDateTime goodsCreateDate, String goodsStatus, Boolean goodsIsdelete) {
		this.goodsId = goodsId;
		this.sellerId = sellerId;
		this.sellerNick = sellerNick;
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

}