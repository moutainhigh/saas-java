package com.hq.storeMS.service.daySnapshot.apiData;

import com.hq.storeMS.common.util.AppUtils;

public class DaySnapshotQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 账号名称
	private String name;

	private int pageItemCount;
	private int pageNo;

	public static DaySnapshotQueryForm newInstance() {
		return new DaySnapshotQueryForm();
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
	}

	public long getMinTime() {
		return minTime;
	}

	public DaySnapshotQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public DaySnapshotQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public DaySnapshotQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public String getName() {
		return name;
	}

	public DaySnapshotQueryForm setName(String name) {
		this.name = name;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public DaySnapshotQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public DaySnapshotQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
