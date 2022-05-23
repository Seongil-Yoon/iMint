package multi.fclass.iMint.block.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jungmin, Yang
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class BlockDTO {

	// 차단ID
	private Integer blockId;

	// 회원ID
	private String mbId;

	// 차단회원ID
	private String mbId2;

	// 차단일자
	private LocalDateTime blockDate;

	// 차단 취소 여부
	private Boolean blockIsdelete;

	@Override
	public String toString() {
		return "Block [blockId=" + blockId + ", mbId=" + mbId + ", mbId2=" + mbId2 + ", blockDate=" + blockDate
				+ ", blockIsdelete=" + blockIsdelete + "]";
	}

}