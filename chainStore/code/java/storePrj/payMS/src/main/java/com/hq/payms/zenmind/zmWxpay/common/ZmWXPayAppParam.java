package com.hq.payms.zenmind.zmWxpay.common;

/**
 * 对于不同的普通商户，动态传入该商户的appId, mchId, key, certPath
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmWXPayAppParam {

	private String appId; //appId
	private String mchId; //商户号
	private String key; //API密钥,用于生成签名
	private String certPath; //商户证书
	
	public static ZmWXPayAppParam newInstance(String appId, String mchId, String key, String certPath) {
		ZmWXPayAppParam instance = new ZmWXPayAppParam();
		instance.appId = appId;
		instance.mchId = mchId;
		instance.key = key;
		instance.certPath = certPath;
		return instance;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	
}
