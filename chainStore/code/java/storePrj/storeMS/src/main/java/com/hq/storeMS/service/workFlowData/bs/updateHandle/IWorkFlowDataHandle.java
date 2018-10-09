package com.hq.storeMS.service.workFlowData.bs.updateHandle;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateForm;

public interface IWorkFlowDataHandle {
	public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm);
}
