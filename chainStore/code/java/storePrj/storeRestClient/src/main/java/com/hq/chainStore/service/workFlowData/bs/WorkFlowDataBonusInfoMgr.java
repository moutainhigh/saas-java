package com.hq.chainStore.service.workFlowData.bs;

import java.util.Collection;
import java.util.List;

import com.hq.chainStore.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.chainStore.service.workFlowData.apiData.BonusInfoAddListForm;
import com.hq.chainStore.service.workFlowData.apiData.BonusInfoUpdateForm;
import com.hq.chainStore.service.workFlowData.data.WorkFlowData;
import com.hq.chainStore.service.workFlowData.data.WorkFlowDataDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataBonusInfoMgr {

	public static WorkFlowDataBonusInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataBonusInfoMgr.class);
	}
	
	private final String module = "bonusInfo";
	
	public WorkFlowData updateBonusInfo(long workFlowDataId, String bonusId, BonusInfoUpdateForm inputForm) {
		return WorkFlowDataDAO.getInstance().updateSubDomains(workFlowDataId, bonusId, inputForm, module);
	}
	
	public WorkFlowData deleteBonusInfo(long workFlowDataId, String bonusId) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, bonusId, module);
	}
	
	public WorkFlowData deleteBonusInfo(long workFlowDataId, Collection<String> bonusIds) {
		return WorkFlowDataDAO.getInstance().deleteSubDomains(workFlowDataId, StringUtils4Client.join(bonusIds, ","), module);
	}
	
	public WorkFlowData addBonusInfo(long workFlowDataId, BonusInfoAddForm inputForm) {
		return WorkFlowDataDAO.getInstance().addSubDomains(workFlowDataId, inputForm, module);
	}
	
	public WorkFlowData addBonusInfoList(long workFlowDataId, List<BonusInfoAddForm> inputForms) {
		final String actionName = "addList";
		BonusInfoAddListForm addListForm = BonusInfoAddListForm.newInstance();
		addListForm.setBonusInfoAddForms(inputForms);
		return WorkFlowDataDAO.getInstance().rawReqSubDomains(actionName, workFlowDataId, addListForm, module);
	}
}
