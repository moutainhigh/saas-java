package com.hq.storeMS.service.storeProductInfo.apiData;

import java.util.ArrayList;
import java.util.List;

public class ProductBatchPullForm {
	private List<ProductPullForm> pullForms = new ArrayList<ProductPullForm>();

	public static ProductBatchPullForm newInstance() {
		return new ProductBatchPullForm();
	}

	public List<ProductPullForm> getPullForms() {
		return pullForms;
	}

	public void setPullForms(List<ProductPullForm> pullForms) {
		this.pullForms = pullForms;
	}

}
