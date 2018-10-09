package com.hq.payms.zenmind.zmAlipay.common;

public class ZmAlipayCfg {

	//支付宝配置
	private String alipayOpenApiDomain; // 支付宝openapi域名
	private String alipayMcloudApiDomain; // 支付宝mcloudmonitor域名
	private String alipayPid; // 商户partner id
	private String alipayAppid; // 商户应用id

	private String alipayPrivateKey; // RSA私钥，用于对商户请求报文加签
	private String alipayDevPublicKey; // RSA公钥，仅用于验证开发者网关
	private String alipayZfbPublicKey; // 支付宝RSA公钥，用于验签支付宝应答
	private String alipaySignType; // 签名类型

	private int alipayMaxQueryRetry; // 最大查询次数
	private long alipayQueryDuration; // 查询间隔（毫秒）

	private int alipayMaxCancelRetry; // 最大撤销次数
	private long alipayCancelDuration; // 撤销间隔（毫秒）

	private long alipayHeartbeatDelay; // 交易保障线程第一次调度延迟（秒）
	private long alipayHeartbeatDuration; // 交易保障线程调度间隔（秒）
	
	private String alipayNotifyUrl; //支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置 
	private String alipayRefundNotifyUrl; //支付宝退款通知路径
	
	private int alipayHttpConnectTimeoutMs;
	private int alipayHttpReadTimeoutMs;
	
	private int alipayOrderQueryNThread; //订单支付结果查询线程数
	private int alipayTimeoutExpress;  //支付失效时间(单位为秒)
	private int alipayAuthorityNotifyTimeout; //官方回调通知超时时间(单位为秒)
	private int alipayCallbackMaxCount; //回调通知storeMS的最大次数
		
	
	public String getAlipayOpenApiDomain() {
		return alipayOpenApiDomain;
	}

	public void setAlipayOpenApiDomain(String alipayOpenApiDomain) {
		this.alipayOpenApiDomain = alipayOpenApiDomain;
	}

	public String getAlipayMcloudApiDomain() {
		return alipayMcloudApiDomain;
	}

	public void setAlipayMcloudApiDomain(String alipayMcloudApiDomain) {
		this.alipayMcloudApiDomain = alipayMcloudApiDomain;
	}

	public String getAlipayPid() {
		return alipayPid;
	}

	public void setAlipayPid(String alipayPid) {
		this.alipayPid = alipayPid;
	}

	public String getAlipayAppid() {
		return alipayAppid;
	}

	public void setAlipayAppid(String alipayAppid) {
		this.alipayAppid = alipayAppid;
	}

	public String getAlipayPrivateKey() {
		return alipayPrivateKey;
	}

	public void setAlipayPrivateKey(String alipayPrivateKey) {
		this.alipayPrivateKey = alipayPrivateKey;
	}

	public String getAlipayDevPublicKey() {
		return alipayDevPublicKey;
	}

	public void setAlipayDevPublicKey(String alipayDevPublicKey) {
		this.alipayDevPublicKey = alipayDevPublicKey;
	}

	public String getAlipayZfbPublicKey() {
		return alipayZfbPublicKey;
	}

	public void setAlipayZfbPublicKey(String alipayZfbPublicKey) {
		this.alipayZfbPublicKey = alipayZfbPublicKey;
	}

	public String getAlipaySignType() {
		return alipaySignType;
	}

	public void setAlipaySignType(String alipaySignType) {
		this.alipaySignType = alipaySignType;
	}

	public int getAlipayMaxQueryRetry() {
		return alipayMaxQueryRetry;
	}

	public void setAlipayMaxQueryRetry(int alipayMaxQueryRetry) {
		this.alipayMaxQueryRetry = alipayMaxQueryRetry;
	}

	public long getAlipayQueryDuration() {
		return alipayQueryDuration;
	}

	public void setAlipayQueryDuration(long alipayQueryDuration) {
		this.alipayQueryDuration = alipayQueryDuration;
	}

	public int getAlipayMaxCancelRetry() {
		return alipayMaxCancelRetry;
	}

	public void setAlipayMaxCancelRetry(int alipayMaxCancelRetry) {
		this.alipayMaxCancelRetry = alipayMaxCancelRetry;
	}

	public long getAlipayCancelDuration() {
		return alipayCancelDuration;
	}

	public void setAlipayCancelDuration(long alipayCancelDuration) {
		this.alipayCancelDuration = alipayCancelDuration;
	}

	public long getAlipayHeartbeatDelay() {
		return alipayHeartbeatDelay;
	}

	public void setAlipayHeartbeatDelay(long alipayHeartbeatDelay) {
		this.alipayHeartbeatDelay = alipayHeartbeatDelay;
	}

	public long getAlipayHeartbeatDuration() {
		return alipayHeartbeatDuration;
	}

	public void setAlipayHeartbeatDuration(long alipayHeartbeatDuration) {
		this.alipayHeartbeatDuration = alipayHeartbeatDuration;
	}

	public String getAlipayNotifyUrl() {
		return alipayNotifyUrl;
	}

	public void setAlipayNotifyUrl(String alipayNotifyUrl) {
		this.alipayNotifyUrl = alipayNotifyUrl;
	}

	public int getAlipayTimeoutExpress() {
		return alipayTimeoutExpress;
	}

	public void setAlipayTimeoutExpress(int alipayTimeoutExpress) {
		this.alipayTimeoutExpress = alipayTimeoutExpress;
	}

	public int getAlipayHttpConnectTimeoutMs() {
		return alipayHttpConnectTimeoutMs;
	}

	public void setAlipayHttpConnectTimeoutMs(int alipayHttpConnectTimeoutMs) {
		this.alipayHttpConnectTimeoutMs = alipayHttpConnectTimeoutMs;
	}

	public int getAlipayHttpReadTimeoutMs() {
		return alipayHttpReadTimeoutMs;
	}

	public void setAlipayHttpReadTimeoutMs(int alipayHttpReadTimeoutMs) {
		this.alipayHttpReadTimeoutMs = alipayHttpReadTimeoutMs;
	}

	public int getAlipayOrderQueryNThread() {
		return alipayOrderQueryNThread;
	}

	public void setAlipayOrderQueryNThread(int alipayOrderQueryNThread) {
		this.alipayOrderQueryNThread = alipayOrderQueryNThread;
	}

	public int getAlipayAuthorityNotifyTimeout() {
		return alipayAuthorityNotifyTimeout;
	}

	public void setAlipayAuthorityNotifyTimeout(int alipayAuthorityNotifyTimeout) {
		this.alipayAuthorityNotifyTimeout = alipayAuthorityNotifyTimeout;
	}

	public String getAlipayRefundNotifyUrl() {
		return alipayRefundNotifyUrl;
	}

	public void setAlipayRefundNotifyUrl(String alipayRefundNotifyUrl) {
		this.alipayRefundNotifyUrl = alipayRefundNotifyUrl;
	}

	public int getAlipayCallbackMaxCount() {
		return alipayCallbackMaxCount;
	}

	public void setAlipayCallbackMaxCount(int alipayCallbackMaxCount) {
		this.alipayCallbackMaxCount = alipayCallbackMaxCount;
	}
	
}