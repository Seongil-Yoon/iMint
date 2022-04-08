package multi.fclass.iMint.transaction.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class TransactionDTO {

	// 거래ID
	private Integer trxId;

	// 채팅방ID
	private Integer chatRoomId;

	// 예약완료시간
	private Timestamp trxResrvDate;

	// 예약취소시간
	private Boolean trxIsdelete;

	// 거래완료시간
	private Timestamp trxCompleteDate;

	public TransactionDTO() {
	}

	public TransactionDTO(Integer trxId, Integer chatRoomId, Timestamp trxResrvDate, Boolean trxIsdelete,
			Timestamp trxCompleteDate) {
		this.trxId = trxId;
		this.chatRoomId = chatRoomId;
		this.trxResrvDate = trxResrvDate;
		this.trxIsdelete = trxIsdelete;
		this.trxCompleteDate = trxCompleteDate;
	}

	public Integer getTrxId() {
		return trxId;
	}

	public void setTrxId(Integer trxId) {
		this.trxId = trxId;
	}

	public Integer getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}

	public Timestamp getTrxResrvDate() {
		return trxResrvDate;
	}

	public void setTrxResrvDate(Timestamp trxResrvDate) {
		this.trxResrvDate = trxResrvDate;
	}

	public Boolean getTrxIsdelete() {
		return trxIsdelete;
	}

	public void setTrxIsdelete(Boolean trxIsdelete) {
		this.trxIsdelete = trxIsdelete;
	}

	public Timestamp getTrxCompleteDate() {
		return trxCompleteDate;
	}

	public void setTrxCompleteDate(Timestamp trxCompleteDate) {
		this.trxCompleteDate = trxCompleteDate;
	}

	@Override
	public String toString() {
		return "Transaction [trxId=" + trxId + ", chatRoomId=" + chatRoomId + ", trxResrvDate=" + trxResrvDate
				+ ", trxIsdelete=" + trxIsdelete + ", trxCompleteDate=" + trxCompleteDate + "]";
	}

}