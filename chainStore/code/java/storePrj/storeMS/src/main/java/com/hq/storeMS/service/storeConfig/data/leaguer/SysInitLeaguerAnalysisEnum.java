package com.hq.storeMS.service.storeConfig.data.leaguer;

public enum SysInitLeaguerAnalysisEnum {
	HIGH_GRADE_CUSTOMER(20l, "优质会员阈值", 0), 
	RISK_CUSTOMER(20l, "流失风险会员阈值", 1), 
	QUIESCENCE_CUSTOMER(60l, "静止会员阈值", 1),
	PRODUCTCARD_EXPIRED_THRESHOLD(30l, "次卡即将过期阈值", 0),
	MEMBERCARD_EXPIRED_THRESHOLD(30l,"会员卡即将过期阈值",0),
	MEMBERCARD_BALANCE_THRESHOLD(100l,"会员卡余额不足阈值",0),
	
	;

	private String name;
	private long threshold;
	private int compare;

	private SysInitLeaguerAnalysisEnum(long thresholdP, String nameP, int compareP) {
		this.name = nameP;
		this.threshold = thresholdP;
		this.compare = compareP;
	}

	public static SysInitLeaguerAnalysisEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

	public String getName() {
		return name;
	}

	public long getThreshold() {
		return threshold;
	}

	public int getCompare() {
		return compare;
	}
}
