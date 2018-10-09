package com.hq.storeClient.service.dataReport.data;

import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 销售统计实体
 * 
 * @author kevin
 *
 */
@SynClass
@Table(name = "dataReport")
public class DataReport {
	private long orderId;
	// 订单编号
	private String orderNumber;
	// 客户ID
	private String leaguerId;
	// 创建时间
	private long createdTime;
	// 付款时间
	private long payTime;
	// 店铺ID
	private long storeId;
	// C端用户ID
	private long cuserId;
	// 创建者ID
	private long creatorId;

	// 购买类型 BuyTypeEnum
	private int buyType;
	// 应结
	private float pay;
	// 项目/商品/次卡的ID
	private String pgId;
	// 数量
	private int count;

	public static DataReport newInstance() {
		DataReport data = new DataReport();
		return data;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
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

}
