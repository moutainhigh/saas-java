package com.hq.chainStore.service.report.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.order.data.Order;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

public class OrderReportDAO extends RestDao<OrderReport> {

	public static OrderReportDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OrderReportDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getReportService();
	}
	
	@Override
	public List<OrderReport> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	public static void main(String[] args) {
		new ClassInfo(Order.class);
	}

}
