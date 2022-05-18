package multi.fclass.iMint.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;
import multi.fclass.iMint.wishlist.service.WishlistServiceImpl;

/**
 * @author Seongil, Yoon
 *
 */
@Service
public class MainServiceImpl implements IMainService {

	@Autowired
	IGoodsDAO goodsDAO;

	@Autowired
	WishlistServiceImpl wishService;

	@Override
	public List<HashMap<String, Object>> goodsListMap(String goodsCategory, int lastBoard, String mbLocation,
			String searchOption, String keyword, List<String> blocklist) {
		System.out.println(goodsCategory + ", " + searchOption + ", " + keyword);
		List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		
		if(blocklist.isEmpty() || blocklist == null) {
			blocklist.add("");
		}
		List<GoodsDTO> goodsList = goodsDAO.goodsList(goodsCategory, lastBoard, mbLocation, searchOption, keyword, blocklist);
		GoodsImagesDTO goodsThumbnail = null;
		
		for (int i = 0; i < goodsList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("goods", goodsList.get(i));
			goodsThumbnail = goodsDAO.goodsThumbnail(goodsList.get(i).getGoodsId());
			if (goodsThumbnail == null) {
				goodsThumbnail = new GoodsImagesDTO(null, goodsList.get(i).getGoodsId(), "/static/images/noimage.png",
						true, "noimage.png", null);
			}
			map.put("goodsImage", goodsThumbnail);
			map.put("countWishes", wishService.countWishes(goodsList.get(i).getGoodsId()));
			listMap.add(i, map);
		}
		return listMap;
	}

}
