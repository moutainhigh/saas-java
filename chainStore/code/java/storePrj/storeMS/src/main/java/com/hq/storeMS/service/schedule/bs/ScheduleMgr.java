package com.hq.storeMS.service.schedule.bs;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.schedule.apiData.ScheduleQueryForm;
import com.hq.storeMS.service.schedule.data.Schedule;
import com.hq.storeMS.service.schedule.data.ScheduleStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ScheduleMgr {

	public static ScheduleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ScheduleMgr.class);
	}

	public void addAndReturnId(Schedule target) {
		ScheduleDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void update(Schedule target) {
		ScheduleDataHolder.getInstance().updpate(target);
	}
	
	public void delete(long id) {
		ScheduleDataHolder.getInstance().delete(id);
	}
	
	public Schedule getSimple(long id) {
		return ScheduleDataHolder.getInstance().get(id);
	}
	
	public Schedule get(long id) {
		Schedule schedule =ScheduleDataHolder.getInstance().get(id);
		updateScheduleStatus(schedule);
		return schedule;
	}
	

	public List<Schedule> findScheduleList(ScheduleQueryForm queryForm) {
		List<Schedule> scheduleList = ScheduleDataHolder.getInstance().findScheduleList(queryForm);
		List<Schedule> scheduleROList = new ArrayList<Schedule>();
		for (Schedule schedule : scheduleList) {
			updateScheduleStatus(schedule);
			if(ScheduleStatusEnum.NEW.ordinal() != schedule.getStatu()){//排除新建状态的待办事项
				scheduleROList.add(schedule);
			}
		}
		return scheduleROList;
	}
	
	
	private void updateScheduleStatus(Schedule schedule) {
		ScheduleStatusEnum statusEnum = ScheduleStatusEnum.valueOf(schedule.getStatu());
		switch (statusEnum) {
		case NEW:
			updateUnDealStatu(schedule);
			break;
		case PENDING:
			break;
		case FINISH:
			break;
		default:
			break;
		}
	}

	
	/**
	 * 
	 * updateUnDealStatu:(状态为PENDING 标识为未处理). <br/>   
	 *  
	 * @author helen
	 * @param schedule
	 * @return  
	 * @since JDK 1.7
	 */
	private boolean updateUnDealStatu(Schedule schedule) {
		boolean b = false;
		long noticeTime = schedule.getNoticeTime();//提醒时间
		long nowTime = System.currentTimeMillis();
		//超过提醒时间设为未处理
		if (nowTime >noticeTime) {
			schedule.setStatu(ScheduleStatusEnum.PENDING.ordinal());
			ScheduleDataHolder.getInstance().updpate(schedule);
			b = true;
		}
		return b;
	}
	
}
