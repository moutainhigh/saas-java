package com.hq.chainStore.service.orderComment.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OrderCommentDAO extends RestDao<OrderComment> {

	public static OrderCommentDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OrderCommentDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getReportService();
	}
}
