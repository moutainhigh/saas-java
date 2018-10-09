package com.hq.chainMS.service.chainClerk.apiData;

import com.hq.chainMS.service.chainUser.data.ChainUser;

public class ChainClerkUpdateInfoForm {
	private long id;
	// 名称
	private String name;
	// 性别 对应 GenderEnum
	private int gender;
	// 密码
	private String password;
	// 是否是跨店员工 对应 CrossClerkEnum
	private int crossClerk;

	public static ChainClerkUpdateInfoForm newInstance() {
		return new ChainClerkUpdateInfoForm();
	}
	
	public void updateChainUser(ChainUser user) {
		//修改密码
//		String encryptPassword = ChainUserPwdHelper.getInstance().getEncryptPassword(user, password);
//		user.setPassword(encryptPassword);
		user.setName(name);
		user.setGender(gender);
		user.setCrossClerk(crossClerk);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCrossClerk() {
		return crossClerk;
	}

	public void setCrossClerk(int crossClerk) {
		this.crossClerk = crossClerk;
	}

}
