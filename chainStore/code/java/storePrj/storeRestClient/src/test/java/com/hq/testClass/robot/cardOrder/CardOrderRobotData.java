package com.hq.testClass.robot.cardOrder;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.order.data.OrderStatusEnum;


public class CardOrderRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;
	
	private float price;
	
	private int status;// 已支付
	private int payType;// 支付方式
	private float cost;//实付金额

	public static CardOrderRobotData newInstance(int mark) {
		CardOrderRobotData data = new CardOrderRobotData();
		data.price = RandomUtils.nextFloat(1000f, 5000f);
		data.status = OrderStatusEnum.HAS_PAY.ordinal();
		data.payType = RandomUtils.nextInt(1, 5);
		data.cost = RandomUtils.nextFloat(1000f, 5000f);
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

}
