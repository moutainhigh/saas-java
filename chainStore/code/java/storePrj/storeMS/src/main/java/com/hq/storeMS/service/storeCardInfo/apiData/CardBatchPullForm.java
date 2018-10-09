package com.hq.storeMS.service.storeCardInfo.apiData;

import java.util.ArrayList;
import java.util.List;

public class CardBatchPullForm {
	private List<CardPullForm> pullForms = new ArrayList<CardPullForm>();

	public static CardBatchPullForm newInstance() {
		return new CardBatchPullForm();
	}

	public List<CardPullForm> getPullForms() {
		return pullForms;
	}

	public void setPullForms(List<CardPullForm> pullForms) {
		this.pullForms = pullForms;
	}

}
