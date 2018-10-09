package com.hq.storeManagerMS.service.muserAdminRole.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleQueryApiForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class MUserAdminRoleDAO extends MongodbDao<MUserAdminRole> {

	public static MUserAdminRoleDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MUserAdminRoleDAO.class);
	}
	
	public List<MUserAdminRole> findList(MUserAdminRoleQueryApiForm queryForm) {
		Query query = buildQuery(queryForm);
	    return super.find(query);
	}
	
	public List<MUserAdminRole> findByNames(List<String> names) {
		Criteria criteria = new Criteria();
		criteria.and("name").in(names);
		Query query = new Query(criteria);
		return findPage(query, ServerConstants.PAGE_ITEM_COUNT, 1);
	}
	
	private Query buildQuery(MUserAdminRoleQueryApiForm queryForm){
		Criteria criteria = new Criteria();
		if(StringUtils.isNoneBlank(queryForm.getName())){
			criteria.and("name").regex("^.*" + queryForm.getName() + ".*$");
		}
		
		if(queryForm.getState() != -1){
			criteria.and("status").is(queryForm.getState());
		}
		
	    Query query = new Query(criteria);
	    
	    return query;
	}
}
