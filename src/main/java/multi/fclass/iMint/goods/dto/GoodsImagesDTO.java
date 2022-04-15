package multi.fclass.iMint.goods.dto;

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
public class GoodsImagesDTO {

	// 상품이미지ID
	private Integer goodsImagesId;

	// 상품ID
	@NonNull
	private Integer goodsId;

	// 이미지파일경로
	private String goodsImagesPath;

	// 대표이미지여부
	private Boolean goodsImagesThumbnail;

	// 파일원본이름
	private String goodsImagesOriginname;

	// 이미지 삭제여부
	private Boolean goodsImagesIsdelete;

}