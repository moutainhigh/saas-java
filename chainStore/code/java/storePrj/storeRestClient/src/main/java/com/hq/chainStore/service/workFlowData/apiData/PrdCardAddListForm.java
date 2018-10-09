package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class PrdCardAddListForm {
	private List<PrdCardAddForm> prdCardAddForms = new ArrayList<PrdCardAddForm>();

	public static PrdCardAddListForm newInstance() {
		return new PrdCardAddListForm();
	}

	public List<PrdCardAddForm> getPrdCardAddForms() {
		return prdCardAddForms;
	}

	public void setPrdCardAddForms(List<PrdCardAddForm> prdCardAddForms) {
		this.prdCardAddForms = prdCardAddForms;
	}
}
