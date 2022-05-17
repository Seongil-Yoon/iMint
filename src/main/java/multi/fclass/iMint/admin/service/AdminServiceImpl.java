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

	public void ban(String ban_mbId) {
		String[] ban_list = ban_mbId.split(",");
		for(String ban_member: ban_list) {
			dao.banmember(ban_member);
		}
	}
}
