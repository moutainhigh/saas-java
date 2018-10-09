package com.hq.chainStore.service.storeVipType.data;

public enum StoreVipLevelEnum {

	ExperienceUser("体验用户"),//会员初始状态为“体验用户”，通过设置变为其它用户，体验时长为1个月
	InnerBetaUser("内测用户"),//内测会员，体验时长为1年
	SilverUser("白银用户"),
	GoldUser("黄金用户"),
	DiamondUser("钻石用户"),
	HonKonUser("宏强定制会员"),
	StandardUser("标准会员"),
	;
	
	private String mark;
	
	private StoreVipLevelEnum(String mark){
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static StoreVipLevelEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
}
