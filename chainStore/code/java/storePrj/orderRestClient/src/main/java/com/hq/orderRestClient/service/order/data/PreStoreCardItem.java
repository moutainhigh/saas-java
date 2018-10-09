package com.hq.orderRestClient.service.order.data;

import com.hq.orderRestClient.common.constants.OrderClientConstants;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 预存卡消费清单
 */
@SynClass
public class PreStoreCardItem {
	// id _preStore_{itemType}_{pgId}_{preStoreCardId}
	private String id;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目/商品/套餐的ID
	private String pgId;
	// 客户预存卡的ID
	private String preStoreCardId;
	// 数量
	private int count;

	public static PreStoreCardItem newInstance(int itemTypeP, String pgIdP, String preStoreCardIdP) {
		PreStoreCardItem data = new PreStoreCardItem();
		data.itemType = itemTypeP;
		data.pgId = pgIdP;
		data.preStoreCardId = preStoreCardIdP;
		data.id = StringFormatUtil.format("{}_{}_{}_{}", OrderClientConstants.ORDER_PRESTORE_ITEM_ID_SUFFFIX, itemTypeP, pgIdP, preStoreCardIdP);
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPreStoreCardId() {
		return preStoreCardId;
	}

	public void setPreStoreCardId(String preStoreCardId) {
		this.preStoreCardId = preStoreCardId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
