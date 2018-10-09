package com.hq.chainStore.service.schedule.apidata;

public class ScheduleUpdateStatusForm {

	private int statu;

	public static ScheduleUpdateStatusForm newInstance() {
		return new ScheduleUpdateStatusForm();
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	@Override
	public String toString() {
		return "ScheduleUpdateStatusForm [ statu=" + statu + "]";
	}

}
