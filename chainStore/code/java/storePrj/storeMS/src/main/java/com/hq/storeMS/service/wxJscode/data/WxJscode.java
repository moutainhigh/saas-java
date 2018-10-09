package com.hq.storeMS.service.wxJscode.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;
import com.zenmind.wx.jscode2session.JsCode2SessionResponse;

@SynClass
@Table(name = "wxJscode")
public class WxJscode {
	@Id
	//jscode
	private String id;
	
	private int errcode;
	private String errmsg;
	private String openId;
	@SynIgnoreField
	private String sessionKey;
	private String unionId;

	public static WxJscode newInstance() {
		WxJscode data = new WxJscode();
		return data;
	}

	public static WxJscode newInstance(String jscode, JsCode2SessionResponse response) {
		WxJscode data = newInstance();
		FastBeanCopyer.getInstance().copy(response, data);
		data.id = jscode;
		return data;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
