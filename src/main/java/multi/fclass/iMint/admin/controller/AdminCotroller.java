package multi.fclass.iMint.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.admin.dto.AdminDTO;
import multi.fclass.iMint.admin.service.AdminServiceImpl;

@Controller
public class AdminCotroller {
	
	@Autowired
	AdminServiceImpl service;
	
}
