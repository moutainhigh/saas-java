package com.hq.storeManagerMS.service.sms.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeManagerMS.service.sms.apiData.QuerySmsForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class SmsMongoDAO extends MongodbDao<Sms> {
	public static SmsMongoDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SmsMongoDAO.class);
	}

	public List<Sms> findSmsList(QuerySmsForm querySmsForm) {
		Criteria criteria = buildCriteria(querySmsForm);
	    Query query = new Query(criteria);
	    return super.find(query);
	}

	private Criteria buildCriteria(QuerySmsForm querySmsForm) {
		Criteria criteria = new Criteria();  
		if(querySmsForm.getPhone() > 0L){
			criteria.and("phone").is(querySmsForm.getPhone());
		}
		if(querySmsForm.getIsUse() != -1){
			criteria.and("isUse").is(querySmsForm.getIsUse());
		}
		return criteria;
	}
}
