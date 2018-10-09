package com.hq.storeMS.service.storePackageProject.bs.update;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateForm;

public interface IPackageProjectHandler {
	public OperateTips update(long storeId, StorePackageProjectUpdateForm updateForm);
}
