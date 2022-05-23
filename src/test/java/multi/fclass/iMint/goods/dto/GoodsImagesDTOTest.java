package multi.fclass.iMint.goods.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import lombok.NonNull;

/**
 * @author Junming, Yang
 *
 */

class GoodsImagesDTOTest {
	
	@Test
	void GoodsImagesDtoTest() {
	
		// given

		Integer goodsImagesId = 10000;
		
		@NonNull
		Integer goodsId = 10000;
		
		String goodsImagesPath = "test_123456";
		
		Boolean goodsImagesThumbnail = false;
		
		String goodsImagesOriginname = "sample.png";
		
		Boolean goodsImagesIsdelete = false;
			
		// when
		GoodsImagesDTO dto = new GoodsImagesDTO(goodsImagesId, goodsId, goodsImagesPath, goodsImagesThumbnail, goodsImagesOriginname, goodsImagesIsdelete);
		
		// then
		assertThat(dto.getGoodsImagesId(), equalTo(goodsImagesId));
		assertThat(dto.getGoodsId(), equalTo(goodsId));
		assertThat(dto.getGoodsImagesPath(), equalTo(goodsImagesPath));
		assertThat(dto.getGoodsImagesThumbnail(), equalTo(goodsImagesThumbnail));				
		assertThat(dto.getGoodsImagesOriginname(), equalTo(goodsImagesOriginname));
		assertThat(dto.getGoodsImagesIsdelete(), equalTo(goodsImagesIsdelete));			
		
	}
	
}
