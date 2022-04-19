package multi.fclass.iMint.goods.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

public interface IGoodsService {
	public GoodsDTO goods(int goodsId);

	public List<GoodsImagesDTO> goodsImageList(int goodsId);

	public int goodsWrite(GoodsDTO goodsDto, List<MultipartFile> files);

	public int goodsDelete(int goodsId, String mbId);
}
