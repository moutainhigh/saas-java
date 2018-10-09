package com.hq.chainStore.service.workFlowType.bs;

import java.util.List;

import com.hq.chainStore.service.workFlowType.apiData.AddWorkFlowTypeForm;
import com.hq.chainStore.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.chainStore.service.workFlowType.apiData.UpdWorkFlowTypeForm;
import com.hq.chainStore.service.workFlowType.apiData.UpdWorkFlowTypeInfoForm;
import com.hq.chainStore.service.workFlowType.apiData.UpdateWorkFlowTypeEnum;
import com.hq.chainStore.service.workFlowType.data.WorkFlowType;
import com.hq.chainStore.service.workFlowType.data.WorkFlowTypeDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class WorkFlowTypeMgr {

	public static WorkFlowTypeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowTypeMgr.class);
	}
	
	public List<WorkFlowType> findByCond(QueryWorkFlowTypeForm queryForm) {
		final String findPath = "findByCond";
		ReqMap reqMap = ReqMap.newInstance();
		return WorkFlowTypeDAO.getInstance().findWithReqParam(findPath, reqMap, queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public WorkFlowType findByName(String typeName) {
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("typeName", typeName);
		return WorkFlowTypeDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public WorkFlowType getWorkFlowType(long workFlowTypeId) {
		return WorkFlowTypeDAO.getInstance().get(workFlowTypeId);
	}
	
	public void updateWorkFlowType(long workFlowTypeId, UpdWorkFlowTypeForm updWorkFlowTypeForm) {
		WorkFlowTypeDAO.getInstance().update(workFlowTypeId, updWorkFlowTypeForm);
	}
	
	public void updWorkFlowTypeInfo(long workFlowTypeId, UpdWorkFlowTypeInfoForm updWorkFlowTypeInfoForm) {
		UpdWorkFlowTypeForm updWorkFlowTypeForm = UpdWorkFlowTypeForm.newInstance();
		updWorkFlowTypeForm.setUpdateType(UpdateWorkFlowTypeEnum.UpdateWorkFlowTypeInfo.ordinal());
		updWorkFlowTypeForm.setUpdWorkFlowTypeInfoForm(updWorkFlowTypeInfoForm);
		WorkFlowTypeDAO.getInstance().update(workFlowTypeId, updWorkFlowTypeForm);
	}
	
	public WorkFlowType addWorkFlowType(AddWorkFlowTypeForm addWorkFlowTypeForm) {
		return WorkFlowTypeDAO.getInstance().add(addWorkFlowTypeForm);
	}
	
}
