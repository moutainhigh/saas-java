package com.hq.chainMS.service.chainProduct.apiData;

import java.util.ArrayList;
import java.util.List;

public class ProductBatchAllotForm {
	private List<ProductAllotForm> productAllotForms = new ArrayList<ProductAllotForm>();

	public static ProductBatchAllotForm newInstance() {
		return new ProductBatchAllotForm();
	}

	public List<ProductAllotForm> getProductAllotForms() {
		return productAllotForms;
	}

	public void setProductAllotForms(List<ProductAllotForm> productAllotForms) {
		this.productAllotForms = productAllotForms;
	}
}
