package multi.fclass.iMint.block.dto;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * @author Junming, Yang
 *
 */

class BlockDTOTest {

	@Test
	void blockDtoTest() {
		
		// given
		Integer blockId = 10000;
		String mbId = "test_123456";
		String mbId2 = "test_7891011";
		LocalDateTime blockDate = null;
		Boolean blockIsdelete = false;
		
		// when
		BlockDTO dto = new BlockDTO(blockId, mbId, mbId2, blockDate, blockIsdelete);
		
		// then
		assertThat(dto.getBlockId(), equalTo(blockId));
		assertThat(dto.getMbId(), equalTo(mbId));
		assertThat(dto.getMbId2(), equalTo(mbId2));				
		assertThat(dto.getBlockDate(), equalTo(blockDate));
		assertThat(dto.getBlockIsdelete(), equalTo(blockIsdelete));			
	}

}
