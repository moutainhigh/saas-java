package com.hq.payms.service.wxpay.apiData;

import com.hq.payms.service.common.BaseApiForm;
import com.hq.payms.service.wxpay.data.UnifiedOrderReq;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class UnifiedOrderApiForm extends BaseApiForm implements IntfWxpayApiForm {
	//订单号
	private long orderId;
	
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;
	
	//订单总金额，只能为整数;单位为"分"
	private String total_fee; 
	
	//商户交易号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
	private String out_trade_no; 
	
	public static UnifiedOrderApiForm newInstance() {
		return new UnifiedOrderApiForm();
	}
	
	public UnifiedOrderReq toUnifiedOrderReq() {
		UnifiedOrderReq target = UnifiedOrderReq.newInstance();
		FastBeanCopyer.getInstance().copy(this, target);
		return target;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getOrderOriginType() {
		return orderOriginType;
	}

	public void setOrderOriginType(int orderOriginType) {
		this.orderOriginType = orderOriginType;
	}
	
}
