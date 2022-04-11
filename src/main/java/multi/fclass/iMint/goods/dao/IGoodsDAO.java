package multi.fclass.iMint.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

@Mapper
public interface IGoodsDAO {
	//비동기 무한스크롤
	public List<GoodsDTO> goodsList(@Param("goods_id") int goods_id);
	public List<GoodsImagesDTO> goodsImagesList(@Param("goods_id") int goods_id);
}
