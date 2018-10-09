package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class DelimitCardRecordUpdListForm {
	private List<DelimitCardRecordAddForm> delimitCardUpdateForms = new ArrayList<DelimitCardRecordAddForm>();

	public static DelimitCardRecordUpdListForm newInstance() {
		return new DelimitCardRecordUpdListForm();
	}

	public List<DelimitCardRecordAddForm> getDelimitCardUpdateForms() {
		return delimitCardUpdateForms;
	}

	public void setDelimitCardUpdateForms(List<DelimitCardRecordAddForm> delimitCardUpdateForms) {
		this.delimitCardUpdateForms = delimitCardUpdateForms;
	}
}
