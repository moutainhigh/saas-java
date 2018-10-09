package com.hq.chainStore.service.storeGoods.apiData;

import java.util.ArrayList;
import java.util.List;

public class GoodsBatchCancelForm {
	private List<GoodsCancelForm> cancelForms = new ArrayList<GoodsCancelForm>();

	public static GoodsBatchCancelForm newInstance() {
		return new GoodsBatchCancelForm();
	}

	public List<GoodsCancelForm> getCancelForms() {
		return cancelForms;
	}

	public void setCancelForms(List<GoodsCancelForm> cancelForms) {
		this.cancelForms = cancelForms;
	}
}
