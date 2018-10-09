package com.hq.storeMS.service.schedule.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.leaguerAffair.data.AlarmClock;
import com.hq.storeMS.service.schedule.apiData.ScheduleQueryForm;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ScheduleDAO extends MongodbDao<Schedule> {
	
	public static ScheduleDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ScheduleDAO.class);
	}

	public void addSchedule(AlarmClock target) {
		
	}
	
	public List<Schedule> findScheduleList(ScheduleQueryForm queryForm){
		Criteria criteria = buildCriteria(queryForm);
		Query query = new Query(criteria);
	    return super.find(query);
	}

	private Criteria buildCriteria(ScheduleQueryForm queryForm) {
		Criteria criteria = new Criteria();  
		//店员查询
		if(queryForm.getStoreId() > 0L){
			criteria.and("storeId").is(queryForm.getStoreId());
		}
		
		//医美师查询
		if(queryForm.getBeauticianId() > 0L){
			criteria.and("beauticianId").is(queryForm.getBeauticianId());
		}
		//会员查询
		if(StringUtils.isNoneBlank(queryForm.getLeaguerId())){
			criteria.and("leaguerId").is(queryForm.getLeaguerId());
		}
		
		//状态查找
		if(queryForm.getStatu() == ScheduleStatusEnum.PENDING.ordinal()){//查询 新建和未处理的待办事项
			criteria.and("statu").lt(ScheduleStatusEnum.FINISH.ordinal());
		}else if(queryForm.getStatu() == ScheduleStatusEnum.FINISH.ordinal()){
			criteria.and("statu").is(queryForm.getStatu());
		}
		return criteria;
	}
	
	
	public long count(MsgQueryForm queryForm) {
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(queryForm.getStoreId());
		criteria.and("beauticianId").is(queryForm.getBuserId());
		
		long now = System.currentTimeMillis();
		criteria.orOperator(Criteria.where("statu").is(ScheduleStatusEnum.PENDING.ordinal()),
			Criteria.where("statu").is(ScheduleStatusEnum.NEW.ordinal()).and("noticeTime").lte(now)
		);
	    Query query = new Query(criteria);
		return count(query);
	}
}
