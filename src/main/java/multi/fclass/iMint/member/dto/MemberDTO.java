package multi.fclass.iMint.member.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {

	// 등록순서
	private Integer mbNo;

	// 회원ID
	private String mbId;

	// 보호자
	private String mbGuard;

	// 닉네임
	private String mbNick;

	// 이메일
	private String mbEmail;

	// 가입일자
	private LocalDateTime mbJoinDate;

	// 관심사
	private String mbInterest;

	// 내 동네
	private String mbLocation;

	// 평가점수
	private Integer mbRatingsTotal;

	// 아이등록인증PIN
	private String mbPin;

	// 프로필사진
	private String mbThumbnail;

	// 탈퇴여부
	private Boolean mbIsdelete;

	public MemberDTO() {
	}

	public MemberDTO(Integer mbNo, String mbId, String mbGuard, String mbNick, String mbEmail, LocalDateTime mbJoinDate,
			String mbInterest, String mbLocation, Integer mbRatingsTotal, String mbPin, String mbThumbnail,
			Boolean mbIsdelete) {
		this.mbNo = mbNo;
		this.mbId = mbId;
		this.mbGuard = mbGuard;
		this.mbNick = mbNick;
		this.mbEmail = mbEmail;
		this.mbJoinDate = mbJoinDate;
		this.mbInterest = mbInterest;
		this.mbLocation = mbLocation;
		this.mbRatingsTotal = mbRatingsTotal;
		this.mbPin = mbPin;
		this.mbThumbnail = mbThumbnail;
		this.mbIsdelete = mbIsdelete;
	}

	public Integer getMbNo() {
		return mbNo;
	}

	public void setMbNo(Integer mbNo) {
		this.mbNo = mbNo;
	}

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getMbGuard() {
		return mbGuard;
	}

	public void setMbGuard(String mbGuard) {
		this.mbGuard = mbGuard;
	}

	public String getMbNick() {
		return mbNick;
	}

	public void setMbNick(String mbNick) {
		this.mbNick = mbNick;
	}

	public String getMbEmail() {
		return mbEmail;
	}

	public void setMbEmail(String mbEmail) {
		this.mbEmail = mbEmail;
	}

	public LocalDateTime getMbJoinDate() {
		return mbJoinDate;
	}

	public void setMbJoinDate(LocalDateTime mbJoinDate) {
		this.mbJoinDate = mbJoinDate;
	}

	public String getMbInterest() {
		return mbInterest;
	}

	public void setMbInterest(String mbInterest) {
		this.mbInterest = mbInterest;
	}

	public String getMbLocation() {
		return mbLocation;
	}

	public void setMbLocation(String mbLocation) {
		this.mbLocation = mbLocation;
	}

	public Integer getMbRatingsTotal() {
		return mbRatingsTotal;
	}

	public void setMbRatingsTotal(Integer mbRatingsTotal) {
		this.mbRatingsTotal = mbRatingsTotal;
	}

	public String getMbPin() {
		return mbPin;
	}

	public void setMbPin(String mbPin) {
		this.mbPin = mbPin;
	}

	public String getMbThumbnail() {
		return mbThumbnail;
	}

	public void setMbThumbnail(String mbThumbnail) {
		this.mbThumbnail = mbThumbnail;
	}

	public Boolean getMbIsdelete() {
		return mbIsdelete;
	}

	public void setMbIsdelete(Boolean mbIsdelete) {
		this.mbIsdelete = mbIsdelete;
	}

	@Override
	public String toString() {
		return "Member [mbNo=" + mbNo + ", mbId=" + mbId + ", mbGuard=" + mbGuard + ", mbNick=" + mbNick + ", mbEmail="
				+ mbEmail + ", mbJoinDate=" + mbJoinDate + ", mbInterest=" + mbInterest + ", mbLocation=" + mbLocation
				+ ", mbRatingsTotal=" + mbRatingsTotal + ", mbPin=" + mbPin + ", mbThumbnail=" + mbThumbnail
				+ ", mbIsdelete=" + mbIsdelete + "]";
	}

}