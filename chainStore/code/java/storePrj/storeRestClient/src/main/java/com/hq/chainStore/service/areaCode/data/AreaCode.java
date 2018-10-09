package com.hq.chainStore.service.areaCode.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "areaCode")
public class AreaCode implements IntfSynData {
	@Id
	private long id;
	// 国家代码
	private String areaCode;
	// 国家名称 中文
	private String countryCh;
	// 国家名称 英文
	private String countryEn;
	// 是否已删除
	private int entityState;

	private long createdTime;

	private long lastUpdateTime;

	private long ver;

	public static AreaCode newInstance() {
		return new AreaCode();
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getCountryCh() {
		return countryCh;
	}

	public void setCountryCh(String countryCh) {
		this.countryCh = countryCh;
	}

	public String getCountryEn() {
		return countryEn;
	}

	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}

}
