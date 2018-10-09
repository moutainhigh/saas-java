package com.hq.storeMS.service.storePackageProject.apiData;

import java.util.ArrayList;
import java.util.List;

public class PackageBatchPullForm {
	private List<PackagePullForm> pullForms = new ArrayList<PackagePullForm>();

	public static PackageBatchPullForm newInstance() {
		return new PackageBatchPullForm();
	}

	public List<PackagePullForm> getPullForms() {
		return pullForms;
	}

	public void setPullForms(List<PackagePullForm> pullForms) {
		this.pullForms = pullForms;
	}

}
