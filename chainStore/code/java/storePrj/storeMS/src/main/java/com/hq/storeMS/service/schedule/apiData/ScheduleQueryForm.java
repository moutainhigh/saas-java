package com.hq.storeMS.service.schedule.apiData;

import com.zenmind.common.StringFormatUtil;

public class ScheduleQueryForm {

	private long storeId;
	private long beauticianId;
	private String leaguerId;
	private int statu;// 状态

	private int pageItemCount;
	private int pageNo;

	public static ScheduleQueryForm newInstance() {
		ScheduleQueryForm params = new ScheduleQueryForm();
		params.storeId = 0L;
		params.beauticianId = 0L;
		params.leaguerId = "";
		params.statu = 0;

		params.pageItemCount = 0;
		params.pageNo = 1;
		return params;
	}

	public String getListId() {
		String format = "{}_{}_{}_{}_{}_{}";
		return StringFormatUtil.format(format, storeId, beauticianId,
				leaguerId, statu, pageItemCount, pageNo);
	}

	public long getStoreId() {
		return storeId;
	}

	public ScheduleQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public ScheduleQueryForm setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
		return this;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public ScheduleQueryForm setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
		return this;
	}

	public int getStatu() {
		return statu;
	}

	public ScheduleQueryForm setStatu(int statu) {
		this.statu = statu;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ScheduleQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ScheduleQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

}
