package com.hq.chainClient.service.areaCode.bs;

import java.util.List;

import com.hq.chainClient.service.areaCode.apiData.AreaCodeAddForm;
import com.hq.chainClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainClient.service.areaCode.data.AreaCode;
import com.hq.chainClient.service.areaCode.data.AreaCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeClientMgr {

	public static AreaCodeClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeClientMgr.class);
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
