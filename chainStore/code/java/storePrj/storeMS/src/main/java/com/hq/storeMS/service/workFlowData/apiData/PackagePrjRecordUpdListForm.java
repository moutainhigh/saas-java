package com.hq.storeMS.service.workFlowData.apiData;

import java.util.ArrayList;
import java.util.List;

public class PackagePrjRecordUpdListForm {
	private List<PackagePrjRecordAddForm> packagePrjRecordUpdForms = new ArrayList<PackagePrjRecordAddForm>();

	public static PackagePrjRecordUpdListForm newInstance() {
		return new PackagePrjRecordUpdListForm();
	}

	public List<PackagePrjRecordAddForm> getPackagePrjRecordUpdForms() {
		return packagePrjRecordUpdForms;
	}

	public void setPackagePrjRecordUpdForms(List<PackagePrjRecordAddForm> packagePrjRecordUpdForms) {
		this.packagePrjRecordUpdForms = packagePrjRecordUpdForms;
	}
}
