package com.hq.chainStore.service.leaguerRecord.bs;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordAddForm;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordApiForm;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordUpdateForm;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordUpdateType;
import com.hq.chainStore.service.leaguerRecord.data.LeaguerRecord;
import com.hq.chainStore.service.leaguerRecord.data.LeaguerRecordDAO;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerRecordMgr {

	public static LeaguerRecordMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerRecordMgr.class);
	}
	
	public PageResp<LeaguerRecord> getLeaguerRecordPageInfo(LeaguerRecordQueryForm queryForm) {
		final String findPath = "getLeaguerRecordPageInfo";
		return LeaguerRecordDAO.getInstance().getLeaguerRecordPageInfo(findPath, queryForm);
	}

	@Deprecated
	public LeaguerRecord getLeaguerRecord(long leaguerRecordId) {
		return LeaguerRecordDAO.getInstance().get(leaguerRecordId);
	}

	@Deprecated
	public void updateLeaguerRecord(long leaguerRecordId, LeaguerRecordUpdateForm inputForm) {
		LeaguerRecordDAO.getInstance().update(leaguerRecordId, inputForm);
	}

	@Deprecated
	public void deleteLeaguerRecord(long leaguerRecordId) {
		LeaguerRecordDAO.getInstance().delete(leaguerRecordId);
	}

	public LeaguerRecord addLeaguerRecord(LeaguerRecordAddForm inputForm) {
		return LeaguerRecordDAO.getInstance().add(inputForm);
	}
	
	public LeaguerRecord getLeaguerRecord(long storeId, long leaguerRecordId) {
		String uriPath=StringUtils4Client.format("{}/{}", storeId, leaguerRecordId);
		return LeaguerRecordDAO.getInstance().findOne(uriPath);
	}
	
	public void updateInfo(long storeId, long leaguerRecordId, LeaguerRecordUpdateForm inputForm) {
		LeaguerRecordApiForm updateForm = LeaguerRecordApiForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setLeaguerRecordUpdateForm(inputForm);
		updateForm.setUpdateType(LeaguerRecordUpdateType.UpdateInfo.ordinal());
		update(leaguerRecordId, updateForm);
	}

	private void update(long leaguerRecordId, LeaguerRecordApiForm updateForm) {
		String id=StringUtils4Client.format("updateLeaguerRecord/{}", leaguerRecordId);
		LeaguerRecordDAO.getInstance().update(id, updateForm);
	}

	public void deleteLeaguerRecord(long storeId, long leaguerRecordId) {
		String id=StringUtils4Client.format("{}/{}", storeId, leaguerRecordId);
		LeaguerRecordDAO.getInstance().delete(id);
	}
}
