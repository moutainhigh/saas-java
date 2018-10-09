package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class PrdCardUpdListForm {
	private List<PrdCardAddForm> prdCardAddForms = new ArrayList<PrdCardAddForm>();

	public static PrdCardUpdListForm newInstance() {
		return new PrdCardUpdListForm();
	}

	public List<PrdCardAddForm> getPrdCardAddForms() {
		return prdCardAddForms;
	}

	public void setPrdCardAddForms(List<PrdCardAddForm> prdCardAddForms) {
		this.prdCardAddForms = prdCardAddForms;
	}
}
