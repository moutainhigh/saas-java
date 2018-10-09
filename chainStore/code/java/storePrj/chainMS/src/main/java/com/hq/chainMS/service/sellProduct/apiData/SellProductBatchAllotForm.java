package com.hq.chainMS.service.sellProduct.apiData;

import java.util.ArrayList;
import java.util.List;

public class SellProductBatchAllotForm {
	private List<SellProductAllotForm> productAllotForms = new ArrayList<SellProductAllotForm>();

	public static SellProductBatchAllotForm newInstance() {
		return new SellProductBatchAllotForm();
	}

	public List<SellProductAllotForm> getProductAllotForms() {
		return productAllotForms;
	}

	public void setProductAllotForms(List<SellProductAllotForm> productAllotForms) {
		this.productAllotForms = productAllotForms;
	}

}
