SELECT
		cr.id,
	    g.goods_id,
	    IF(g.seller_id LIKE "google_102592663151810141035", cr.buyer_id, g.seller_id) AS opponent_id,
	    IF(g.seller_id LIKE "google_102592663151810141035", mbbuy.mb_nick, g.seller_nick) AS opponent_nick,
	    IF(g.seller_id LIKE "google_102592663151810141035", mbbuy.mb_thumbnail, mbsell.mb_thumbnail) AS opponent_thumbnail,
	    g.goods_location,
	    gi.goods_images_path,
	    gi.goods_images_originname,
	    cm.message,
	    IF(cm.send_date IS NULL, cr.create_date, cm.send_date) AS last_update_date
	FROM
		`imint`.`chatroom` AS cr
	    JOIN (SELECT mb_id, mb_nick, mb_thumbnail FROM `imint`.`member`) AS mbbuy on cr.buyer_id = mbbuy.mb_id
	    JOIN `imint`.`goods` AS g ON cr.goods_id = g.goods_id
	    JOIN (SELECT mb_id, mb_thumbnail FROM `imint`.`member`) AS mbsell on g.seller_id = mbsell.mb_id
	    JOIN `imint`.`goods_images` AS gi ON g.goods_id = gi.goods_id
	    LEFT JOIN
	    	(SELECT
	    		chatroom_id,
	    		message,
	    		send_date
	    	FROM
	    		(SELECT
	    			chatroom_id,
	    			message,
	    			send_date,
	    			ROW_NUMBER() OVER(PARTITION BY chatroom_id ORDER BY send_date DESC) AS recent
	    		FROM
	    			`imint`.`chat_messages`
	    		WHERE
	    			is_deleted = FALSE) AS r
	    	WHERE
	    		r.recent = 1
	    	ORDER BY
	    		send_date DESC) AS cm ON cr.id = cm.chatroom_id
	WHERE
		cr.isdelete = FALSE
	    AND gi.goods_images_isdelete = FALSE
	    AND (g.seller_id LIKE "google_102592663151810141035" OR cr.buyer_id LIKE "google_102592663151810141035")
	    AND gi.goods_images_thumbnail = TRUE
	ORDER BY
		last_update_date DESC, cr.create_date DESC
	LIMIT
		0, 10