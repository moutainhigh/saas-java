package com.hq.storeMS.service.bonusRecord.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 订单提成  订单对应的提成列表   
 * 后期应该使用该类进行优化
 * bonusRecord 与 OrderBonus 的相互转换
 * @author kevin
 *
 */
@SynClass
public class OrderBonus {
	//订单ID
	@Id
	private long id;
	// 店铺ID
	@IndexField
	private long storeId;
	// 订单号
	private String orderNumber;
	@IndexField
	// 订单时间
	private long orderTime;
	// 提成的状态 BonusRecordStatusEnum
	private int status;

	private Map<String, BonusInfo> bonusInfoMap = new HashMap<String, BonusInfo>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static OrderBonus newInstance() {
		OrderBonus data = new OrderBonus();
		data.status = BonusRecordStatusEnum.UNVALID.ordinal();
		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, BonusInfo> getBonusInfoMap() {
		return bonusInfoMap;
	}

	public void setBonusInfoMap(Map<String, BonusInfo> bonusInfoMap) {
		this.bonusInfoMap = bonusInfoMap;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}
}
