package multi.fclass.iMint.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.admin.dao.IAdminDAO;
import multi.fclass.iMint.admin.dto.AdminDTO;

/**
 * @author Junming, Yang
 *
 */

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	IAdminDAO dao;

	// 강제탈퇴 
	public void ban(String ban_members) {
		String[] ban_list = ban_members.split(",");
		for(String ban_mbId: ban_list) {
			// role을 guest로 강등 
			dao.banmember(ban_mbId);
			// 강제탈퇴된 회원이 작성한 상품 삭제 
			dao.banmembergoods(ban_mbId);
		}
	}
}
