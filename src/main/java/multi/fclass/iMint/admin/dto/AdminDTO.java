package multi.fclass.iMint.admin.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import multi.fclass.iMint.member.dto.Role;

/**
 * @author Junming, Yang
 *
 */

@Data
@NoArgsConstructor // 기본 생성자 
@AllArgsConstructor // 전체 생성자 
@Component
public class AdminDTO {

	String mbLocation;
	
	String mbWithdrawAll;
	
	String mbCntAll;
}
