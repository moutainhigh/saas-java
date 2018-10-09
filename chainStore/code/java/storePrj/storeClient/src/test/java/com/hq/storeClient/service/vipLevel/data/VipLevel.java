package com.hq.storeClient.service.vipLevel.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "vipLevel")
public class VipLevel {

	@Id
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
	// 等级默认图片
	private String defualtImg;

	private int entityState;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static VipLevel newInstance() {
		VipLevel data = new VipLevel();
		return data;
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

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public VipContent getVipContent() {
		return vipContent;
	}

	public void setVipContent(VipContent vipContent) {
		this.vipContent = vipContent;
	}

	public String getDefualtImg() {
		return defualtImg;
	}

	public void setDefualtImg(String defualtImg) {
		this.defualtImg = defualtImg;
	}

}
