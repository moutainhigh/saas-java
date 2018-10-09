package com.hq.orderRestClient.service.order.data;

import com.hq.orderRestClient.common.constants.OrderClientConstants;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 划卡消费清单
 */
@SynClass
public class DelimitCardItem {
	// 划卡消费ID _delimitCard_{itemType}_{pgId}_{leaguerPrdCardId} 方便退单时使用
	private String delimitCardItemId;
	// 类型 ProductCardItemEnum
	private int itemType;
	// 项目/商品/套餐的ID
	private String pgId;
	// 客户次卡的ID
	private String leaguerPrdCardId;
	// 数量
	private int count;

	public static DelimitCardItem newInstance(int itemTypeP, String pgIdP, String leaguerPrdCardIdP) {
		DelimitCardItem data = new DelimitCardItem();
		data.itemType = itemTypeP;
		data.pgId = pgIdP;
		data.leaguerPrdCardId = leaguerPrdCardIdP;
		data.delimitCardItemId = StringFormatUtil.format("{}_{}_{}_{}", OrderClientConstants.ORDER_DELIMITCARD_ITEM_ID_SUFFFIX, itemTypeP, pgIdP, leaguerPrdCardIdP);
		return data;
	}

	public String getDelimitCardItemId() {
		return delimitCardItemId;
	}

	public void setDelimitCardItemId(String delimitCardItemId) {
		this.delimitCardItemId = delimitCardItemId;
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

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
}
