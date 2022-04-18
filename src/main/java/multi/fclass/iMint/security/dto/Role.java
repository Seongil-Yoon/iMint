package multi.fclass.iMint.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Junming, Yang
 *
 */

@Getter
@RequiredArgsConstructor
public enum Role {

	GEUST("ROLE_GEUST", "비회원"),
	UN_GUARD("ROLE_uncerti_GUARD","미인증 보호자"),
	UN_CHILD("ROLE_uncerti_CHILD","미인증 아이"),
	GUARD("ROLE_GUARD","보호자"),
	CHILD("ROLE_CHILD","아이"),
	ADMIN("ROLE_ADMIN","관리자");
	
    private final String key;
    private final String title;
}