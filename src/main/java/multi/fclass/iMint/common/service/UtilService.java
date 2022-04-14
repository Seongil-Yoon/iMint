package multi.fclass.iMint.common.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;

@Service
public class UtilService implements IUtilService {
	
	// 윈도우에서는 \\, 리눅스서버에서는/
	@Value("${route}")
	String route;
	@Value("${directory}")
	String directory;
	@Value("${goodsImagePath}")
	String goodsImagePath;
	@Value("${memberImagePath}")
	String memberImagePath;

	@Override
	public List<String> createGoodsPaths(LocalDateTime createTime) {
		List<String> path = new ArrayList<String>();
		path.add(directory);
		path.add(goodsImagePath);
        String year = String.valueOf(createTime.getYear());
        path.add(year);//년
        String month = String.valueOf(createTime.getMonth());
        path.add(month);//월
		
		return path;
	}

	// 경로조립
	@Override
	public String completePath(List<String> paths) {
		String path = "";
		for (String i : paths) {
			path += i + route;
		}
		return path;
	}
}
