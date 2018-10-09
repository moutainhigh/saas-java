package com.hq.chainMS.service.chainClerk.apiData;

import java.util.ArrayList;
import java.util.List;

public class ChainBatchAllotStoreForm {
	private List<ChainAllotStoreForm> chainAllotStoreForms = new ArrayList<ChainAllotStoreForm>();

	public static ChainBatchAllotStoreForm newInstance() {
		return new ChainBatchAllotStoreForm();
	}

	public List<ChainAllotStoreForm> getChainAllotStoreForms() {
		return chainAllotStoreForms;
	}

	public void setChainAllotStoreForms(List<ChainAllotStoreForm> chainAllotStoreForms) {
		this.chainAllotStoreForms = chainAllotStoreForms;
	}

}
