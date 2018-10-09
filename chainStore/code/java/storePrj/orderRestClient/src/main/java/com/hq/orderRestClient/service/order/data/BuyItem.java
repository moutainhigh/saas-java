package com.hq.orderRestClient.service.order.data;

import java.util.HashSet;
import java.util.Set;

import com.hq.orderRestClient.common.constants.OrderClientConstants;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 购买消费清单
 */
@SynClass
public class BuyItem {
	// 购买项ID _buy_{buyType}_{pgId} 方便退单时使用
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

	/****************************** 遗留字段 ******************************/
	// 名称
	private String pgName;
	// 客户次卡的ID
	private String prdCardId;
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();
	// 结算方式 现结/赠送
	private String payName;

	public static BuyItem newInstance(int buyTypeP, String pgIdP) {
		BuyItem data = new BuyItem();
		data.buyType = buyTypeP;
		data.pgId = pgIdP;
		data.buyItemId = StringFormatUtil.format("{}_{}_{}", OrderClientConstants.ORDER_BUY_ITEM_ID_SUFFFIX, buyTypeP,
				pgIdP);
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

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public String getPrdCardId() {
		return prdCardId;
	}

	public void setPrdCardId(String prdCardId) {
		this.prdCardId = prdCardId;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}
}
