package multi.fclass.iMint.rating.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class RatingDTO {

	// 평가ID
	private Integer ratingId;

	// 거래ID
	private Integer trxId;

	// 회원ID
	private String mbId;

	// 평가점수
	private Integer ratingScore;

	// 평가등록일자
	private LocalDateTime ratingCreateDate;

	public RatingDTO() {
	}

	public RatingDTO(Integer ratingId, Integer trxId, String mbId, Integer ratingScore, LocalDateTime ratingCreateDate) {
		this.ratingId = ratingId;
		this.trxId = trxId;
		this.mbId = mbId;
		this.ratingScore = ratingScore;
		this.ratingCreateDate = ratingCreateDate;
	}

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public Integer getTrxId() {
		return trxId;
	}

	public void setTrxId(Integer trxId) {
		this.trxId = trxId;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public Integer getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(Integer ratingScore) {
		this.ratingScore = ratingScore;
	}

	public LocalDateTime getRatingCreateDate() {
		return ratingCreateDate;
	}

	public void setRatingCreateDate(LocalDateTime ratingCreateDate) {
		this.ratingCreateDate = ratingCreateDate;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", trxId=" + trxId + ", mbId=" + mbId + ", ratingScore=" + ratingScore
				+ ", ratingCreateDate=" + ratingCreateDate + "]";
	}

}
