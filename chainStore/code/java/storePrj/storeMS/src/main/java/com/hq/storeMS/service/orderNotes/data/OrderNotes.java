package com.hq.storeMS.service.orderNotes.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "orderNotes")
public class OrderNotes {
	@Id
	// 订单ID
	private long id;
	// 店铺ID
	@IndexField
	private long storeId;

	// 订单付款备注信息
	private OrderPayRemark orderPayRemark;

	// 退单记录
	private int chargeBackRecordIndex = 0;
	private Map<Integer, ChargeBackRecord> chargeBackRecordMap = new HashMap<Integer, ChargeBackRecord>();

	// 会员充值 撤单
	private RevokeContent revokeContent;

	@IndexField
	private long createTime;
	private long lastUpdateTime;
	private long ver;

	public static OrderNotes newInstance(long storeId, long orderId) {
		OrderNotes data = new OrderNotes();
		long curTime = System.currentTimeMillis();
		data.createTime = curTime;
		data.lastUpdateTime = curTime;
		data.id = orderId;
		data.storeId = storeId;
		return data;
	}

	public Map<String, ItemCountAndCost> takeItemCountAndCostMap() {
		Map<String, ItemCountAndCost> result = new HashMap<String, ItemCountAndCost>();
		Collection<ChargeBackRecord> values = chargeBackRecordMap.values();
		for (ChargeBackRecord chargeBackRecord : values) {
			List<ChargeBackItem> chargeBackItems = chargeBackRecord.getChargeBackItems();
			for (ChargeBackItem chargeBackItem : chargeBackItems) {
				ItemCountAndCost itemCountAndCost = result.get(chargeBackItem.getItemId());
				if (itemCountAndCost == null) {
					itemCountAndCost = ItemCountAndCost.newInstance(chargeBackItem.getItemId());
					result.put(chargeBackItem.getItemId(), itemCountAndCost);
				}
				itemCountAndCost.setCost(itemCountAndCost.getCost() + chargeBackItem.getCost());
				itemCountAndCost.setCount(itemCountAndCost.getCount() + chargeBackItem.getCount());
			}
		}
		return result;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getChargeBackRecordIndex() {
		return chargeBackRecordIndex;
	}

	public void setChargeBackRecordIndex(int chargeBackRecordIndex) {
		this.chargeBackRecordIndex = chargeBackRecordIndex;
	}

	public Map<Integer, ChargeBackRecord> getChargeBackRecordMap() {
		return chargeBackRecordMap;
	}

	public void setChargeBackRecordMap(Map<Integer, ChargeBackRecord> chargeBackRecordMap) {
		this.chargeBackRecordMap = chargeBackRecordMap;
	}

	public OrderPayRemark getOrderPayRemark() {
		return orderPayRemark;
	}

	public void setOrderPayRemark(OrderPayRemark orderPayRemark) {
		this.orderPayRemark = orderPayRemark;
	}

	public RevokeContent getRevokeContent() {
		return revokeContent;
	}

	public void setRevokeContent(RevokeContent revokeContent) {
		this.revokeContent = revokeContent;
	}

}
