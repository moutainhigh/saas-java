package com.hq.storeMS.service.leaguerRecord.data;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class RelateOrder {
	// 订单ID
	private long orderId;
	// 订单内容
	private String orderContent;

	public static RelateOrder newInstance(long orderIdP, String orderContentP) {
		RelateOrder data = new RelateOrder();
		data.orderId = orderIdP;
		data.orderContent = orderContentP;
		return data;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

}
