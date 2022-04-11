package multi.fclass.iMint.main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.main.service.MainServiceImpl;

/**
 * @author Seongil, Yoon
 *
 */
@Controller
public class MainCotroller {

	@Autowired
	MainServiceImpl service;

	@GetMapping("main")
	public String mainPage() {
		return "main";
	}

	@ResponseBody
	@GetMapping("goods-list/{goods_id}")
	public List<HashMap<String, Object>> goodsListMap(@PathVariable int goods_id) {
		return service.goodsListMap(goods_id);
	}
}
