package com.hq.storeMS.service.workFlowData.data;

import com.hq.orderRestClient.service.order.data.PreStoreCardItem;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PreStoreCardRecord {
	// 划卡ID preStoreCardId_pgId_itemType
	private String id;
	// 客户预存卡ID
	private String preStoreCardId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目、商品、套餐ID
	private String pgId;
	// 抵消次数
	private int count;
	// 创建时间
	private long createTime;

	public static PreStoreCardRecord newInstance(String preStoreCardIdP, String pgIdP, int itemTypeP) {
		PreStoreCardRecord data = new PreStoreCardRecord();
		data.createTime = System.currentTimeMillis();
		data.preStoreCardId = preStoreCardIdP;
		data.itemType = itemTypeP;
		data.pgId = pgIdP;
		data.id = StringFormatUtil.format("{}_{}_{}", preStoreCardIdP, pgIdP, itemTypeP);
		return data;
	}
	
	public PreStoreCardItem toPreStoreCardItem() {
		PreStoreCardItem item = PreStoreCardItem.newInstance(this.itemType, this.pgId, this.preStoreCardId);
		FastBeanCopyer.getInstance().copy(this, item);
		return item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPreStoreCardId() {
		return preStoreCardId;
	}

	public void setPreStoreCardId(String preStoreCardId) {
		this.preStoreCardId = preStoreCardId;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
