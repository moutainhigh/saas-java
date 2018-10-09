package com.hq.storeMS.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class DecreasePrdCardUpdListForm {
	private List<DecreasePrdCardAddForm> decreasePrdCardAddForms = new ArrayList<DecreasePrdCardAddForm>();

	public static DecreasePrdCardUpdListForm newInstance() {
		return new DecreasePrdCardUpdListForm();
	}

	public List<DecreasePrdCardAddForm> getDecreasePrdCardAddForms() {
		return decreasePrdCardAddForms;
	}

	public void setDecreasePrdCardAddForms(
			List<DecreasePrdCardAddForm> decreasePrdCardAddForms) {
		this.decreasePrdCardAddForms = decreasePrdCardAddForms;
	}
}
