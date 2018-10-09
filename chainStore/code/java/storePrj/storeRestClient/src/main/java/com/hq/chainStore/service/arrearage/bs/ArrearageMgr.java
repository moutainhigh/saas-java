package com.hq.chainStore.service.arrearage.bs;

import java.util.List;

import com.hq.chainStore.service.arrearage.apiData.ArrearageAddPaymentHistoryApiForm;
import com.hq.chainStore.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.chainStore.service.arrearage.apiData.ArrearageUpdateApiForm;
import com.hq.chainStore.service.arrearage.apiData.ArrearageUpdateType;
import com.hq.chainStore.service.arrearage.data.Arrearage;
import com.hq.chainStore.service.arrearage.data.ArrearageDAO;
import com.hq.chainStore.service.arrearage.data.ArrearageGroup;
import com.hq.chainStore.service.common.PageResp;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageMgr {

	public static ArrearageMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageMgr.class);
	}
	
	public PageResp<Arrearage> getArrearagePageInfo(ArrearageQueryForm queryForm) {
		final String findPath = "getArrearagePageInfo";
		return ArrearageDAO.getInstance().getArrearagePageInfo(findPath, queryForm);
	}
	
	public List<Arrearage> findArrearageList(ArrearageQueryForm queryForm) {
		final String findPath = "findArrearageList";
		return ArrearageDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public PageResp<ArrearageGroup> getArrearageGroupPageInfo(ArrearageQueryForm queryForm) {
		final String findPath = "getArrearageGroupPageInfo";
		return ArrearageDAO.getInstance().getArrearageGroupPageInfo(findPath, queryForm);
	}
	
	public List<ArrearageGroup> findArrearageGroupList(ArrearageQueryForm queryForm) {
		final String findPath = "findArrearageGroupList";
		return ArrearageDAO.getInstance().findArrearageGroupList(findPath, queryForm);
	}
	
	@Deprecated
	public Arrearage getArrearage(long arrearageId) {
		return ArrearageDAO.getInstance().get(arrearageId);
	}
	
	public Arrearage getArrearage(long storeId, long arrearageId) {
		String uriPath = StringUtils4Client.format("{}/{}", storeId, arrearageId);
		return ArrearageDAO.getInstance().findOne(uriPath);
	}
	
	public void updateArrearage(long arrearageId, ArrearageUpdateApiForm updateForm) {
		ArrearageDAO.getInstance().update(arrearageId, updateForm);
	}
	
	public void addPaymentHistory(long arrearageId, long storeId, ArrearageAddPaymentHistoryApiForm addPaymentHistoryApiForm){
		ArrearageUpdateApiForm updateForm = ArrearageUpdateApiForm.newInstance();
		updateForm.setUpdateTypeEnum(ArrearageUpdateType.AddPaymentHistory);
		updateForm.setStoreId(storeId);
		updateForm.setAddPaymentHistoryApiForm(addPaymentHistoryApiForm);
		updateArrearage(arrearageId, updateForm);
	}
}
