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
	public List<HashMap<String, Object>> goodsListMap(int goods_id) {
		List<HashMap<String, Object>> listMap = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;

		List<GoodsDTO> goodsList = goodsDAO.goodsList(goods_id);
		List<GoodsImagesDTO> goodsImagesList = goodsDAO.goodsImagesList(goods_id);
		for (int i = 0; i < goodsList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("goods", goodsList.get(i));
			map.put("goodsImage", goodsImagesList.get(i));
			listMap.add(i, map);
		}
//		for(HashMap<String, Object> map1 : listMap) {
//			System.out.println(map1);
//		}

		return listMap;
	}

}
