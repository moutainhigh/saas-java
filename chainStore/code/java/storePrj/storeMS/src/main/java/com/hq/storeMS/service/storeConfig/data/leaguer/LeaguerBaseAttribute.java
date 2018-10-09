package com.hq.storeMS.service.storeConfig.data.leaguer;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 客户基础属性
 *
 */
@SynClass
public class LeaguerBaseAttribute {
	// 标签名 中文
	private String label;
	// 属性名 英文
	private String attributeName;
	// 是否启用 对应LeaguerAttributeStateEnum
	private int status;
	// 是否必填 对应RequiredEnum
	private int require;

	public static LeaguerBaseAttribute newInstance() {
		LeaguerBaseAttribute data = new LeaguerBaseAttribute();
		data.status = LeaguerAttributeStateEnum.Enable.ordinal();
		return data;
	}

	public static LeaguerBaseAttribute newInstance(SysInitLeaguerBaseAttributeEnum sysInitLeaguerBaseAttributeEnum) {
		LeaguerBaseAttribute data = newInstance();
		data.label = sysInitLeaguerBaseAttributeEnum.getLable();
		data.attributeName = sysInitLeaguerBaseAttributeEnum.getAttributeName();
		data.require = sysInitLeaguerBaseAttributeEnum.getRequire();
		return data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public int getRequire() {
		return require;
	}

	public void setRequire(int require) {
		this.require = require;
	}
}
