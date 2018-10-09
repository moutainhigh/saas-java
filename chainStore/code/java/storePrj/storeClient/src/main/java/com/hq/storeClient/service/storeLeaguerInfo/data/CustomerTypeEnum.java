package com.hq.storeClient.service.storeLeaguerInfo.data;


public enum CustomerTypeEnum {
	UNKONW("未知", 0f, 0, 0),
	HIGH_GRADE_CUSTOMER("优质客", 0f, 5, 0), 
	RISK_CUSTOMER("流失风险",0f, 2, 0),
	QUIESCENCE_CUSTOMER("静止客",0f, 1, 0),
	;

	private String type;//顾客类型  类型是根据顾客的消费总额、来店次数、最后来店时间三个条件来确定的。
	private float cost;//消费总额
	private int rate;//来店频率
	private int lastTime;//最后消费时间间隔  单位：月

	private CustomerTypeEnum(String type, float cost, int rate, int lastTime) {
		this.type = type;
		this.cost = cost;
		this.rate = rate;
		this.lastTime = lastTime;
	}

	public String getType() {
		return type;
	}

	public float getCost() {
		return cost;
	}

	public int getRate() {
		return rate;
	}

	public long getLastTime() {
		return lastTime;
	}

	public static CustomerTypeEnum valueOf(int ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
}
