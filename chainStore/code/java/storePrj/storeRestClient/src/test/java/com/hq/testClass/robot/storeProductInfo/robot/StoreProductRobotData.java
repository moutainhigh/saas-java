package com.hq.testClass.robot.storeProductInfo.robot;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeProductInfo.data.ProductInfoState;

public class StoreProductRobotData {
	private int mark;
	private long id;
	private long storeId;
	private String typeId;

	private String number;// 编号
	private String name;
	private float price;
	private float cost;// 成本
	private int state;
	private String descript;
	private String path;

	// 评分
	private float score;
	// 订单数量
	private long orderCount;

	public static StoreProductRobotData newInstance(int mark) {
		StoreProductRobotData robotData = new StoreProductRobotData();
		robotData.number = RandomStringUtils.random(10, "0123456789");
		robotData.name = "项目-" + mark;
		robotData.price = RandomUtils.nextFloat(1000f, 2000f);
		robotData.cost = RandomUtils.nextFloat(1000f, robotData.price);
		robotData.state = ProductInfoState.Open.ordinal();
		robotData.descript = "项目" + mark;
		robotData.path = "img/logo/store/zhimeitong-logo.png";
		return robotData;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}

}
