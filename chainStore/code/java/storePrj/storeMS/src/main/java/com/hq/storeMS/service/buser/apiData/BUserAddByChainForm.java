package com.hq.storeMS.service.buser.apiData;

import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BuserOriginEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class BUserAddByChainForm {
	// 名称
	private String name;
	// 手机号码
	private long phone;
	// 密码
	private String password;
	// 性别
	private int gender;
	// 手机号码区号
	private String areaCode;
	// 连锁店ID
	private long chainId;
	private String salt;
	private String headImg;

	public static BUserAddByChainForm newInstance() {
		return new BUserAddByChainForm();
	}

	public BUser toBUser() {
		BUser buser = BUser.newInstance();
		FastBeanCopyer.getInstance().copy(this, buser);
		buser.getChainIds().add(chainId);
		buser.setOrigin(BuserOriginEnum.Chain.ordinal());
		return buser;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
