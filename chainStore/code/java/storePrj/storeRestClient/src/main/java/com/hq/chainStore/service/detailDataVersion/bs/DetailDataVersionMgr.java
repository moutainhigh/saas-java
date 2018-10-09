package com.hq.chainStore.service.detailDataVersion.bs;

import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersion;
import com.hq.chainStore.service.detailDataVersion.data.DetailDataVersionDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class DetailDataVersionMgr {
	public static DetailDataVersionMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionMgr.class);
	}

	public DetailDataVersion get(long id) {
		return DetailDataVersionDAO.getInstance().get(id);
	}
}
