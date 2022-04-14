package multi.fclass.iMint.common.service;

import java.time.LocalDateTime;
import java.util.List;

import multi.fclass.iMint.goods.dto.GoodsDTO;

public interface IUtilService {
	// dto에서 게시글 생성날짜를 가져와서 경로배열 반환, goods/년/월
	public List<String> createGoodsPaths(LocalDateTime createTime);
	
	//경로배열 조립
    public String completePath(List<String> paths);
}
