package com.hq.chainMS.service.buserMessage.data;

import com.hq.storeClient.service.message.data.BUserMessage;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageCacheDAO {

	public static BUserMessageCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageCacheDAO.class);
	}

	public BUserMessage get(long id) {
		return BUserMessageRedisDAO.getInstance().get(id);
	}
}
