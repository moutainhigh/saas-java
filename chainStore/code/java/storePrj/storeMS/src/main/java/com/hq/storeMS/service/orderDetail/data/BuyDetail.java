package com.hq.storeMS.service.orderDetail.data;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class BuyDetail {
	// 购买项ID buyType_pgId 方便退单时使用
	private String buyItemId;
	// 购买类型 BuyTypeEnum
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	private String pgName;
	private String typeName;
	// 默认图片
	private String defaultImg;
	// 原价
	private float oldPrice;
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

	public static BuyDetail newInstance() {
		BuyDetail data = new BuyDetail();
		return data;
	}

	public static BuyDetail newInstanceByBuyItem(BuyItem item, ConsumeItem consumeItem) {
		BuyDetail data = new BuyDetail();
		FastBeanCopyer.getInstance().copy(item, data);
		data.pgName = consumeItem.getPgName();
		data.oldPrice = consumeItem.getPrice();
		data.defaultImg = consumeItem.getDefaultImg();
		data.typeName = consumeItem.getTypeName();
		return data;
	}

	public String getBuyItemId() {
		return buyItemId;
	}

	public void setBuyItemId(String buyItemId) {
		this.buyItemId = buyItemId;
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

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
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

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}

}
