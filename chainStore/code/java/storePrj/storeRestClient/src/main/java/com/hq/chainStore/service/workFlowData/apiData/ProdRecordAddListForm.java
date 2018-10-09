package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class ProdRecordAddListForm {
	private List<ProdRecordAddForm> prodRecordAddForms = new ArrayList<ProdRecordAddForm>();

	public static ProdRecordAddListForm newInstance() {
		return new ProdRecordAddListForm();
	}

	public List<ProdRecordAddForm> getProdRecordAddForms() {
		return prodRecordAddForms;
	}

	public void setProdRecordAddForms(List<ProdRecordAddForm> prodRecordAddForms) {
		this.prodRecordAddForms = prodRecordAddForms;
	}
}
