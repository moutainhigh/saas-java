package com.hq.storeManagerMS.service.sms.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

//短信记录，方便统计  筛选黑名单等等。
@SynClass
@Table(name = "sms")
public class Sms implements SmsRO {
	@Id
	private long id;

	//手机号码
	@IndexField
	private long phone;
	//验证码
	private String verifyCode; 
	//是否使用  SmsUseEnum
	@IndexField
	private int isUse;
	//使用时间
	private long usingTime;

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static Sms newInstance() {
		Sms data = new Sms();
		
		data.isUse = SmsUseEnum.NOT_USE.ordinal();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;

		return data;
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public long getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(long usingTime) {
		this.usingTime = usingTime;
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
