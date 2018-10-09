package com.hq.chainStore.service.workFlowData.bs;

import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordUpdListForm;
import com.hq.chainStore.service.workFlowData.apiData.GoodsRecordUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataGoodsRecordMgr {

	public static WorkFlowDataGoodsRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataGoodsRecordMgr.class);
	}
	
	private final String module = "goods";
	
	public WorkFlowData updateGoodsRecord(long workFlowDataId, String goodsId, GoodsRecordUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, goodsId, inputForm, module);
	}
	
	public WorkFlowData deleteGoodsRecord(long workFlowDataId, String goodsId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, goodsId, module);
	}
	
	public WorkFlowData addGoodsRecord(long workFlowDataId, GoodsRecordAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addGoodsRecordList(long workFlowDataId, GoodsRecordAddListForm addListForm) {
		final String actionName = "addList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
	
	public WorkFlowData updGoodsRecordList(long workFlowDataId, GoodsRecordUpdListForm updListForm) {
		final String actionName = "updList";
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, updListForm, module);
	}
	
}
