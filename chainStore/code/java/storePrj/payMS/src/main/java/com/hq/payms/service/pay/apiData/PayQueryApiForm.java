package com.hq.payms.service.pay.apiData;

import org.apache.commons.lang3.StringUtils;

public class PayQueryApiForm {
	
	//api类型 ApiTypeEnum
	private int apiType = -1;
	
	//商户交易号，要求32个字符内，只能包含字母、数字、下划线，需保证商户系统端不能重复
	private String outTradeNo;
	
	public static PayQueryApiForm newInstance(int apiType, String outTradeNo) {
		PayQueryApiForm instance = new PayQueryApiForm();
		instance.apiType = apiType;
		instance.outTradeNo = outTradeNo;
		return instance;
	}

	public int getApiType() {
		return apiType;
	}

	public void setApiType(int apiType) {
		this.apiType = apiType;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	public boolean isValid() {
		return  this.getApiType() > -1
				&& StringUtils.isNotBlank(this.getOutTradeNo());
	}
	
	

}
