package com.hq.chainMS.service.chain.data;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.chainMS.service.chain.apiData.ChainQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class ChainDAO extends MongodbDao<Chain> {

	public static ChainDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainDAO.class);
	}

	public Chain findByNumber(String number) {
		Criteria criteria = new Criteria();  
		criteria.and("number").is(number);
		return super.findOne(new Query(criteria));
	}

	public List<Chain> findChainByCond(ChainQueryForm queryForm) {
		return super.find(buildQuery(queryForm));
	}
	
	private Query buildQuery(ChainQueryForm queryForm){
		Criteria criteria = new Criteria();
		criteria.and("state").is(ChainState.Open.ordinal());
		if(StringUtils.isNoneBlank(queryForm.getNumber())) {
			criteria.and("number").is(queryForm.getNumber());
		}
		if(CollectionUtils.isNotEmpty(queryForm.getChainIds())) {
			criteria.and("_id").in(queryForm.getChainIds());
		}
	    return new Query(criteria);
	}
	
}
