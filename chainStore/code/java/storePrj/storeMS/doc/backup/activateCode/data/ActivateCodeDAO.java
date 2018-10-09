package com.hq.storeMS.service.activateCode.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.activateCode.apiData.QueryActivateCodeForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ActivateCodeDAO extends MongodbDao<ActivateCode> {

	public static ActivateCodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ActivateCodeDAO.class);
	}
	
	public List<ActivateCode> findByCond(QueryActivateCodeForm queryForm) {
		Criteria criteria = buildCriteria(queryForm);
	    Query query = new Query(criteria);
		return super.find(query);
	}

	private Criteria buildCriteria(QueryActivateCodeForm queryForm) {
		Criteria criteria = new Criteria();
		if(queryForm.getPhone() > 0L){
			criteria.and("phone").is(queryForm.getPhone());
		}
		if(queryForm.getStatus() > 0){
			criteria.and("status").is(queryForm.getStatus());
		}
		if(StringUtils.isNoneBlank(queryForm.getActivateCode())){
			criteria.and("activateCode").is(queryForm.getActivateCode());
		}
		return criteria;
	}
	
	public ActivateCode findOne(String activateCode) {
		Criteria criteria = new Criteria();  
		criteria.and("activateCode").is(activateCode);
	    Query query = new Query(criteria);
		return findOne(query);
	}
}
