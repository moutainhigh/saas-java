package com.hq.storeMS.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class DecreasePrdCardAddListForm {
	private List<DecreasePrdCardAddForm> decreasePrdCardAddForms = new ArrayList<DecreasePrdCardAddForm>();

	public static DecreasePrdCardAddListForm newInstance() {
		return new DecreasePrdCardAddListForm();
	}

	public List<DecreasePrdCardAddForm> getDecreasePrdCardAddForms() {
		return decreasePrdCardAddForms;
	}

	public void setDecreasePrdCardAddForms(
			List<DecreasePrdCardAddForm> decreasePrdCardAddForms) {
		this.decreasePrdCardAddForms = decreasePrdCardAddForms;
	}
}
