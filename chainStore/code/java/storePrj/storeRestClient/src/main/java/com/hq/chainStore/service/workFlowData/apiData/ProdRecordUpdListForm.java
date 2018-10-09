package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class ProdRecordUpdListForm {
	private List<ProdRecordAddForm> prodRecordAddForms = new ArrayList<ProdRecordAddForm>();

	public static ProdRecordUpdListForm newInstance() {
		return new ProdRecordUpdListForm();
	}

	public List<ProdRecordAddForm> getProdRecordAddForms() {
		return prodRecordAddForms;
	}

	public void setProdRecordAddForms(List<ProdRecordAddForm> prodRecordAddForms) {
		this.prodRecordAddForms = prodRecordAddForms;
	}
}
