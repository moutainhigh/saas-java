package com.hq.storeMS.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class DelimitCardRecordAddListForm {
	private List<DelimitCardRecordAddForm> delimitCardAddForms = new ArrayList<DelimitCardRecordAddForm>();

	public static DelimitCardRecordAddListForm newInstance() {
		return new DelimitCardRecordAddListForm();
	}

	public List<DelimitCardRecordAddForm> getDelimitCardAddForms() {
		return delimitCardAddForms;
	}

	public void setDelimitCardAddForms(List<DelimitCardRecordAddForm> delimitCardAddForms) {
		this.delimitCardAddForms = delimitCardAddForms;
	}
}
