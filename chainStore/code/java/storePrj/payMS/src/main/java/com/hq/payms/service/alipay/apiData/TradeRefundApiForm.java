package com.hq.payms.service.alipay.apiData;

import com.hq.payms.service.alipay.data.TradeRefundReq;
import com.hq.payms.service.common.BaseApiForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class TradeRefundApiForm extends BaseApiForm{
	// (必填) 外部订单号，需要退款交易的商户外部订单号
	private String outTradeNo;

	// (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
	private String refundAmount;
	
	// (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
    // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
	// 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
	private String outRequestNo;

	public TradeRefundReq toTradeRefundReq() {
		TradeRefundReq target = TradeRefundReq.newInstance();
		FastBeanCopyer.getInstance().copy(this, target);
		return target;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getOutRequestNo() {
		return outRequestNo;
	}

	public void setOutRequestNo(String outRequestNo) {
		this.outRequestNo = outRequestNo;
	}

}
