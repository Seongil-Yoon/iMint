package multi.fclass.carrot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.carrot.admin.dto.AdminDTO;
import multi.fclass.carrot.admin.service.AdminServiceImpl;

@Controller
public class AdminCotroller {
	
	@Autowired
	AdminServiceImpl service;
	
}
