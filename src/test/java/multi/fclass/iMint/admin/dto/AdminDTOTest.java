package multi.fclass.iMint.admin.dto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;

class AdminDTOTest {

	@Test
	void adminDtoTest() {
		
		// given
		String mbLocation = "Seoul";
		String mbWithdrawAll = "ok";
		String mbCntAll = "ok";
		
		// when
		AdminDTO dto = new AdminDTO(mbLocation, mbWithdrawAll, mbCntAll);
		
		// then
		assertThat(dto.getMbLocation(), equalTo(mbLocation));
		assertThat(dto.getMbWithdrawAll(), equalTo(mbWithdrawAll));
		assertThat(dto.getMbCntAll(), equalTo(mbCntAll));		
	}

}
