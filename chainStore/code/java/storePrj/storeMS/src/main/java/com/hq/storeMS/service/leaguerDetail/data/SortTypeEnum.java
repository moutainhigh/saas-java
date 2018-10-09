package com.hq.storeMS.service.leaguerDetail.data;

public enum SortTypeEnum {
	LastUpdateTime("最后更新时间"), 
	LastConsumeTime("来店时间"), 
	AvgPrice("客单价"), 
	ConsumeAmount("消费总额"), 
	MonthRate("来店频率"),
	MemberCardEndTime("会员卡到期时间"),
	MemberCardBalance("会员卡余额"),
	;

	private String mark;

	private SortTypeEnum(String mark) {
		this.mark = mark;
	}

	public static SortTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getMark() {
		return mark;
	}
}
