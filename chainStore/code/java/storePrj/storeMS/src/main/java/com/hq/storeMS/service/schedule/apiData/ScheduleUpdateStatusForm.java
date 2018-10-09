package com.hq.storeMS.service.schedule.apiData;

import com.hq.storeMS.service.schedule.data.Schedule;
import com.hq.storeMS.service.schedule.data.ScheduleStatusEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ScheduleUpdateStatusForm {

	private int statu;
	
	public static ScheduleUpdateStatusForm newInstance() {
		return new ScheduleUpdateStatusForm();
	}
	
	public void updateSchedule(Schedule schedule) {
		FastBeanCopyer.getInstance().copy(this, schedule);
	}

	public ScheduleStatusEnum getStatuEnum() {
		return ScheduleStatusEnum.valueOf(this.statu);
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}



	@Override
	public String toString() {
		return "ScheduleUpdateStatusForm [ statu="
				+ statu + "]";
	}



	


}
