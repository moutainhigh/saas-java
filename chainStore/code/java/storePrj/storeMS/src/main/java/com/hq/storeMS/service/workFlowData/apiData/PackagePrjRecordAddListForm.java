package com.hq.storeMS.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class PackagePrjRecordAddListForm {
	private List<PackagePrjRecordAddForm> packagePrjRecordAddForms = new ArrayList<PackagePrjRecordAddForm>();

	public static PackagePrjRecordAddListForm newInstance() {
		return new PackagePrjRecordAddListForm();
	}

	public List<PackagePrjRecordAddForm> getPackagePrjRecordAddForms() {
		return packagePrjRecordAddForms;
	}

	public void setPackagePrjRecordAddForms(List<PackagePrjRecordAddForm> packagePrjRecordAddForms) {
		this.packagePrjRecordAddForms = packagePrjRecordAddForms;
	}
}
