package multi.fclass.iMint.main.service;

import java.util.ArrayList;
import java.util.HashMap;
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
public class MainServiceImpl implements IMainService {

	@Autowired
	IGoodsDAO goodsDAO;

	@Override
	public List<HashMap<String, Object>> goodsListMap(int lastBoard) {
		List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;

		List<GoodsDTO> goodsList = goodsDAO.goodsList(lastBoard);
		GoodsImagesDTO goodsThumbnail = null;
		for (int i = 0; i < goodsList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("goods", goodsList.get(i));
			goodsThumbnail = goodsDAO.goodsThumbnail(goodsList.get(i).getGoodsId());
			if(goodsThumbnail == null) {
				goodsThumbnail = new GoodsImagesDTO(null, goodsList.get(i).getGoodsId(), "/static/images/noimage.png", true, "noimage.png", null);
			}
			map.put("goodsImage", goodsThumbnail);
			listMap.add(i, map);
		}
		return listMap;
	}

}
