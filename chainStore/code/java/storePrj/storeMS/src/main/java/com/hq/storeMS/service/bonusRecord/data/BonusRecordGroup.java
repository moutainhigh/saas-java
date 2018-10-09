package com.hq.storeMS.service.bonusRecord.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BonusRecordGroup {
	//日期字符串; 格式：yyyyMM
	private String dateStr;
	// 用户ID
	private long buserId;
	// 业绩金额
	private float amountCost;
	// 提成金额
	private float bonusCost;
	
	/*******************************衍生信息*******************************/
	// 手机号码
	private long buserPhone;
	// 用户名称
	private String buserName;
	//头像
	private String buserHeadImg;
	//性别
	private int gender;
	// 岗位
	private Set<String> roleNames = new HashSet<String>();

	public static BonusRecordGroup newInstance() {
		return new BonusRecordGroup();
	}
	
	public void addRoleName(String roleName) {
		this.roleNames.add(roleName);
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

	public float getAmountCost() {
		return amountCost;
	}

	public void setAmountCost(float amountCost) {
		this.amountCost = amountCost;
	}

	public float getBonusCost() {
		return bonusCost;
	}

	public void setBonusCost(float bonusCost) {
		this.bonusCost = bonusCost;
	}

	public long getBuserPhone() {
		return buserPhone;
	}

	public void setBuserPhone(long buserPhone) {
		this.buserPhone = buserPhone;
	}

	public String getBuserHeadImg() {
		return buserHeadImg;
	}

	public void setBuserHeadImg(String buserHeadImg) {
		this.buserHeadImg = buserHeadImg;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Set<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

}
