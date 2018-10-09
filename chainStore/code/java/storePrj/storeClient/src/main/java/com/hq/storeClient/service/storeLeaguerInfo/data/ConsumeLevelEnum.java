package com.hq.storeClient.service.storeLeaguerInfo.data;


public enum ConsumeLevelEnum {
	POOR(0L, "屌丝消费"),
	NORMAL(50000L,"平民消费"),
	KING(100000L, "王者消费"), 
	;

	private String mark;
	private long val;

	private ConsumeLevelEnum(long val, String mark) {
		this.val = val;
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public long getVal() {
		return val;
	}

	public static ConsumeLevelEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
