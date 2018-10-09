package com.hq.storeMS.service.storeGoods.data;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class GoodsTmp {
	private long id;

	private String number;

	private String name;

	private String typeName;

	private float price;

	private float cost;
	// 状态 对应GoodsStateEnum
	private int state;

	private String descript;

	private float score;

	private long orderCount;

	private List<String> imgPaths = new ArrayList<String>();

	public static GoodsTmp newInstance() {
		GoodsTmp data = new GoodsTmp();
		data.score = 0f;
		data.orderCount = 0L;

		return data;
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

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}

}
