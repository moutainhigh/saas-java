package com.hq.storeMS.service.wxAccessToken.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "wxAccessToken")
public class WxAccessToken {

	@Id
	private long id;
	// AppId
	private String appId;

	// 有效时间，单位：秒
	private int expires_in;
	// token信息
	private String access_token;
	// 错误码
	private int errcode;
	// 错误信息
	private String errmsg;

	// 创建时间
	private long createdTime;
	// 最后修改时间
	private long lastUpdateTime;
	// 版本
	private int ver;
	
	/************ 遗留的字段 ************/
	// 密钥
	@SynIgnoreField
	private String secret;

	public static WxAccessToken newInstance() {
		WxAccessToken instance = new WxAccessToken();
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

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
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
}
