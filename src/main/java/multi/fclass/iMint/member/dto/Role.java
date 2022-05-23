package multi.fclass.iMint.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Jungmin, Yang
 *
 */

@Getter
@RequiredArgsConstructor
public enum Role {

	// 관리자가 강퇴한 회원(재가입 불가)
	GUEST("ROLE_GUEST", "강퇴 회원"),
	
	// 회원가입 미완료, 자진 탈퇴 
	UN_GUARD("ROLE_uncerti_GUARD","미인증 보호자"), 
	UN_CHILD("ROLE_uncerti_CHILD","미인증 아이"),
	
	// 회원가입 완료 회원 
	GUARD("ROLE_GUARD","보호자"),
	CHILD("ROLE_CHILD","아이"),
	
	// 관리자
	ADMIN("ROLE_ADMIN","관리자");
	
    private final String key;
    private final String title;
}