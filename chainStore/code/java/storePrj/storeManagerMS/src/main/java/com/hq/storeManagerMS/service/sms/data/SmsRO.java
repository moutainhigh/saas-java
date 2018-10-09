package com.hq.storeManagerMS.service.sms.data;


public interface SmsRO {
	public long getId();
	public long getPhone();
	public String getVerifyCode();
	public int getIsUse();
	public long getUsingTime();
	public long getCreatedTime();
	public long getLastUpdateTime();
	public int getVer();
}
