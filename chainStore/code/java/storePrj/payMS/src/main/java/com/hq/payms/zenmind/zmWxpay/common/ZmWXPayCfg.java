package com.hq.payms.zenmind.zmWxpay.common;

public class ZmWXPayCfg {

	//微信支付配置
	
	/* 这四项配置关系到支付到哪个商户，要依赖数据库 */
	private String wxpayAppId; //appId
	private String wxpayMchId; //商户号
	private String wxpayKey; //API密钥,用于生成签名
	private String wxpayCertPath; //商户证书
	
	private String wxpayNotifyUrl; //支付结果通知url
	private String wxpayRefundNotifyUrl; //退款结果通知url
	
	private boolean wxpayUseSandbox = false; //是否沙箱环境
	private int wxpayHttpConnectTimeoutMs;
	private int wxpayHttpReadTimeoutMs;
	private int wxpayOrderQueryNThread; //订单支付结果查询线程数
	private int wxpayTimeExpire; //支付失效时间(单位为秒)
	private int wxpayAuthorityNotifyTimeout; //微信官方回调通知超时时间(单位为秒)
	private int wxpayCallbackMaxCount; //回调通知storeMS的最大次数
	
	private String wxpayCertPathPrefix; //商户证书本地路径前缀
	private String wxpayCertPathNetPrefix; //商户证书网络路径前缀

	public String getWxpayCertPath() {
		return wxpayCertPath;
	}

	public void setWxpayCertPath(String wxpayCertPath) {
		this.wxpayCertPath = wxpayCertPath;
	}

	public String getWxpayAppId() {
		return wxpayAppId;
	}

	public void setWxpayAppId(String wxpayAppId) {
		this.wxpayAppId = wxpayAppId;
	}

	public String getWxpayMchId() {
		return wxpayMchId;
	}

	public void setWxpayMchId(String wxpayMchId) {
		this.wxpayMchId = wxpayMchId;
	}

	public String getWxpayKey() {
		return wxpayKey;
	}

	public void setWxpayKey(String wxpayKey) {
		this.wxpayKey = wxpayKey;
	}

	public String getWxpayNotifyUrl() {
		return wxpayNotifyUrl;
	}

	public void setWxpayNotifyUrl(String wxpayNotifyUrl) {
		this.wxpayNotifyUrl = wxpayNotifyUrl;
	}

	public String getWxpayRefundNotifyUrl() {
		return wxpayRefundNotifyUrl;
	}

	public void setWxpayRefundNotifyUrl(String wxpayRefundNotifyUrl) {
		this.wxpayRefundNotifyUrl = wxpayRefundNotifyUrl;
	}

	public boolean isWxpayUseSandbox() {
		return wxpayUseSandbox;
	}

	public void setWxpayUseSandbox(boolean wxpayUseSandbox) {
		this.wxpayUseSandbox = wxpayUseSandbox;
	}

	public int getWxpayHttpConnectTimeoutMs() {
		return wxpayHttpConnectTimeoutMs;
	}

	public void setWxpayHttpConnectTimeoutMs(int wxpayHttpConnectTimeoutMs) {
		this.wxpayHttpConnectTimeoutMs = wxpayHttpConnectTimeoutMs;
	}

	public int getWxpayHttpReadTimeoutMs() {
		return wxpayHttpReadTimeoutMs;
	}

	public void setWxpayHttpReadTimeoutMs(int wxpayHttpReadTimeoutMs) {
		this.wxpayHttpReadTimeoutMs = wxpayHttpReadTimeoutMs;
	}

	public int getWxpayOrderQueryNThread() {
		return wxpayOrderQueryNThread;
	}

	public void setWxpayOrderQueryNThread(int wxpayOrderQueryNThread) {
		this.wxpayOrderQueryNThread = wxpayOrderQueryNThread;
	}

	public int getWxpayTimeExpire() {
		return wxpayTimeExpire;
	}

	public void setWxpayTimeExpire(int wxpayTimeExpire) {
		this.wxpayTimeExpire = wxpayTimeExpire;
	}

	public int getWxpayAuthorityNotifyTimeout() {
		return wxpayAuthorityNotifyTimeout;
	}

	public void setWxpayAuthorityNotifyTimeout(int wxpayAuthorityNotifyTimeout) {
		this.wxpayAuthorityNotifyTimeout = wxpayAuthorityNotifyTimeout;
	}

	public String getWxpayCertPathPrefix() {
		return wxpayCertPathPrefix;
	}

	public void setWxpayCertPathPrefix(String wxpayCertPathPrefix) {
		this.wxpayCertPathPrefix = wxpayCertPathPrefix;
	}

	public String getWxpayCertPathNetPrefix() {
		return wxpayCertPathNetPrefix;
	}

	public void setWxpayCertPathNetPrefix(String wxpayCertPathNetPrefix) {
		this.wxpayCertPathNetPrefix = wxpayCertPathNetPrefix;
	}

	public int getWxpayCallbackMaxCount() {
		return wxpayCallbackMaxCount;
	}

	public void setWxpayCallbackMaxCount(int wxpayCallbackMaxCount) {
		this.wxpayCallbackMaxCount = wxpayCallbackMaxCount;
	}
	
}
