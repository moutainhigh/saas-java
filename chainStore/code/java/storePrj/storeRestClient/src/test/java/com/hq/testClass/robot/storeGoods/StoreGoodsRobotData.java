package com.hq.testClass.robot.storeGoods;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeGoods.data.GoodsStateEnum;

public class StoreGoodsRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;
	private long storeId;

	// 商品ID 或者 分类ID
	private long id;
	// 商品编号
	private String number;
	// 名称
	private String name;
	// 分类名称
	private String typeName;
	// 商品分类Id
	private String typeId;
	// 售价
	private float price;
	// 成本
	private float cost;
	// 状态 对应GoodsStateEnum
	private int state;
	// 描述
	private String descript;
	// 评分
	private float score;
	// 订单数量
	private long orderCount;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static StoreGoodsRobotData newInstance(int mark) {
		StoreGoodsRobotData data = new StoreGoodsRobotData();
		data.mark = mark;
		data.number = UUID.randomUUID().toString();
		data.name = "商品-" + mark;
		data.typeName = "商品分类-" + mark;
		data.price = RandomUtils.nextFloat(100.0f, 1000.0f);
		data.cost = RandomUtils.nextFloat(100.0f, data.price);
		data.state = GoodsStateEnum.Open.ordinal();
		data.descript = "描述-" + mark;
		data.score = 0;
		data.orderCount = 0;
		data.imgPaths.add("img/logo/store/zhimeitong-logo.png");
		return data;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

}
