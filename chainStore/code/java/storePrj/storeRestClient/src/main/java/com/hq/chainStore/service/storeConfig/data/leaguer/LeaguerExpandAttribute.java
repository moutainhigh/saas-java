package com.hq.chainStore.service.storeConfig.data.leaguer;

/**
 * 客户扩展属性
 *
 */
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
		return new LeaguerExpandAttribute();
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
