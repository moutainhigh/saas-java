package com.hq.payms.service.pay.apiData;

import org.apache.commons.lang3.StringUtils;

import com.hq.payms.common.utils.AmountUtils;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ScanPayApiForm{
	
	//api类型 ApiTypeEnum
	private int apiType = -1;
	
	//用于查询商户账号、密钥等信息; 
	private long storeId;
	
	//订单号
	private long orderId;
	
	//订单号来源类型，用来区分如何回调；OrderOriginTypeEnum
	private int orderOriginType;
	
	//商户交易号，要求32个字符内，只能包含字母、数字、下划线，需保证商户系统端不能重复
	private String outTradeNo;

	//订单总金额，单位为元，不能超过1亿元
	private String totalAmount;

	//付款条码，用户微信或支付宝app点击“付款”产生的付款条码；条码示例: 286648048691290423
	private String authCode;
	
	
	/**
	 * 转成微信apiForm
	 * @return
	 */
	public MicroPayApiForm toMicroPayApiForm(){
		MicroPayApiForm target = MicroPayApiForm.newInstance();
		target.setStoreId(this.getStoreId());
		target.setOrderId(this.getOrderId());
		target.setOut_trade_no(this.getOutTradeNo());
		//微信支付单位为分
		target.setTotal_fee(AmountUtils.changeY2F(this.getTotalAmount()));
		target.setAuth_code(this.getAuthCode());
		return target;
	}
	
	/**
	 * 转成支付宝apiForm
	 * @return
	 */
	public TradePayApiForm toTradePayApiForm() {
		TradePayApiForm target = TradePayApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, target);
		target.setStoreId(this.getStoreId());
		return target;
	}
	
	public boolean isValid() {
		return this.apiType > -1
				&& this.storeId > 0L
				&& this.orderId > 0L
				&& this.orderOriginType > -1
				&& StringUtils.isNotBlank(this.outTradeNo)
				&& StringUtils.isNotBlank(this.totalAmount)
				&& StringUtils.isNotBlank(this.authCode);
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public int getApiType() {
		return apiType;
	}

	public void setApiType(int apiType) {
		this.apiType = apiType;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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
