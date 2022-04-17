package multi.fclass.iMint.member.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import multi.fclass.iMint.goods.dto.GoodsDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MemberDTO {

	// 등록순서
    private Integer mbNo;

    // 회원ID
    private String mbId;

    // SNS사이트
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

    // 멤버권한
    private String mbRole;

    @Builder
	public MemberDTO(String mbId, String mbNick, String mbLocation) {
		super();
		this.mbId = mbId;
		this.mbNick = mbNick;
		this.mbLocation = mbLocation;
	}
    
    
}