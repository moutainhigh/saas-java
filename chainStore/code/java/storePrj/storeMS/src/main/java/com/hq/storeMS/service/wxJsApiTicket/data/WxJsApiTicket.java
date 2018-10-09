package com.hq.storeMS.service.wxJsApiTicket.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "wxJsApiTicket")
public class WxJsApiTicket {

	@Id
	private long id;
	//AppId
	private String appId;
	
	//jsapi_ticket
	@SynIgnoreField
	private String jsapiTicket;
	//时间戳
	private String timestamp;
	//随机数字符串
	private String nonceStr;
	//签名
	private String signature;
	
	
	//创建时间
	private long createdTime;
	//最后修改时间
	private long lastUpdateTime;
	//版本
	private int ver;
	
	/************ 遗留的字段 ************/
	
	//密钥
	@SynIgnoreField
	private String secret;
	
	//access_token
	@SynIgnoreField
	private String accessToken;
	
	//App的URL
	@SynIgnoreField
	private String appUrl;
	
	public static WxJsApiTicket newInstance() {
		WxJsApiTicket instance = new WxJsApiTicket();
		long curTime = System.currentTimeMillis();
		instance.createdTime = curTime;
		instance.lastUpdateTime = curTime;
		return instance;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getVer() {
		return ver;
	}
	public void setVer(int ver) {
		this.ver = ver;
	}

	@Override
	public String toString() {
		return "WxJsApiTicket [id=" + id + ", appId=" + appId + ", secret=" + secret + ", appUrl=" + appUrl
				+ ", accessToken=" + accessToken + ", jsapiTicket=" + jsapiTicket + ", timestamp=" + timestamp
				+ ", nonceStr=" + nonceStr + ", signature=" + signature + ", createdTime=" + createdTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", ver=" + ver + "]";
	}
	
}
