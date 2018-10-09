package com.hq.storeClient.service.storeProductInfo.apiData;

import java.util.ArrayList;
import java.util.List;

public class ProductBatchCancelForm {
	private List<ProductCancelForm> cancelForms = new ArrayList<ProductCancelForm>();

	public static ProductBatchCancelForm newInstance() {
		return new ProductBatchCancelForm();
	}

	public List<ProductCancelForm> getCancelForms() {
		return cancelForms;
	}

	public void setCancelForms(List<ProductCancelForm> cancelForms) {
		this.cancelForms = cancelForms;
	}
}
