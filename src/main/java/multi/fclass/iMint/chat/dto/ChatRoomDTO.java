package multi.fclass.iMint.chat.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ChatRoomDTO {

	// 채팅방ID
	private Integer chatRoomId;

	// 상품ID
	private Integer goodsId;

	// 구매희망회원ID
	private String mbId;

	// 채팅시작시간
	private LocalDateTime chatRoomCreateDate;

	// 채팅방삭제여부
	private Boolean chatRoomIsdelete;
	
	// 채팅방예약시간
	private LocalDateTime chatRoomResrvDate;


	public ChatRoomDTO() {
	}

	public ChatRoomDTO(Integer chatRoomId, Integer goodsId, String mbId, LocalDateTime chatRoomCreateDate,
			Boolean chatRoomIsdelete, LocalDateTime chatRoomResrvDate) {
		super();
		this.chatRoomId = chatRoomId;
		this.goodsId = goodsId;
		this.mbId = mbId;
		this.chatRoomCreateDate = chatRoomCreateDate;
		this.chatRoomIsdelete = chatRoomIsdelete;
		this.chatRoomResrvDate = chatRoomResrvDate;
	}

	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public LocalDateTime getChatRoomCreateDate() {
		return chatRoomCreateDate;
	}

	public void setChatRoomCreateDate(LocalDateTime chatRoomCreateDate) {
		this.chatRoomCreateDate = chatRoomCreateDate;
	}

	public Boolean getChatRoomIsdelete() {
		return chatRoomIsdelete;
	}

	public void setChatRoomIsdelete(Boolean chatRoomIsdelete) {
		this.chatRoomIsdelete = chatRoomIsdelete;
	}

	public LocalDateTime getChatRoomResrvDate() {
		return chatRoomResrvDate;
	}

	public void setChatRoomIsreserved(LocalDateTime chatRoomResrvDate) {
		this.chatRoomResrvDate = chatRoomResrvDate;
	}

	@Override
	public String toString() {
		return "ChatRoomDTO [chatRoomId=" + chatRoomId + ", goodsId=" + goodsId + ", mbId=" + mbId
				+ ", chatRoomCreateDate=" + chatRoomCreateDate + ", chatRoomIsdelete=" + chatRoomIsdelete
				+ ", chatRoomIsreserved=" + chatRoomResrvDate + "]";
	}



}
