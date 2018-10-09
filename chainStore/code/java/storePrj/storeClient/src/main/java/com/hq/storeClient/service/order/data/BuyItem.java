package com.hq.storeClient.service.order.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 购买消费清单
 */

@SynClass
public class BuyItem {
	// 购买项ID buyType_pgId 方便退单时使用
	private String buyItemId;
	// 购买类型 BuyTypeEnum
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	// 售价
	private float price;
	// 数量
	private int count;
	// 折扣
	private float discount;
	// 总价
	private float cost;
	// 应结
	private float pay;
	// 预存数量
	private int restCount;

	public static BuyItem newInstance() {
		BuyItem data = new BuyItem();
		return data;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	public String getBuyItemId() {
		return buyItemId;
	}

	public void setBuyItemId(String buyItemId) {
		this.buyItemId = buyItemId;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}
}
