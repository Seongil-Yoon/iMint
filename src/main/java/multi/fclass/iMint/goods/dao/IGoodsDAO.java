package multi.fclass.iMint.goods.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Mapper
public interface IGoodsDAO {
	// 비동기 무한스크롤
	public List<GoodsDTO> goodsList(@Param("lastBoard") int lastBoard);
	public GoodsImagesDTO goodsThumbnail(@Param("goods_id") int goodsId);

	// 상품상세
	public GoodsDTO goods(@Param("goods_id") int goodsId);
	public List<GoodsImagesDTO> goodsImageList(@Param("goods_id") int goodsId);
	
	// 상품등록
	public int goodsInsert(GoodsDTO goodsDTO);
	public int goodsImagesInsert(GoodsImagesDTO goodsImagesDTO);
	public String goodsDate(@Param("goods_id") int goodsId);
	
	public int goodsDelete(@Param("goods_id") int goodsId, @Param("seller_id") int sellerId);
}
