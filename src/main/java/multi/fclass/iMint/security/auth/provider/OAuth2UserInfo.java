package multi.fclass.iMint.security.auth.provider;

/**
 * @author Jungmin, Yang
 *
 */

// OAuth2.0 제공자(구글, 네이버, 카카오)들 마다 응답해주는 속성값이 달라서 공통 타입으로 제작.
public interface OAuth2UserInfo {	
	String getMbId();
	String getMbProvider();
	String getMbNick();
	String getMbEmail();
	
	String getAgeRange(); // 연령대는 DB에 저장은 하지 않음. 보호자, 아이 구분 용.
}