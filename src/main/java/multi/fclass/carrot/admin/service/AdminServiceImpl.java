package multi.fclass.carrot.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.carrot.admin.dao.IAdminDAO;
import multi.fclass.carrot.admin.dto.AdminDTO;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	IAdminDAO dao;
}
