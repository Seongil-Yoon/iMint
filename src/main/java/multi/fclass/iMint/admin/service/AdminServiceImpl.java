package multi.fclass.iMint.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.admin.dao.IAdminDAO;
import multi.fclass.iMint.admin.dto.AdminDTO;
import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;

/**
 * @author Junming, Yang
 *
 */

@Service
public class AdminServiceImpl implements IAdminService {
	private final static int BOARD_PER_PAGE = 10;

	@Autowired
	IAdminDAO dao;

	@Autowired
	IGoodsDAO goodsDao;

	// 강제탈퇴
	public void ban(String ban_members) {
		String[] ban_list = ban_members.split(",");
		for (String ban_mbId : ban_list) {
			// role을 guest로 강등
			dao.banmember(ban_mbId);
			// 강제탈퇴된 회원이 작성한 상품 삭제
			dao.banmembergoods(ban_mbId);
		}
	}

	@Override
	public List<GoodsDTO> adminGoodsList(String keyword, String goodsCategory, int page) {
		page = (page - 1) * 15;
		return goodsDao.adminGoodsList(keyword, goodsCategory, page);
	}

	@Override
	public int goodsCount(String keyword, String goodsCategory) {
		int total = goodsDao.goodsCount(keyword, goodsCategory);
		int pagenum = 0;
		if (total % BOARD_PER_PAGE == 0) { // 10으로 고정
			pagenum = total / BOARD_PER_PAGE;
		} else {
			pagenum = total / BOARD_PER_PAGE + 1; // 여분의 페이지1개를 추가하기 위해
		}
		return pagenum; // 필요한 페이지갯수 넘김
	}

}
