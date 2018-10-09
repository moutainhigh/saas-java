package com.hq.storeMS.service.storeConfig.data.leaguer;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 客户扩展属性
 *
 */
@SynClass
public class LeaguerExpandAttribute {
	private int id;
	// 属性名称 中文
	private String label;
	// 是否启用 对应LeaguerAttributeStateEnum
	private int status;
	// 是否必填 对应RequiredEnum
	private int require;
	// 排序下标
	private int sort;
	// 属性类型 对应AttributeTypeEnum
	private int attributeType;
	// 输入提示
	private String tips;

	public static LeaguerExpandAttribute newInstance() {
		LeaguerExpandAttribute data = new LeaguerExpandAttribute();
		data.status = LeaguerAttributeStateEnum.Enable.ordinal();
		data.require = RequiredEnum.Selection.ordinal();
		return data;
	}
	
	public static LeaguerExpandAttribute newInstance(SysInitExpandAttributeEnum attributeEnum) {
		LeaguerExpandAttribute data = newInstance();
		data.label = attributeEnum.getAttributeName();
		data.attributeType = AttributeTypeEnum.SingleLine.ordinal();
		data.id = attributeEnum.ordinal() + 1;
		data.sort = data.id;
		return data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(int attributeType) {
		this.attributeType = attributeType;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequire() {
		return require;
	}

	public void setRequire(int require) {
		this.require = require;
	}

}
