package multi.fclass.iMint.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
	@Autowired
	IGoodsDAO goodsDAO;
	
	@Override
	public GoodsDTO goods(int goodsId) {
		return goodsDAO.goods(goodsId);
	}

	@Override
	public List<GoodsImagesDTO> goodsImage(int goodsId) {
		List<GoodsImagesDTO> goodsImages = goodsDAO.goodsImages(goodsId);
		if(goodsImages.isEmpty()) {
			goodsImages.add(new GoodsImagesDTO(null, goodsId, "/static/images/noimage.png", true, "noimage.png", null));
		}
		return goodsImages;
	}
	
	
}
