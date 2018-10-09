package com.hq.payms.service.alipay.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 支付宝共有请求参数<br>
 *  @JsonIgnore 标识的是非业务参数，无须转json，但必须传入AlipayClient或者作为最终的共有请求参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class CommonReq {
	
	// （必填）用于打款到子商户的收款账号(所有接口都支持这种方式)
	@JsonIgnore
	protected String appId;
	// （必填）用于打款到子商户的收款账号(所有接口都支持这种方式)
	@JsonIgnore
	protected String privateKey;
	// （必填）用于打款到子商户的收款账号(所有接口都支持这种方式)
	@JsonIgnore
	protected String alipayPublicKey; 
	
	// （非必填）系统商编号，该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
	// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
	@JsonIgnore
	protected String sysServiceProviderId;
	
	// (非必填，只有当面付接口支持这种方式)子商户授权码，用于打款到子商户的收款账号
	@JsonIgnore
	protected String appAuthToken;
	
	//（非必填，跟支付到哪个账号没关系）卖家支付宝账号ID，如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
	protected String sellerId;

	
	public String getSysServiceProviderId() {
		return sysServiceProviderId;
	}

	public void setSysServiceProviderId(String sysServiceProviderId) {
		this.sysServiceProviderId = sysServiceProviderId;
	}

	public String getAppAuthToken() {
		return appAuthToken;
	}

	public void setAppAuthToken(String appAuthToken) {
		this.appAuthToken = appAuthToken;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	
}
