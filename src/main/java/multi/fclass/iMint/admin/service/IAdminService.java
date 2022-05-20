package multi.fclass.iMint.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import multi.fclass.iMint.goods.dto.GoodsDTO;

/**
 * @author Junming, Yang
 *
 */

public interface IAdminService {
	
	// 강제 탈퇴 
	public void ban(String ban_members);
	
	// 전체 상품 조회
	public List<GoodsDTO> adminGoodsList(String keyword, String goodsCategory, int page);
	
	// 상품 총 개수
	public int goodsCount(String keyword, String goodsCategory);
}
