package multi.fclass.iMint.block.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

/**
 * @author Junming, Yang
 *
 */

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

	public BlockDTO() {
	}

	public BlockDTO(Integer blockId, String mbId, String mbId2, LocalDateTime blockDate, Boolean blockIsdelete) {
		this.blockId = blockId;
		this.mbId = mbId;
		this.mbId2 = mbId2;
		this.blockDate = blockDate;
		this.blockIsdelete = blockIsdelete;
	}

	public Integer getBlockId() {
		return blockId;
	}

	public void setBlockId(Integer blockId) {
		this.blockId = blockId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getMbId2() {
		return mbId2;
	}

	public void setMbId2(String mbId2) {
		this.mbId2 = mbId2;
	}

	public LocalDateTime getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(LocalDateTime blockDate) {
		this.blockDate = blockDate;
	}

	public Boolean getBlockIsdelete() {
		return blockIsdelete;
	}

	public void setBlockIsdelete(Boolean blockIsdelete) {
		this.blockIsdelete = blockIsdelete;
	}

	@Override
	public String toString() {
		return "Block [blockId=" + blockId + ", mbId=" + mbId + ", mbId2=" + mbId2 + ", blockDate=" + blockDate
				+ ", blockIsdelete=" + blockIsdelete + "]";
	}

}