package multi.fclass.iMint.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.block.service.IBlockService;
import multi.fclass.iMint.mypage.dao.IMypageDAO;
import multi.fclass.iMint.mypage.dto.MypageBlockDTO;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageConnectionDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
@Service
public class MypageServiceImpl implements IMypageService {

	@Autowired
	IMypageDAO mypageDAO;

	@Autowired
	IBlockService blockService;

	// 내 보호자 조회 서비스
	@Override
	public MypageConnectionDTO getMyGuard(String myId) {
		return mypageDAO.getMyGuard(myId);
	}

	// 내 아이 목록 조회 서비스
	@Override
	public List<MypageConnectionDTO> getMyChildrenList(String myId) {
		return mypageDAO.getMyChildrenList(myId);
	}

	@Override
	public boolean isMyChild(String myId, String childId) {
		boolean flag = false;
		List<MypageConnectionDTO> childrenList = getMyChildrenList(myId);

		for (MypageConnectionDTO child : childrenList) {
			if (child.getMbId().equals(childId)) {
				return true;
			}
		}

		return flag;
	}

	// 관심 목록 조회 서비스
	@Override
	public List<MypageDTO> getWishList(String myId) {
		List<String> blockList = blockService.blocklist(myId);
		List<MypageDTO> wishList = mypageDAO.getWishList(myId);
		wishList.removeIf((dto) -> (blockList.contains(dto.getSellerId()))); // 차단한 회원의 판매글을 목록에서 제외

		return wishList;
	}

	// 구매예약/판매중 목록 조회 서비스
	@Override
	public List<MypageDTO> getTradeList(String myId) {
		List<String> blockList = blockService.blocklist(myId);
		List<MypageDTO> tradeList = mypageDAO.getTradeList(myId);
		tradeList.removeIf((dto) -> (blockList.contains(dto.getSellerId()))); // 차단한 회원과의 거래를 목록에서 제외

		return tradeList;
	}

	// 거래완료 목록 조회 서비스
	@Override
	public List<MypageDTO> getCompleteList(String myId) {
		List<MypageDTO> compList = mypageDAO.getCompleteList(myId);

		return compList;
	}

	// 채팅방 목록 조회 서비스
	@Override
	public List<MypageChatroomDTO> getChatroomList(String myId) {
		List<String> blockList = blockService.blocklist(myId);
		List<MypageChatroomDTO> chatroomList = mypageDAO.getChatroomList(myId);
		chatroomList.removeIf((dto) -> (blockList.contains(dto.getOpponentId()))); // 차단한 회원과의 채팅을 목록에서 제외

		return chatroomList;
	}

	// 차단 목록 조회 서비스
	@Override
	public List<MypageBlockDTO> getBlockList(String myId) {
		return mypageDAO.getBlockList(myId);
	}

}
