package multi.fclass.iMint.main.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import multi.fclass.iMint.goods.dto.GoodsDTO;

public interface IMainService {
	public List<HashMap<String, Object>> goodsListMap(int lastBoard, String mbLocation);
	
}
