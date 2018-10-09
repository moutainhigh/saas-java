package com.hq.storeClient.service.storePackageProject.apiData;

import java.util.ArrayList;
import java.util.List;

public class PackageBatchCancelForm {
	private List<PackageCancelForm> cancelForms = new ArrayList<PackageCancelForm>();

	public static PackageBatchCancelForm newInstance() {
		return new PackageBatchCancelForm();
	}

	public List<PackageCancelForm> getCancelForms() {
		return cancelForms;
	}

	public void setCancelForms(List<PackageCancelForm> cancelForms) {
		this.cancelForms = cancelForms;
	}
}
