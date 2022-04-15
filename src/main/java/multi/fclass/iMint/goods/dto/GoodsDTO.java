package multi.fclass.iMint.goods.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class GoodsDTO {

	// 상품ID
	private Integer goodsId;

	// 판매자ID
	@NonNull
	private String sellerId;

	// 닉네임
	@NonNull
	private String sellerNick;

	// 글제목
	@NonNull
	private String goodsTitle;

	// 글내용
	private String goodsContent;

	// 가격
	@NonNull
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

}