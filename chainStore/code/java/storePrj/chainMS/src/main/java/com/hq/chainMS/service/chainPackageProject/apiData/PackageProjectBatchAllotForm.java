package com.hq.chainMS.service.chainPackageProject.apiData;

import java.util.ArrayList;
import java.util.List;

public class PackageProjectBatchAllotForm {
	private List<PackageProjectAllotForm> packageProjectAllotForms = new ArrayList<PackageProjectAllotForm>();

	public static PackageProjectBatchAllotForm newInstance() {
		return new PackageProjectBatchAllotForm();
	}

	public List<PackageProjectAllotForm> getPackageProjectAllotForms() {
		return packageProjectAllotForms;
	}

	public void setPackageProjectAllotForms(List<PackageProjectAllotForm> packageProjectAllotForms) {
		this.packageProjectAllotForms = packageProjectAllotForms;
	}
}
