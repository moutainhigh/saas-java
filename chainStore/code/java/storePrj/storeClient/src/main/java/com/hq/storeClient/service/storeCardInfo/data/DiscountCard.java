package com.hq.storeClient.service.storeCardInfo.data;

public class DiscountCard {
	private String id;// storeId_index
	private String name;
	private int cardType;// 卡片类型 对应cardTypeEnum枚举
	private float percent;// 折扣
	private float cash;// 返现
	private int consumeType;// 消费类型 对应consumeTypeEnum
	private int count;// 次数
	private long effectiveTime;// 有效日期
	private String backImg;// 背景图
	private String descript;// 描述
	private int status;// 卡片状态 对应CardStatusEnum

	public static DiscountCard newInstance() {
		DiscountCard data = new DiscountCard();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public float getCash() {
		return cash;
	}

	public void setCash(float cash) {
		this.cash = cash;
	}

	public int getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(int consumeType) {
		this.consumeType = consumeType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(long effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
