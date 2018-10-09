package com.hq.storeMS.service.storePackageProject.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PackageProjectBatchUpdateStateForm {
	private Set<String> idSet = new HashSet<String>();
	private int state;

	public static PackageProjectBatchUpdateStateForm newInstance() {
		return new PackageProjectBatchUpdateStateForm();
	}
	
	public List<PackageProjectUpdateStateForm> toPackageProjectUpdateStateFormList(){
		List<PackageProjectUpdateStateForm> list = new ArrayList<PackageProjectUpdateStateForm>();
		for (String id : idSet) {
			list.add(PackageProjectUpdateStateForm.newInstance(id, state));
		}
		return list;
	}

	public Set<String> getIdSet() {
		return idSet;
	}

	public void setIdSet(Set<String> idSet) {
		this.idSet = idSet;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
