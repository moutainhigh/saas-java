package com.hq.storeMS.service.order.apiData;

import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.storeMS.common.constants.ServerConstants;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 商城的购买消费清单
 */
@SynClass
public class BuyItemForCuser {
	// 购买类型 BuyTypeEnum
	private int buyType;
	// 项目/商品/次卡/套餐的ID
	private String pgId;
	// 原价
	private float oldPrice;
	// 折扣
	private float discount;
	// 数量
	private int count;
	//预存数量
	private int restCount;

	public static BuyItemForCuser newInstance() {
		BuyItemForCuser data = new BuyItemForCuser();
		return data;
	}
	
	public BuyItem toBuyItem() {
		BuyItem data = BuyItem.newInstance(buyType, pgId);
		FastBeanCopyer.getInstance().copy(this, data);
		data.setPrice(BigDecimalUtil.round(oldPrice * discount / ServerConstants.DISCOUNT_NUM, 2));
		data.setCost(BigDecimalUtil.round(oldPrice * count, 2));
		data.setPay(BigDecimalUtil.round(oldPrice * count * discount / ServerConstants.DISCOUNT_NUM, 2));
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

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}

}
