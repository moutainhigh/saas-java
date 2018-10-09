package com.hq.storeMS.service.storeConfig.data.leaguer;

/**
 * 客户基础属性
 *
 */
public enum SysInitLeaguerBaseAttributeEnum {
	name("姓名","name", 0), 
	sex("性别", "sex", 0),
	phone("手机号码", "phone", 0),
	headImg("头像", "headImg", 1),
	buserIds("跟进人员", "buserIds", 1),
	birthday("出生日期", "birthday", 1),
	dateType("阴历阳历", "dateType", 1),
	idCard("身份证", "idCard", 1),
	aliasName("昵称", "aliasName", 1),
	wechatNumber("微信号", "wechatNumber", 1),
	recommender("介绍人", "recommender", 1),
	originId("客户来源", "originId", 1),
	address("联系地址", "address", 1),
	company("工作单位", "company", 1),
	job("职位", "job", 1),
	labelIds("标签", "labelIds", 1),
	;

	private String lable;
	private String attributeName;
	private int require;

	private SysInitLeaguerBaseAttributeEnum(String lableP, String attributeNameP, int requireP) {
		this.lable = lableP;
		this.attributeName = attributeNameP;
		this.require = requireP;
	}

	public static SysInitLeaguerBaseAttributeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getLable() {
		return lable;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public int getRequire() {
		return require;
	}

}
