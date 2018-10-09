package com.hq.chainClient.testClass.robot.chainClerk;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainClerk.apiData.ChainClerkAddForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChainClerkRobotData {
	// 名称
	private String name;
	// 手机号码
	private long phone;
	// 验证码
	private String verifyCode;
	// 手机号码区号
	private String areaCode;
	private String password;

	public static ChainClerkRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		ChainClerkRobotData data = new ChainClerkRobotData();
		data.name="name"+random;
		data.phone=13500000000L+RandomUtils.nextLong(1, 10000000);
		data.areaCode="+86";
		data.verifyCode="0021";
		data.password="123456";
		return data;
	}
	
	public ChainClerkAddForm toChainClerkAddForm(long chainId) {
		ChainClerkAddForm data = ChainClerkAddForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setChainId(chainId);
		return data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
