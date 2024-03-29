package multi.fclass.iMint.member.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Jungmin, Yang
 *
 */

@Data
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 전체 생성자
public class MemberDTO {

	// 등록 순서
	private Integer mbNo;

	// 회원ID
	private String mbId;

	// 사이트SNS
	private String mbProvider;

	// 보호자
	private String mbGuard;

	// 닉네임
	private String mbNick;

	// 이메일
	private String mbEmail;

	// 가입일자
	private LocalDateTime mbJoinDate;

	// 관심사
	private String mbInterest;

	// 내 동네
	private String mbLocation;

	// 평가점수
	private Integer mbRatingsTotal;

	// 아이등록인증PIN
	private String mbPin;

	// 프로필사진
	@Getter(AccessLevel.NONE)
	private String mbThumbnail;

	public String getMbThumbnail() {
		if (mbThumbnail == null || mbThumbnail.equals("")) {
			return "/static/images/default-icon.jpeg";
		} else {
			return mbThumbnail;
		}
	}

	// 탈퇴여부
	private Boolean mbIsdelete;

	// 권한
	private Role mbRole;

	// 필수값만 있는 생성자 (가입시)
	@Builder
	public MemberDTO(String mbId, String mbProvider, String mbGuard, String mbNick, String mbEmail, String mbLocation,
			String mbPin, Role mbRole) {
		this.mbId = mbId;
		this.mbProvider = mbProvider;
		this.mbGuard = mbGuard;
		this.mbNick = mbNick;
		this.mbEmail = mbEmail;
		this.mbLocation = mbLocation;
		this.mbPin = mbPin;
		this.mbRole = mbRole;
	}

	// 수정 가능한 고객 정보
	public MemberDTO update(String mbGuard, String mbInterest, String mbLocation, String mbNick, Integer mbRatingsTotal,
			String mbPin, String mbThumbnail, Boolean mbIsdelete, Role mbRole) {
		this.mbGuard = mbGuard;
		this.mbInterest = mbInterest;
		this.mbLocation = mbLocation;
		this.mbNick = mbNick;
		this.mbRatingsTotal = mbRatingsTotal;
		this.mbPin = mbPin;
		this.mbThumbnail = mbThumbnail;
		this.mbIsdelete = mbIsdelete;
		this.mbRole = mbRole;
		return this;
	}

	public String getRoleKey() {
		return this.mbRole.getKey();
	}

}
