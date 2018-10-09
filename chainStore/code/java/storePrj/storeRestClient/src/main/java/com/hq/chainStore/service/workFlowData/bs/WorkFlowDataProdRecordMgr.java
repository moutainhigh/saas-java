package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdInfoForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdStatusForm;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdateEnum;
import com.hq.chainStore.service.workFlowData.apiData.ProdRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataProdRecordMgr {

	public static WorkFlowDataProdRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataProdRecordMgr.class);
	}
	
	private final String module = "product";
	
	public WorkFlowData updateProdRecord(long workFlowDataId, String productId, ProdRecordUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, productId, inputForm, module);
	}
	
	public WorkFlowData updateProdRecordInfo(long workFlowDataId, String productId, ProdRecordUpdInfoForm data) {
		ProdRecordUpdateForm inputForm = ProdRecordUpdateForm.newInstance();
		inputForm.setProdRecordUpdInfoForm(data);
		inputForm.setUpdateType(ProdRecordUpdateEnum.updateProductInfo.ordinal());
		return updateProdRecord(workFlowDataId, productId, inputForm);
	}
	
	public WorkFlowData updateProdRecordStatus(long workFlowDataId, String productId, ProdRecordUpdStatusForm data) {
		ProdRecordUpdateForm inputForm = ProdRecordUpdateForm.newInstance();
		inputForm.setProdRecordUpdStatusForm(data);
		inputForm.setUpdateType(ProdRecordUpdateEnum.updateProductStatus.ordinal());
		return updateProdRecord(workFlowDataId, productId, inputForm);
	}
	
	public WorkFlowData deleteProdRecord(long workFlowDataId, String productId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, productId, module);
	}
	
	public WorkFlowData addProdRecord(long workFlowDataId, ProdRecordAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addProdRecordList(long workFlowDataId, ProdRecordAddListForm addListForm) {
		final String actionName = "addList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
	
	public WorkFlowData updProdRecordList(long workFlowDataId, ProdRecordUpdListForm updListForm) {
		final String actionName = "updList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, updListForm, module);
	}
	
}
