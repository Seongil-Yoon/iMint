package multi.fclass.iMint.goods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.goods.dto.GoodsDTO;
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
		return goodsSevice.goodsImageList(goodsId);
	}
	
	@GetMapping("goods/write")
	public String goodsWriteView() {
		return "goods/goods-write";
	}
	
	@ResponseBody
	@PostMapping("goods/write")
	public GoodsDTO goodsWrite(@RequestPart("GoodsDTO") GoodsDTO goodsDTO,  
			@RequestPart("files") List<MultipartFile> files) {
		
		int goodsId = goodsSevice.goodsWrite(goodsDTO, files);
		System.out.println("작성된 상품글ID : " + goodsId);
		if(goodsId != -1) {
			goodsDTO.setGoodsId(goodsId);
		}
		// 브라우저단에서 location.href로 상품상세
		return goodsDTO;
	}
}
