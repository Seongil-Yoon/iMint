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
	public List<GoodsDTO> goodsList(@Param("goods_category") String goodsCategory, @Param("lastBoard") int lastBoard,
			@Param("mb_location") String mbLocation, @Param("option") String searchOption,
			@Param("keyword") String keyword, @Param("blocklist") List<String> blocklist);

	public GoodsImagesDTO goodsThumbnail(@Param("goods_id") int goodsId);

	// 상품상세
	public GoodsDTO goods(@Param("goods_id") int goodsId);

	public List<GoodsImagesDTO> goodsImageList(@Param("goods_id") int goodsId);

	// 상품등록
	public int goodsInsert(GoodsDTO goodsDTO);

	public int goodsImagesInsert(GoodsImagesDTO goodsImagesDTO);

	public String goodsDate(@Param("goods_id") int goodsId);

	// 상품수정
	public int goodsUpdate(GoodsDTO goodsDTO);

	public int goodsImagesDelete(@Param("goods_id") int goodsId);

	// 상품삭제
	public int goodsIsdelete(@Param("goods_id") int goodsId, @Param("seller_id") String sellerId);

	public int goodsImagesIsdelete(@Param("goods_id") int goodsId);
	
	// 전체 상품 조회
	public List<GoodsDTO> adminGoodsList(@Param("keyword") String keyword, @Param("goods_category") String goodsCategory, @Param("page") int page);
	
	// 상품 총 개수
	public int goodsCount(@Param("keyword") String keyword, @Param("goods_category") String goodsCategory);
}
