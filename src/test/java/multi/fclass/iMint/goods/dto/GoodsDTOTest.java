package multi.fclass.iMint.goods.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import lombok.NonNull;

/**
 * @author Junming, Yang
 *
 */

class GoodsDTOTest {
	
	@Test
	void GoodsDtoTest() {
	
		// given
		Integer goodsId = 10000;
		
		@NonNull
		String sellerId = "test_123456";
		
		@NonNull
		String sellerNick = "test";
		
		@NonNull
		String goodsTitle = "test";
		
		String goodsContent = "test";
		
		@NonNull
		Long goodsPrice = (long) 10000;
		
		String goodsCategory = "test";
		
		Boolean goodsSuggestible = false;
		
		String goodsLocation = "seoul";
		
		LocalDateTime goodsCreateDate = null;
		
		String goodsStatus = "판매중";
		
		Boolean goodsIsdelete = false;
			
		// when
		GoodsDTO dto = new GoodsDTO(goodsId, sellerId, sellerNick, goodsTitle, goodsContent, goodsPrice, goodsCategory, goodsSuggestible, goodsLocation, goodsCreateDate, goodsStatus, goodsIsdelete);
		
		// then
		assertThat(dto.getGoodsId(), equalTo(goodsId));
		assertThat(dto.getSellerId(), equalTo(sellerId));
		assertThat(dto.getGoodsTitle(), equalTo(goodsTitle));				
		assertThat(dto.getGoodsStatus(), equalTo(goodsStatus));
		assertThat(dto.getGoodsIsdelete(), equalTo(goodsIsdelete));			
		
	}
	
}
