package com.hq.storeMS.service.leaguerDetail.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.leaguerDetail.data.PreStoreCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerCardEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerPrdCardItem;

public class PreStoreCardAddForm {
	private String leaguerId;
	// 订单ID
	private long orderId;
	// 次卡内容
	private List<LeaguerPrdCardItem> leaguerPrdCardItems = new ArrayList<LeaguerPrdCardItem>();

	public static PreStoreCardAddForm newInstance() {
		return new PreStoreCardAddForm();
	}
	
	public PreStoreCard toPreStoreCard() {
		PreStoreCard data = PreStoreCard.newInstance();
		data.setOrderId(orderId);
		data.setId(PreStoreCard.genPreStoreCardId(leaguerId, orderId));
		data.setState(LeaguerCardEnum.NOTUSE.ordinal());
		data.setLeaguerPrdCardItems(leaguerPrdCardItems);
		return data;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<LeaguerPrdCardItem> getLeaguerPrdCardItems() {
		return leaguerPrdCardItems;
	}

	public void setLeaguerPrdCardItems(List<LeaguerPrdCardItem> leaguerPrdCardItems) {
		this.leaguerPrdCardItems = leaguerPrdCardItems;
	}

}
