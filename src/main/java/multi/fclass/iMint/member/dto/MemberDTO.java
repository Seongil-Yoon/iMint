package multi.fclass.iMint.member.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {

	// 등록순서
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
	private String mbThumbnail;

	// 탈퇴여부
	private Boolean mbIsdelete;

	public MemberDTO() {
	}
	
	public MemberDTO(Integer mbNo, String mbId, String mbProvider ,String mbGuard, String mbNick, String mbEmail, LocalDateTime mbJoinDate,
			String mbInterest, String mbLocation, Integer mbRatingsTotal, String mbPin, String mbThumbnail,
			Boolean mbIsdelete) {
		this.mbNo = mbNo;
		this.mbId = mbId;
		this.mbProvider = mbProvider;
		this.mbGuard = mbGuard;
		this.mbNick = mbNick;
		this.mbEmail = mbEmail;
		this.mbJoinDate = mbJoinDate;
		this.mbInterest = mbInterest;
		this.mbLocation = mbLocation;
		this.mbRatingsTotal = mbRatingsTotal;
		this.mbPin = mbPin;
		this.mbThumbnail = mbThumbnail;
		this.mbIsdelete = mbIsdelete;
	}

	@Override
	public String toString() {
		return "Member [mbNo=" + mbNo + ", mbId=" + mbId + ", mbProvider=" + mbProvider + ", mbGuard=" + mbGuard + ", mbNick=" + mbNick + ", mbEmail="
				+ mbEmail + ", mbJoinDate=" + mbJoinDate + ", mbInterest=" + mbInterest + ", mbLocation=" + mbLocation
				+ ", mbRatingsTotal=" + mbRatingsTotal + ", mbPin=" + mbPin + ", mbThumbnail=" + mbThumbnail
				+ ", mbIsdelete=" + mbIsdelete + "]";
	}

}