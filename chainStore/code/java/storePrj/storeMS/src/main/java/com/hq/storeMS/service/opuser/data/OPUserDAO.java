package com.hq.storeMS.service.opuser.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.opuser.apiData.OPuserQueryApiForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class OPUserDAO extends MongodbDao<OPUser> {

	public static OPUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserDAO.class);
	}

	public OPUser findByPhone(long phone){
		Criteria criteria = new Criteria();  
		criteria.and("phone").is(phone);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
	
	public OPUser findByName(String name){
		Criteria criteria = new Criteria();  
		criteria.and("name").is(name);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
	
	@Override
	public void delete(Object id) {
		//只做软删除
		OPUser target = get(id);
		if(target!=null){
			target.setState(EntityState.Deleted);
			updpate(target);
		}
	}
	
	public List<OPUser> findOPuserList(OPuserQueryApiForm queryForm) {
		Criteria criteria = buildCriteria(queryForm);
	    Query query = new Query(criteria);
		return super.find(query);
	}

	private Criteria buildCriteria(OPuserQueryApiForm queryForm) {
		Criteria criteria = new Criteria();
		if(StringUtils.isNotBlank(queryForm.getName())){
			criteria.and("name").is(queryForm.getName());
		}
		
		if(queryForm.getPhone() > 0L){
			criteria.and("phone").is(queryForm.getPhone());
		}
		return criteria;
	}
	
}
