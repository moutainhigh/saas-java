package com.hq.chainClient.service.sellProduct.apiData;

import java.util.ArrayList;
import java.util.List;

public class SellProductBatchUpdateStateForm {
	private List<SellProductUpdateStateForm> sellProductUpdateStateForms = new ArrayList<SellProductUpdateStateForm>();

	public static SellProductBatchUpdateStateForm newInstance() {
		return new SellProductBatchUpdateStateForm();
	}

	public List<SellProductUpdateStateForm> getSellProductUpdateStateForms() {
		return sellProductUpdateStateForms;
	}

	public void setSellProductUpdateStateForms(List<SellProductUpdateStateForm> sellProductUpdateStateForms) {
		this.sellProductUpdateStateForms = sellProductUpdateStateForms;
	}
}
