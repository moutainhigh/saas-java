package com.hq.storeManagerRestClient.testClass.robot.vipLevel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.hq.storeManagerRestClient.service.vipLevel.data.VipContent;

public class VipLevelRobotData {

	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private long id;
	// 等级编号
	private String number;
	// 等级名称
	private String name;
	// 等级分类VipLevelEnum
	private int type;
	// 等级时长 12月
	private int validPeriod;
	// 单位 对应 ValidPeriodUnitEnum
	private int validPeriodUnit;
	// 开通收费
	private long openCharge;
	// 续费收费
	private long renewCharge;
	// 等级状态 VipLevelStateEnum
	private int state;
	// 等级内容
	private VipContent vipContent = new VipContent();
	// 等级图片列表
	private List<String> imgPaths = new ArrayList<String>();

	private int entityState;

	public static VipLevelRobotData newInstance(int mark) {
		VipLevelRobotData data = new VipLevelRobotData();
		data.mark = mark;
		data.id = mark;
		data.number = RandomStringUtils.random(6, "123456789");
		data.name = "测试等级-" + mark;
		data.type = 0;
		data.validPeriod = 20;
		data.validPeriodUnit = 1;
		data.openCharge = 2500;
		data.renewCharge = 2000;
		data.state = 0;
			VipContent vipContent = new VipContent();
			vipContent.setVipType(mark);
			vipContent.setStoreLimit(20);
			vipContent.setBuserLimit(20);
			vipContent.setGoodsLimit(20);
			vipContent.setLeaguerLimit(20);
			vipContent.setMemberCardLimit(20);
			vipContent.setPackageLimit(20);
			vipContent.setPackageLimit(20);
			vipContent.setPrdCardLimit(20);
		data.vipContent = vipContent;
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}

	public int getValidPeriodUnit() {
		return validPeriodUnit;
	}

	public void setValidPeriodUnit(int validPeriodUnit) {
		this.validPeriodUnit = validPeriodUnit;
	}

	public long getOpenCharge() {
		return openCharge;
	}

	public void setOpenCharge(long openCharge) {
		this.openCharge = openCharge;
	}

	public long getRenewCharge() {
		return renewCharge;
	}

	public void setRenewCharge(long renewCharge) {
		this.renewCharge = renewCharge;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public VipContent getVipContent() {
		return vipContent;
	}

	public void setVipContent(VipContent vipContent) {
		this.vipContent = vipContent;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

}
