package com.hq.chainStore.service.activateCode.bs;

import java.util.List;

import com.hq.chainStore.service.activateCode.apiData.ActivateCodeGenApiForm;
import com.hq.chainStore.service.activateCode.apiData.AddActivateCodeForm;
import com.hq.chainStore.service.activateCode.apiData.QueryActivateCodeForm;
import com.hq.chainStore.service.activateCode.data.ActivateCode;
import com.hq.chainStore.service.activateCode.data.ActivateCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestResp;

public class ActivateCodeMgr {

	public static ActivateCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ActivateCodeMgr.class);
	}
	
	public List<ActivateCode> findByCond(QueryActivateCodeForm queryForm) {
		final String findPath = "findByCond";
		return ActivateCodeDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public ActivateCode getActivateCode(long activateCodeId) {
		return ActivateCodeDAO.getInstance().get(activateCodeId);
	}
	
	public ActivateCode addActivateCode(AddActivateCodeForm addActivateCodeForm) {
		return ActivateCodeDAO.getInstance().add(addActivateCodeForm);
	}
	
	public RestResp genActivateCodes(ActivateCodeGenApiForm inputForm) {
		return ActivateCodeDAO.getInstance().genActivateCodes(inputForm);
	}
	
}
