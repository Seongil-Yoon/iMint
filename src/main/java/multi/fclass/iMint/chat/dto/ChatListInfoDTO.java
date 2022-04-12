package multi.fclass.iMint.chat.dto;

import java.time.LocalDateTime;

public class ChatListInfoDTO {

	// 채팅방ID
	private Integer chatRoomId;

	// 구매희망자 ID
	private String buyerId; 

	// 구매희망자 닉네임
	private String buyerNick;
	
	// 구매희망자 프로필사진 경로
	private String buyerThumbnail;
	
	// 판매자 ID
	private String sellerId;

	// 판매자 닉네임
	private String sellerNick;

	// 구매희망자 프로필사진 경로
	private String sellerThumbnail;

	// 상품 글제목
	private String goodsTitle;
	
	// 상품 가격
	private Long goodsPrice;
	
	// 상품사진 경로
	private String goodsImagesPath;
	
	// 채팅방삭제여부
	private Boolean chatRoomIsdelete;
	
	// 채팅시작시간
	private LocalDateTime chatRoomCreateDate;
	

}
