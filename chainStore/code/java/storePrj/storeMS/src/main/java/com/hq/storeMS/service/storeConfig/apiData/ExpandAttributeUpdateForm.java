package com.hq.storeMS.service.storeConfig.apiData;

import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerExpandAttribute;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ExpandAttributeUpdateForm {
	// 扩展属性ID
	private int id;
	// 属性名称 中文
	private String label;
	// 属性类型 对应AttributeTypeEnum
	private int attributeType;
	// 输入提示
	private String tips;

	public static ExpandAttributeUpdateForm newInstance() {
		return new ExpandAttributeUpdateForm();
	}
	
	public void updateLeaguerExpandAttribute(LeaguerExpandAttribute data) {
		FastBeanCopyer.getInstance().copy(this, data);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
}
