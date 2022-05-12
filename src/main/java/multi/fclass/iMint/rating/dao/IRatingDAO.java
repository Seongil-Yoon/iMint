package multi.fclass.iMint.rating.dao;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.rating.dto.RatingDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IRatingDAO {

	public RatingDTO selectRating(String myId, int trxId);

	public int insertRating(String myId, int trxId, double ratingScore);

	public int updateMemberRating(String opponentId, double newRating);

}
