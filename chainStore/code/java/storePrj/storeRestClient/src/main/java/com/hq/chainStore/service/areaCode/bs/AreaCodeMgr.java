package com.hq.chainStore.service.areaCode.bs;

import java.util.List;

import com.hq.chainStore.service.areaCode.apiData.AreaCodeAddForm;
import com.hq.chainStore.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainStore.service.areaCode.data.AreaCode;
import com.hq.chainStore.service.areaCode.data.AreaCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeMgr {

	public static AreaCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeMgr.class);
	}
	
	public List<AreaCode> findByCondUnAuth(AreaCodeQueryForm queryForm) {
		final String findPath = "findByCond";
		return AreaCodeDAO.getInstance().findByCondUnAuth(findPath, queryForm);
	}
	
	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		final String findPath = "findByCond";
		return AreaCodeDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public AreaCode getAreaCode(long areaCodeId) {
		return AreaCodeDAO.getInstance().get(areaCodeId);
	}
	
	@Deprecated
	public AreaCode addAreaCode(AreaCodeAddForm addForm) {
		return AreaCodeDAO.getInstance().add(addForm);
	}
	
}
