package multi.fclass.iMint.goods.service;

import java.util.List;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

public interface IGoodsService {
	public GoodsDTO goods(int goodsId);

	public List<GoodsImagesDTO> goodsImage(int goodsId);
}
