package com.hq.storeMS.service.orderDetail.data;

import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class RechargeDetail {
	// 实收金额
	private float pay;
	// 赠送金额
	private float largess;
	// 实充金额
	private float amount;

	public static RechargeDetail newInstance() {
		RechargeDetail data = new RechargeDetail();
		return data;
	}

	public static RechargeDetail newInstanceByRechargeItem(RechargeItem item) {
		RechargeDetail data = new RechargeDetail();
		FastBeanCopyer.getInstance().copy(item, data);
		return data;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public float getLargess() {
		return largess;
	}

	public void setLargess(float largess) {
		this.largess = largess;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
