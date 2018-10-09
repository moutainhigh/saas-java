package com.hq.chainStore.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class BonusInfoAddListForm {
	private List<BonusInfoAddForm> bonusInfoAddForms = new ArrayList<BonusInfoAddForm>();

	public static BonusInfoAddListForm newInstance() {
		return new BonusInfoAddListForm();
	}

	public List<BonusInfoAddForm> getBonusInfoAddForms() {
		return bonusInfoAddForms;
	}

	public void setBonusInfoAddForms(List<BonusInfoAddForm> bonusInfoAddForms) {
		this.bonusInfoAddForms = bonusInfoAddForms;
	}
}
