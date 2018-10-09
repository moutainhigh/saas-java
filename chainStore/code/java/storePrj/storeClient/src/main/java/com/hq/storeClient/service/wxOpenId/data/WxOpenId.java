package com.hq.storeClient.service.wxOpenId.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;
@SynClass
@Table(name = "wxOpenId")
public class WxOpenId {
	@Id
	private long id;
	//AppId
	private String appId;
	//密钥
	@SynIgnoreField
	private String secret;
	
	private String jsCode;
	
	private String openId;
	
	//创建时间
	private long createdTime;
	//最后修改时间
	private long lastUpdateTime;
	//版本
	private int ver;
	
	public static WxOpenId newInstance() {
		WxOpenId wxOpenId = new WxOpenId();
		long curTime = System.currentTimeMillis();
		wxOpenId.createdTime = curTime;
		wxOpenId.lastUpdateTime = curTime;
		return wxOpenId;
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
	public String getJsCode() {
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	
}
