package com.hq.storeClient.service.productCardDetail.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 次卡内容
 */
@SynClass
public class ProductCardItem {
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目/商品/套餐 简版信息ID  -1为不限项目
	private String pgId;
	// 数量 -1为无限次
	private int count;
	// 单次折后价
	private float discountPrice;

	public static ProductCardItem newInstance() {
		ProductCardItem data = new ProductCardItem();
		return data;
	}

	public static ProductCardItem newInstanceByPrdAndTime(String productIdP, int countP) {
		ProductCardItem data = newInstance();
		data.itemType = ProductCardItemEnum.PRODUCT.ordinal();
		data.pgId = productIdP;
		data.count = countP;
		return data;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}
}
