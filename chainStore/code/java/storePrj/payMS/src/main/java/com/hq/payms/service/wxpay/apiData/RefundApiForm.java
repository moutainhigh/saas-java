package com.hq.payms.service.wxpay.apiData;

import com.hq.payms.service.common.BaseApiForm;
import com.hq.payms.service.wxpay.data.RefundReq;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class RefundApiForm extends BaseApiForm{
	
	private String out_trade_no; //商户订单号 
	private String out_refund_no; //商户退款单号
	private String total_fee; //订单金额 
	private String refund_fee; //申请退款金额 
	
	public RefundReq toRefundReq() {
		RefundReq target = RefundReq.newInstance();
		FastBeanCopyer.getInstance().copy(this, target);
		return target;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	} 
	
	
}
