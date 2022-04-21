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
}
