package multi.fclass.iMint.chat.dto;

import java.sql.Timestamp;
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
	
	// 정민 추가구현
	// not int DB
	private String content; 
	private String sendTime; 
	//private String senderName; 
	//private String goodsTitle;

	public ChatRoomDTO() {
	}

	public ChatRoomDTO(Integer chatRoomId, Integer goodsId, String mbId, LocalDateTime chatRoomCreateDate,
			Boolean chatRoomIsdelete) {
		this.chatRoomId = chatRoomId;
		this.goodsId = goodsId;
		this.mbId = mbId;
		this.chatRoomCreateDate = chatRoomCreateDate;
		this.chatRoomIsdelete = chatRoomIsdelete;
	}
	//정민 추가구현
	public ChatRoomDTO(String content, String sendTime) {
		this.content = content; 
		this.sendTime = sendTime;
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

	@Override
	public String toString() {
		return "ChatRoomDTO [chatRoomId=" + chatRoomId + ", goodsId=" + goodsId + ", mbId=" + mbId
				+ ", chatRoomCreateDate=" + chatRoomCreateDate + ", chatRoomIsdelete=" + chatRoomIsdelete + "]";
	}

}
