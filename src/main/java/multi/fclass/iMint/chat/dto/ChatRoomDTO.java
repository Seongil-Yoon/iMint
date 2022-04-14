package multi.fclass.iMint.chat.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Component
public class ChatRoomDTO {

	// chatroom 테이블
	// 채팅방ID
	private Integer id; 

	// 상품ID
	private Integer goodsId; 

	// 판매자ID
	private String sellerId;

	// 판매자 닉네임
	private String sellerNick;

	// 구매희망자 ID
	private String buyerId;
  
	// 구매희망자 닉네임
	private String buyerNick;

	// 채팅대화저장파일경로명
	private String fileName;

	// 채팅방삭제여부
	private String isdelete;

	// 예약시간 
	private LocalDateTime resrvDate;

	// 채팅시작시간
	private LocalDateTime createdDate;

	// Not in chatroom DB
	// goods 테이블에서 받아온 값 
	//가격흥정여부
	private Boolean goodsSuggestible;
	
	// 상품명
	private String goodsTitle; 

	//채팅내용
	private String content;
	
	// 채팅메세지 보낸시간
	private String sendTime;
	
	// 채팅보낸 사람
	private String senderName;

	// in chatroom DB
	public ChatRoomDTO(Integer id, Integer goodsId, String sellerId, String sellerNick, String buyerId,
			String buyerNick, String fileName, String isdelete, LocalDateTime resrvDate, LocalDateTime createdDate) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.sellerId = sellerId;
		this.sellerNick = sellerNick;
		this.buyerId = buyerId;
		this.buyerNick = buyerNick;
		this.fileName = fileName;
		this.isdelete = isdelete;
		this.resrvDate = resrvDate;
		this.createdDate = createdDate;

	}
	// Not in chatroom DB
	public ChatRoomDTO(String content, String sendTime, String senderName) {

		this.content = content;
		this.sendTime = sendTime;
		this.senderName = senderName;
	}

	// allArgsConstructor
	public ChatRoomDTO(Integer id, Integer goodsId, String sellerId, String sellerNick, String buyerId,
			String buyerNick, String fileName, String isdelete, LocalDateTime resrvDate, LocalDateTime createdDate,
			Boolean goodsSuggestible, String goodsTitle, String content, String sendTime, String senderName) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.sellerId = sellerId;
		this.sellerNick = sellerNick;
		this.buyerId = buyerId;
		this.buyerNick = buyerNick;
		this.fileName = fileName;
		this.isdelete = isdelete;
		this.resrvDate = resrvDate;
		this.createdDate = createdDate;
		this.goodsSuggestible = goodsSuggestible;
		this.goodsTitle = goodsTitle;
		this.content = content;
		this.sendTime = sendTime;
		this.senderName = senderName;
	}
	
	

}
