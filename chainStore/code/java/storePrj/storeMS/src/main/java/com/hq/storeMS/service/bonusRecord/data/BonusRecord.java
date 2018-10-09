package com.hq.storeMS.service.bonusRecord.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "bonusRecord")
public class BonusRecord {
	@Id
	private long id;
	// 店铺ID
	@IndexField
	private long storeId;
	// 订单号
	private String orderNumber;
	// 订单ID
	@IndexField
	private long orderId;
	@IndexField
	// 订单时间
	private long orderTime;
	
	
	// 类型 BuyTypeEnum
	private int buyType;
	// 名称
	private String buyName;
	// 项目ID、次卡ID、商品ID、套餐ID 充值[-] 支付方式_购买类型_购买项ID_客户次卡ID
	private String buyId;
	//项目ID、次卡ID、商品ID
	private String pgId;

	// 服务员ID
	private long buserId;
	private String buserName;
	// 业绩金额
	private float amount;
	// 提成方式 BonusTypeEnum 固定提成 比例提成
	private int bonusType;
	// 提成比例
	private float percentage;
	// 提成金额
	private float cost;
	// 提成的状态 BonusRecordStatusEnum
	private int status;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static BonusRecord newInstance() {
		BonusRecord data = new BonusRecord();
		data.status = BonusRecordStatusEnum.UNVALID.ordinal();

		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getBonusType() {
		return bonusType;
	}

	public void setBonusType(int bonusType) {
		this.bonusType = bonusType;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}
}
