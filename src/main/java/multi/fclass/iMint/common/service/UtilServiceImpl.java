package multi.fclass.iMint.common.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;

@Service
public class UtilServiceImpl implements IUtilService {

	@Value("${route}")
	String route;
	@Value("${directory}")
	String directory;
	@Value("${goodsImagePath}")
	String goodsImagePath;
	@Value("${memberImagePath}")
	String memberImagePath;

	// 경로배열 생성
	@Override
	public List<String> createGoodsPaths(String createTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(createTime, formatter);

		List<String> path = new ArrayList<String>();
		path.add(directory);
		path.add(goodsImagePath);
		String year = Integer.toString(dateTime.getYear());
		path.add(year);// 년
		String month = Integer.toString(dateTime.getMonthValue());
		path.add(month);// 월
		String day = Integer.toString(dateTime.getDayOfMonth());
		path.add(day);

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

	@Override
	public String completePath(List<String> paths, int idx) {
		String path = "";
		for (int i = idx; i < paths.size(); i++) {
			path += paths.get(i) + route;
		}
		return path;
	}

}
