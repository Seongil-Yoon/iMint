package multi.fclass.iMint.goods.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;
import multi.fclass.iMint.member.dto.MemberDTO;

public interface IGoodsService {
	public GoodsDTO goods(int goodsId);

	public List<GoodsImagesDTO> goodsImageList(int goodsId);

	public int goodsWrite(MemberDTO dto, GoodsDTO goodsDto, List<MultipartFile> files);
	
	public int goodsModify(MemberDTO dto, GoodsDTO goodsDto, List<MultipartFile> files);

	public int goodsDelete(int goodsId, MemberDTO dto);
}
