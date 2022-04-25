SELECT * FROM `imint`.`goods`
		WHERE goods_id > 0
		AND `goods_location` = "중구"
		AND `goods_isdelete` = 0
		ORDER BY `goods_create_date` DESC 
		LIMIT 18;