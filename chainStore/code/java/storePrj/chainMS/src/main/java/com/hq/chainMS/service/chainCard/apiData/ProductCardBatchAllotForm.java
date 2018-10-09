package com.hq.chainMS.service.chainCard.apiData;

import java.util.ArrayList;
import java.util.List;

public class ProductCardBatchAllotForm {
	private List<ProductCardAllotForm> productCardAllotForms = new ArrayList<ProductCardAllotForm>();

	public static ProductCardBatchAllotForm newInstance() {
		return new ProductCardBatchAllotForm();
	}

	public List<ProductCardAllotForm> getProductCardAllotForms() {
		return productCardAllotForms;
	}

	public void setProductCardAllotForms(List<ProductCardAllotForm> productCardAllotForms) {
		this.productCardAllotForms = productCardAllotForms;
	}
}
