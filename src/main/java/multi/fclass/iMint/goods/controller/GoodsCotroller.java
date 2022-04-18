package multi.fclass.iMint.goods.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;
import multi.fclass.iMint.goods.service.GoodsServiceImpl;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dto.User;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Seongil, Yoon
 *
 */
@Controller
public class GoodsCotroller {
	@Autowired
	GoodsServiceImpl goodsSevice;

	@Autowired
	ParseMbId parseService;

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
	public String goodsWriteView(Authentication auth, Model model) {
		String mbId = parseService.parseMbId(auth);
		User userDTO = parseService.getUserMbId(mbId);

//		나중에 세션값으로 대체
//		MemberDTO dto1 = new MemberDTO("test1235", "무민1919", "대구 수성구");
		model.addAttribute("member", userDTO);
		return "goods/goods-write";
	}

	@ResponseBody
	@PostMapping("goods/write")
	public GoodsDTO goodsWrite(@RequestPart("GoodsDTO") GoodsDTO goodsDTO,
			@RequestPart(value = "files", required = false) List<MultipartFile> files) {

		int goodsId = goodsSevice.goodsWrite(goodsDTO, files);
		System.out.println("작성된 상품글ID : " + goodsId);
		if (goodsId != -1) {
			goodsDTO.setGoodsId(goodsId);
		}
		// 브라우저단에서 location.href로 상품상세
		return goodsDTO;
	}
}
