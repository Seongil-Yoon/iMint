package multi.fclass.iMint.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.goods.dto.GoodsImagesDTO;
import multi.fclass.iMint.goods.service.GoodsServiceImpl;

/**
 * @author Seongil, Yoon
 *
 */
@Controller
public class GoodsCotroller {
	@Autowired
	GoodsServiceImpl goodsSevice;

	@GetMapping("goods/detail")
	public String goodsDetail(@RequestParam("goodsId") int goodsId, Model model) {
		model.addAttribute("goods", goodsSevice.goods(goodsId));
		return "goods/goods-detail";
	}

	@ResponseBody
	@GetMapping("goods/detail-images")
	public List<GoodsImagesDTO> goodsDetailImages(@RequestParam("goodsId") int goodsId) {
		return goodsSevice.goodsImage(goodsId);
	}
}
