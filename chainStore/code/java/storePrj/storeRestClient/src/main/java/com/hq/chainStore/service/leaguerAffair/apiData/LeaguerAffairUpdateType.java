package com.hq.chainStore.service.leaguerAffair.apiData;

public enum LeaguerAffairUpdateType {
	AddMembershipCard("绑定会员卡"),
	AddDiscountCard("绑定优惠卷"),
	DelMembershipCard("解绑会员卡"),
	DelDiscountCard("解绑优惠卷"),
	
	AddArchives("添加客户档案"),
	AddAlarmClock("添加客户闹钟"),
	DelArchives("删除客户档案"),
	DelAlarmClock("删除客户闹钟"),
	
	AddProductCard("绑定耗卡"),
	DelProductCard("解绑耗卡"),
	;
	
	private String mark;
	
	private LeaguerAffairUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static LeaguerAffairUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
