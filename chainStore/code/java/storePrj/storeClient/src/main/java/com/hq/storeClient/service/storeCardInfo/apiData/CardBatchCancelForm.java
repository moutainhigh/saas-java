package com.hq.storeClient.service.storeCardInfo.apiData;

import java.util.ArrayList;
import java.util.List;

public class CardBatchCancelForm {
	private List<CardCancelForm> cancelForms = new ArrayList<CardCancelForm>();

	public static CardBatchCancelForm newInstance() {
		return new CardBatchCancelForm();
	}

	public List<CardCancelForm> getCancelForms() {
		return cancelForms;
	}

	public void setCancelForms(List<CardCancelForm> cancelForms) {
		this.cancelForms = cancelForms;
	}
}
