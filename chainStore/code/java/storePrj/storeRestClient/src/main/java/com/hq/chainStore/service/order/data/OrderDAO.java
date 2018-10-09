package com.hq.chainStore.service.order.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

/**
 * 
 * @Deprecated 已经被StoreOrderDAO替代
 *
 */
@Deprecated
public class OrderDAO extends RestDao<Order> {

	public static OrderDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OrderDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getReportService();
	}
	
	@Override
	public List<Order> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	public static void main(String[] args) {
		new ClassInfo(Order.class);
	}
	
}
