package com.hq.orderMS.service.order.data;

import com.hq.orderMS.common.constants.ServerConstants;
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

	public static DelimitCardItem newInstance() {
		return new DelimitCardItem();
	}
	
	public static DelimitCardItem newInstanceByBuyItem(BuyItem item) {
		DelimitCardItem data = newInstance();
		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(item.getBuyType());
		int itemType = ProductCardItemEnum.PRODUCT.ordinal();
		switch (buyTypeEnum) {
		case GOODS:
			itemType = ProductCardItemEnum.GOODS.ordinal();
			break;
		case PACKAGE:
			itemType = ProductCardItemEnum.PACKAGE.ordinal();
			break;
		default:
			break;
		}
		String id = StringFormatUtil.format("{}_{}_{}_{}", ServerConstants.ORDER_DELIMITCARD_ITEM_ID_SUFFFIX, itemType, item.getPgId(), item.getPrdCardId());
		data.delimitCardItemId = id;
		data.itemType = itemType;
		data.pgId = item.getPgId();
		data.leaguerPrdCardId = item.getPrdCardId();
		data.count = item.getCount();
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
