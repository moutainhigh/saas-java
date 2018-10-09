package com.hq.storeMS.service.spreadStatis.apiData;

import com.hq.storeMS.common.util.AppUtils;

public class SpreadStatisQueryForm {
	private long storeId;
	private long minTime;
	private long maxTime;

	private long buserId;
	private long dynamicId;

	private int pageItemCount;
	private int pageNo;

	public static SpreadStatisQueryForm newInstance() {
		SpreadStatisQueryForm data = new SpreadStatisQueryForm();
		return data;
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
	}

	public long getStoreId() {
		return storeId;
	}

	public SpreadStatisQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public SpreadStatisQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public SpreadStatisQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public SpreadStatisQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public long getDynamicId() {
		return dynamicId;
	}

	public SpreadStatisQueryForm setDynamicId(long dynamicId) {
		this.dynamicId = dynamicId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public SpreadStatisQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public SpreadStatisQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}
}
