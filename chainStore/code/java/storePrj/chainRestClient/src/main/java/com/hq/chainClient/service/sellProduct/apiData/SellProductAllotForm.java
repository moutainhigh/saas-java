package com.hq.chainClient.service.sellProduct.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.service.sellProduct.data.SellProductTypeEnum;

public class SellProductAllotForm {
	private SellAllotId sellAllotId;
	// 适用门店
	private Set<Long> applyStoreIds = new HashSet<Long>();

	public static SellProductAllotForm newInstance() {
		return new SellProductAllotForm();
	}
	
	public static SellProductAllotForm newInstance(SellAllotId sellAllotIdP, Set<Long> applyStoreIdsP) {
		SellProductAllotForm data = new SellProductAllotForm();
		data.applyStoreIds = applyStoreIdsP;
		data.sellAllotId = sellAllotIdP;
		return data;
	}
	
	public SellProductTypeEnum getSellProductTypeEnum() {
		return sellAllotId.getSellProductTypeEnum();
	}

	public Set<Long> getApplyStoreIds() {
		return applyStoreIds;
	}

	public void setApplyStoreIds(Set<Long> applyStoreIds) {
		this.applyStoreIds = applyStoreIds;
	}

	public SellAllotId getSellAllotId() {
		return sellAllotId;
	}

	public void setSellAllotId(SellAllotId sellAllotId) {
		this.sellAllotId = sellAllotId;
	}
}
