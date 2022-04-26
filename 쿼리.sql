SELECT * FROM `imint`.`goods`
		WHERE goods_id < 20000
		AND `goods_location` = "대구 남구"
		AND `goods_category` like CONCAT('%',"문구",'%')
		AND `goods_isdelete` = 0
		ORDER BY `goods_create_date` DESC 
		LIMIT 18;

SELECT * FROM `imint`.`goods`
		WHERE goods_id < 20000
		AND (
			`goods_title` LIKE CONCAT('%',"상품",'%')
			OR `goods_content` LIKE CONCAT('%',"상품",'%')
			OR `seller_nick` LIKE CONCAT('%',"상품",'%')
		)
		AND `goods_location` = "대구 남구"
		AND `goods_isdelete` = 0
		ORDER BY `goods_create_date` DESC 
		LIMIT 18;
		
SELECT * FROM `imint`.`goods`
		WHERE goods_id < 20000
		AND replaceCamelCase("sellerNick") LIKE CONCAT('%',"5590",'%')
		AND `goods_location` ="대구 남구"
		AND `goods_isdelete` = 0
		ORDER BY `goods_create_date` DESC 
		LIMIT 18;