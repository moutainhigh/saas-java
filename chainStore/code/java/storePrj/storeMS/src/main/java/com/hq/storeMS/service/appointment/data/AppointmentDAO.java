package com.hq.storeMS.service.appointment.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.appointment.apiData.AppointmentQueryForm;
import com.hq.storeMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class AppointmentDAO extends MongodbMTDao<Appointment> {

	public static AppointmentDAO getInstance() {
		return HotSwap.getInstance().getSingleton(AppointmentDAO.class);
	}
	
	public List<Appointment> findAppointmentList(long bossId, AppointmentQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    List<Appointment> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<Appointment> filter(List<Appointment> list){
		List<Appointment> result = new ArrayList<Appointment>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Appointment data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private Query buildQuery(AppointmentQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(queryForm.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("appointTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("appointTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}
}
