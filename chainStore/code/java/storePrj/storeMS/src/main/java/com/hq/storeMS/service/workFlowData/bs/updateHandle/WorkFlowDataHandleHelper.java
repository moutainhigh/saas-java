package com.hq.storeMS.service.workFlowData.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateEnum;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataUpdateForm;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataHandleHelper {

	public static WorkFlowDataHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataHandleHelper.class);
	}

	private Map<WorkFlowDataUpdateEnum, IWorkFlowDataHandle> handleMapper = new HashMap<WorkFlowDataUpdateEnum, IWorkFlowDataHandle>();

	public WorkFlowDataHandleHelper() {
		handleMapper.put(WorkFlowDataUpdateEnum.cancelWorkFlowData, new IWorkFlowDataHandle(){
			@Override
			public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm){
				return WorkFlowDataUpdateHandle.getInstance().cancelWorkFlowData(workFlowDataId, inputForm.getWorkFlowDataCancelForm());
			}
		});
		handleMapper.put(WorkFlowDataUpdateEnum.updateWorkFlowDataBuyItems, new IWorkFlowDataHandle(){
			@Override
			public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm){
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(WorkFlowDataUpdateEnum.updateWorkFlowDataInfo, new IWorkFlowDataHandle(){
			@Override
			public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm){
				return WorkFlowDataUpdateHandle.getInstance().updateWorkFlowDataInfo(workFlowDataId, inputForm.getUpdWorkFlowDataInfoForm());
			}
		});
		handleMapper.put(WorkFlowDataUpdateEnum.updateWorkFlowDataStatus, new IWorkFlowDataHandle(){
			@Override
			public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm){
				return WorkFlowDataUpdateHandle.getInstance().updateWorkFlowDataStatus(workFlowDataId, inputForm.getWorkFlowDataStatusForm());
			}
		});
		handleMapper.put(WorkFlowDataUpdateEnum.workFlowDataDeleteForm, new IWorkFlowDataHandle(){
			@Override
			public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm){
				return WorkFlowDataUpdateHandle.getInstance().deleteWorkFlowData(workFlowDataId, inputForm.getWorkFlowDataDeleteForm());
			}
		});
	}

	public OperateTips update(long workFlowDataId, WorkFlowDataUpdateForm inputForm) {
		WorkFlowDataUpdateEnum updateType = WorkFlowDataUpdateEnum.valueOf(inputForm.getUpdateType());
		IWorkFlowDataHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(workFlowDataId, inputForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
