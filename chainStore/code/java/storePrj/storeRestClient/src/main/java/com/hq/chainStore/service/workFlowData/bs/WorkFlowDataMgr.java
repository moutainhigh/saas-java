package com.hq.chainStore.service.workFlowData.bs;

import java.util.List;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddByAppoint;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddByLeaguer;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataAddForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataCancelForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataDeleteForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataSwitchLeaguer;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateBuyItemsForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateEnum;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateInfoForm;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataUpdateStatusForm;
import com.hq.chainStore.service.workFlowData.apiData.save.WorkFlowDataSaveForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class WorkFlowDataMgr {

	public static WorkFlowDataMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataMgr.class);
	}
	
	public PageResp<WorkFlowData> findWorkFlowDataPageInfo(WorkFlowDataQueryForm queryForm) {
		final String findPath = "findWorkFlowDataPageInfo";
		return WorkFlowDataDAO.getInstance().findWorkFlowDataPageInfo(findPath, queryForm);
	}
	
	public List<WorkFlowData> findByCond(WorkFlowDataQueryForm queryForm) {
		final String findPath = "findByCond";
		return WorkFlowDataDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public WorkFlowData getWorkFlowData(long workFlowDataId) {
		return WorkFlowDataDAO.getInstance().get(workFlowDataId);
	}
	
	public void deleteWorkFlowData(long workFlowDataId) {
		WorkFlowDataDAO.getInstance().delete(workFlowDataId);
	}
	
	public WorkFlowData updateWorkFlowData(long workFlowDataId, WorkFlowDataUpdateForm updateForm) {
		return WorkFlowDataDAO.getInstance().updateWithResp(workFlowDataId, updateForm);
	}
	
	public WorkFlowData updWorkFlowDataInfo(long workFlowDataId, WorkFlowDataUpdateInfoForm updateInfoForm) {
		WorkFlowDataUpdateForm updateForm = WorkFlowDataUpdateForm.newInstance();
		updateForm.setUpdateType(WorkFlowDataUpdateEnum.updateWorkFlowDataInfo.ordinal());
		updateForm.setUpdWorkFlowDataInfoForm(updateInfoForm);
		return updateWorkFlowData(workFlowDataId, updateForm);
	}
	
	public WorkFlowData updWorkFlowDataStatus(long workFlowDataId, WorkFlowDataUpdateStatusForm updateStatusForm) {
		WorkFlowDataUpdateForm updateForm = WorkFlowDataUpdateForm.newInstance();
		updateForm.setUpdateType(WorkFlowDataUpdateEnum.updateWorkFlowDataStatus.ordinal());
		updateForm.setWorkFlowDataStatusForm(updateStatusForm);
		return updateWorkFlowData(workFlowDataId, updateForm);
	}
	
	public WorkFlowData updateWorkFlowDataBuyItems(long workFlowDataId, WorkFlowDataUpdateBuyItemsForm updateBuyItemsForm) {
		WorkFlowDataUpdateForm updateForm = WorkFlowDataUpdateForm.newInstance();
		updateForm.setUpdateType(WorkFlowDataUpdateEnum.updateWorkFlowDataBuyItems.ordinal());
		updateForm.setWorkFlowDataBuyItemsForm(updateBuyItemsForm);
		return updateWorkFlowData(workFlowDataId, updateForm);
	}
	
	public WorkFlowData deleteWorkFlowData(long workFlowDataId, WorkFlowDataDeleteForm workFlowDataDeleteForm) {
		WorkFlowDataUpdateForm updateForm = WorkFlowDataUpdateForm.newInstance();
		updateForm.setUpdateType(WorkFlowDataUpdateEnum.workFlowDataDeleteForm.ordinal());
		updateForm.setWorkFlowDataDeleteForm(workFlowDataDeleteForm);
		return updateWorkFlowData(workFlowDataId, updateForm);
	}
	
	public WorkFlowData cancelWorkFlowData(long workFlowDataId, WorkFlowDataCancelForm workFlowDataCancelForm) {
		WorkFlowDataUpdateForm updateForm = WorkFlowDataUpdateForm.newInstance();
		updateForm.setUpdateType(WorkFlowDataUpdateEnum.cancelWorkFlowData.ordinal());
		updateForm.setWorkFlowDataCancelForm(workFlowDataCancelForm);
		return updateWorkFlowData(workFlowDataId, updateForm);
	}
	
	public WorkFlowData addWorkFlowData(WorkFlowDataAddForm addForm) {
		return WorkFlowDataDAO.getInstance().add(addForm);
	}
	
	public WorkFlowData addByAppoint(WorkFlowDataAddByAppoint inputForm) {
		final String actionName = "addByAppoint";
		RestResp resp = WorkFlowDataDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), WorkFlowData.class);
	}
	
	public WorkFlowData addByLeaguer(WorkFlowDataAddByLeaguer inputForm) {
		final String actionName = "addByLeaguer";
		RestResp resp = WorkFlowDataDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), WorkFlowData.class);
	}
	
	public WorkFlowData switchLeaguer(WorkFlowDataSwitchLeaguer inputForm) {
		final String actionName = "switchLeaguer";
		RestResp resp = WorkFlowDataDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), WorkFlowData.class);
	}
	
	public WorkFlowData saveOrUpdate(WorkFlowDataSaveForm inputForm) {
		final String actionName = "saveOrUpdate";
		RestResp resp = WorkFlowDataDAO.getInstance().rawReq(actionName, inputForm);
		return JsonUtil.getInstance().fromJson(resp.gettJson(), WorkFlowData.class);
	}
	
}
