package multi.fclass.iMint.security.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본 생성자 
public class User {
	
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
	private String mbThumbnail;

	// 탈퇴여부
	private Boolean mbIsdelete;
	
	// 권한
	private Role mbRole;

	// 전체생성자 
	public User(Integer mbNo, String mbId, String mbProvider ,String mbGuard, String mbNick, String mbEmail, LocalDateTime mbJoinDate,
			String mbInterest, String mbLocation, Integer mbRatingsTotal, String mbPin, String mbThumbnail,
			Boolean mbIsdelete, Role mbRole) {
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
		this.mbRole = mbRole;
	}
	
	// 필수값만 있는 생성자 (가입시) 
	@Builder
    public User(String mbId, String mbProvider,String mbGuard, String mbNick, String mbEmail
    		, String mbLocation, String mbPin, Role mbRole) {
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
    public User update(String mbGuard, String mbInterest, String mbLocation, Integer mbRatingsTotal, String mbPin, String mbThumbnail,
			Boolean mbIsdelete) {
        this.mbGuard = mbGuard;
        this.mbInterest = mbInterest;
        this.mbLocation = mbLocation;
        this.mbRatingsTotal = mbRatingsTotal;
        this.mbPin = mbPin;
        this.mbThumbnail = mbThumbnail;
        this.mbIsdelete = mbIsdelete;
        return this;
    }
	
    public String getRoleKey() {
        return this.mbRole.getKey();
    }
}