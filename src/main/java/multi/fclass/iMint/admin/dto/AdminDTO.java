package multi.fclass.iMint.admin.dto;

import org.springframework.stereotype.Component;

@Component
public class AdminDTO {

	// 관리자아이디
	private String adminId;

	// 관리자비밀번호
	private String adminPw;

	public AdminDTO() {
	}

	public AdminDTO(String adminId, String adminPw) {
		this.adminId = adminId;
		this.adminPw = adminPw;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	@Override
	public String toString() {
		return "AdminDTO [adminId=" + adminId + ", adminPw=" + adminPw + "]";
	}

}
