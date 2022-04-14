package multi.fclass.iMint.security.model;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * 세션에 저장하려면 직렬화를 해야 하는데
 * User 엔티티는 추후 변경사항이 있을 수 있기 때문에
 * 직렬화를 하기 위한 별도의 SessionUser 클래스 생성
 */
@Getter
public class SessionUser implements Serializable {
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

	public SessionUser(User user) {
		this.mbNo = user.getMbNo();
		this.mbId = user.getMbId();
		this.mbProvider = user.getMbProvider();
		this.mbGuard = user.getMbGuard();
		this.mbNick = user.getMbNick();
		this.mbEmail = user.getMbEmail();
		this.mbJoinDate = user.getMbJoinDate();
		this.mbInterest = user.getMbInterest();
		this.mbLocation = user.getMbLocation();
		this.mbRatingsTotal = user.getMbRatingsTotal();
		this.mbPin = user.getMbPin();
		this.mbThumbnail = user.getMbThumbnail();
		this.mbIsdelete = user.getMbIsdelete();
		this.mbRole = user.getMbRole(); // 제일 중요한 정보 
		

	}
    
}

// 직렬화: https://sorjfkrh5078.tistory.com/89