package com.hq.customerRestClient.service.order.data;

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

	public static DelimitCardItem newInstance() {
		DelimitCardItem data = new DelimitCardItem();
		return data;
	}

	public String getDelimitCardItemId() {
		return delimitCardItemId;
	}

	public void setDelimitCardItemId(String delimitCardItemId) {
		this.delimitCardItemId = delimitCardItemId;
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

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
