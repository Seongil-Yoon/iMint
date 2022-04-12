package multi.fclass.iMint.main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.goods.service.GoodsServiceImpl;
import multi.fclass.iMint.main.service.MainServiceImpl;

/**
 * @author Seongil, Yoon
 * 메인페이지와 관련된 로직
 */
@Controller
public class MainCotroller {

	@Autowired
	MainServiceImpl mainService;

	
	@GetMapping("main")
	public String mainPage() {
		return "main";
	}

	@ResponseBody
	@GetMapping("goods-list/{lastBoard}")
	public List<HashMap<String, Object>> goodsListMap(@PathVariable int lastBoard) {
		return mainService.goodsListMap(lastBoard);
	}
	
}
