package multi.fclass.iMint.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GAURD("ROLE_GAURD","보호자"),
    CHILD("ROLE_CHILD","아이"),
	ADMIN("ROLE_ADMIN","관리자");
	
    private final String key;
    private final String title;
}