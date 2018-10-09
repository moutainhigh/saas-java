package com.hq.chainStore.service.bonus.bs;

import java.util.List;

import com.hq.chainStore.service.bonus.apiData.BonusRecordForm;
import com.hq.chainStore.service.bonus.apiData.BonusRecordQueryForm;
import com.hq.chainStore.service.bonus.data.BonusRecord;
import com.hq.chainStore.service.bonus.data.BonusRecordDAO;
import com.hq.chainStore.service.bonus.data.BonusRecordGroup;
import com.hq.chainStore.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusRecordMgr {

	public static BonusRecordMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordMgr.class);
	}
	
	public void saveBonusRecord(BonusRecordForm inputForm) {
		BonusRecordDAO.getInstance().add(inputForm);
	}

	public void deleteBonusRecord(long bonusRecordId) {
		BonusRecordDAO.getInstance().delete(bonusRecordId);
	}
	
	public BonusRecord getBonusRecord(long bonusRecordId) {
		return BonusRecordDAO.getInstance().get(bonusRecordId);
	}

	public List<BonusRecord> findBonusRecordList(BonusRecordQueryForm queryForm) {
		final String findPath = "findBonusRecordList";
		return BonusRecordDAO.getInstance().findWithReqParam(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public PageResp<BonusRecord> findBonusRecordPageInfo(BonusRecordQueryForm queryForm) {
		final String findPath = "findBonusRecordPageInfo";
		return BonusRecordDAO.getInstance().findBonusRecordPageInfo(findPath, queryForm);
	}
	
	public PageResp<BonusRecordGroup> findBonusRecordGroupPageInfo(BonusRecordQueryForm queryForm) {
		final String findPath = "findBonusRecordGroupPageInfo";
		return BonusRecordDAO.getInstance().findBonusRecordGroupPageInfo(findPath, queryForm);
	}
}
