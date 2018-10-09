package com.hq.chainMS.service.chainPackageProject.bs.updateHandle;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateForm;

public interface IPackageProjectHandle {
	public OperateTips update(long chainId, ChainPackageProjectUpdateForm updateForm);
}
