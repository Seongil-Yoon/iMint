package multi.fclass.iMint.member.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import multi.fclass.iMint.block.dto.BlockDTO;

class MemberDTOTest {

	@Test
	void MemberDtoTest() {

		// given
		Integer mbNo = 10000;
		String mbId = "test_123456";
		String mbProvider = "test";
		String mbGuard = null;
		String mbNick = "guard_test";
		String mbEmail = "guard_test@gmail.com";
		LocalDateTime mbJoinDate = null;
		String mbInterest = "완구";
		String mbLocation = "seoul";
		Integer mbRatingsTotal = 10;
		String mbPin = "123456";
		String mbThumbnail = null;
		Boolean mbIsdelete = false;
		Role mbRole = Role.GUARD;

		// when
		MemberDTO dto = new MemberDTO(mbNo, mbId, mbProvider, mbGuard, mbNick, mbEmail, mbJoinDate, mbInterest, mbLocation, mbRatingsTotal, mbPin, mbThumbnail, mbIsdelete, mbRole);
		
		// then
		assertThat(dto.getMbId(), equalTo(mbId));
		assertThat(dto.getMbGuard(), equalTo(mbGuard));				
		assertThat(dto.getMbNick(), equalTo(mbNick));
		assertThat(dto.getMbRole(), equalTo(mbRole));			
	}

}
