package com.hq.storeMS.service.homePage.data;


public enum HomePageItemEnum{
	Leaguer("客户"),
	Card("卡包"),
	Product("项目"),
	Goods("商品"),
	Clerk("员工"),
	WorkFlowType("流程类型"),
	StatisticsData("统计数据"),
	;
	
	private String mark;
	
	private HomePageItemEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static HomePageItemEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
