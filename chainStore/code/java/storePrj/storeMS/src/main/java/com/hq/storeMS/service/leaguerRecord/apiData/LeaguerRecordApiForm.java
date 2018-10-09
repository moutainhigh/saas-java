package com.hq.storeMS.service.leaguerRecord.apiData;

public class LeaguerRecordApiForm {
	// 对应 LeaguerRecordUpdateType
	private int updateType;
	private long storeId;
	
	private LeaguerRecordUpdateForm leaguerRecordUpdateForm;

	public static LeaguerRecordApiForm newInstance() {
		return new LeaguerRecordApiForm();
	}
	
	public LeaguerRecordUpdateType getLeaguerRecordUpdateType() {
		return LeaguerRecordUpdateType.valueOf(updateType);
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public LeaguerRecordUpdateForm getLeaguerRecordUpdateForm() {
		return leaguerRecordUpdateForm;
	}

	public void setLeaguerRecordUpdateForm(LeaguerRecordUpdateForm leaguerRecordUpdateForm) {
		this.leaguerRecordUpdateForm = leaguerRecordUpdateForm;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
}
