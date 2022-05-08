package multi.fclass.iMint.rating.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class RatingDTO {

	int ratingId;
	int trxId;
	String mbId;
	int ratingScore;
	LocalDateTime ratingCreateDate;

}
