package multi.fclass.iMint.chat.dto;

import org.springframework.stereotype.Component;

@Component
public class ChatRoomInfoDTO {
	
	// 구매희망자 ID
	private String buyerId; 
	
	// 구매희망자 닉네임
	private String buyerNick;
	
	// 판매자 ID
	private String sellerId;
	
	// 판매자 닉네임
	private String sellerNick;
	
	// 흥정가능여부
	private Boolean goodsSuggestible;
	
	
}
