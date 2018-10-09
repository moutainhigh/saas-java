package com.hq.storeMS.service.charge.data;

import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeCacheDAO {

	public static ChargeCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeCacheDAO.class);
	}
	
	public Charge get(long id) {
		return ChargeRedisDAO.getInstance().get(id);
	}
}
