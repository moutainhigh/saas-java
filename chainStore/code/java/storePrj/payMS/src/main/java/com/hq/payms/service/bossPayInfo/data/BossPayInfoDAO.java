package com.hq.payms.service.bossPayInfo.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.payms.service.bossPayInfo.apiData.BossPayInfoQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class BossPayInfoDAO extends MongodbDao<BossPayInfo> {

	public static BossPayInfoDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BossPayInfoDAO.class);
	}
	
	public BossPayInfo findByStoreId(long storeId){
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(storeId);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
	
	public List<BossPayInfo> findList(BossPayInfoQueryForm params) {
	    Query query = new Query(buildCriteria(params));
	    //按更新时间  倒序
	    query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "lastUpdateTime")));
		return findPage(query, params.getPageItemCount(), params.getPageNo());
	}
	
	private Criteria buildCriteria(BossPayInfoQueryForm params){
		Criteria criteria = new Criteria();
		if(params.getStoreId() > 0L)
			criteria.and("storeId").is(params.getStoreId());
		if(StringUtils.isNotBlank(params.getWxpayAppId()))
			criteria.and("wxpayAppId").regex("^.*" + params.getWxpayAppId() + ".*$");
		if(StringUtils.isNotBlank(params.getAlipayAppId()))
			criteria.and("alipayAppId").regex("^.*" + params.getAlipayAppId() + ".*$");
//		
//		//时间段判断
//		if(params.getMinTime() > 0L && params.getMaxTime() > 0L){
//			criteria.and("createdTime").gte(params.getMinTime()).lte(params.getMaxTime());
//		}else if(params.getMaxTime() > 0L){
//			criteria.and("createdTime").lte(params.getMaxTime());
//		}else if(params.getMinTime() > 0L){
//			criteria.and("createdTime").gte(params.getMinTime());
//		}
//		
//		if(StringUtils.isNoneBlank(params.getNumberOrName())){
//			Criteria ct = new Criteria();
//			ct.orOperator(Criteria.where("number").regex("^.*" + params.getNumberOrName() + ".*$"),
//					Criteria.where("name").regex("^.*" + params.getNumberOrName() + ".*$"));
//			criteria.andOperator(ct);
//		}
		
		return criteria;
	}
	
	private Criteria buildCriteria4ExactQuery(BossPayInfoQueryForm params){
		Criteria criteria = new Criteria();
		if(params.getStoreId() > 0L)
			criteria.and("storeId").is(params.getStoreId());
		if(StringUtils.isNotBlank(params.getWxpayAppId()))
			criteria.and("wxpayAppId").is(params.getWxpayAppId());
		if(StringUtils.isNotBlank(params.getWxpayCertPath()))
			criteria.and("wxpayCertPath").is(params.getWxpayCertPath());
		if(StringUtils.isNotBlank(params.getAlipayAppId()))
			criteria.and("alipayAppId").is(params.getAlipayAppId());
		return criteria;
	}
	
	public BossPayInfoCount getCount(BossPayInfoQueryForm params) {
	    Query query = new Query(buildCriteria(params));
	    long count = count(query);
	    BossPayInfoCount countObj = BossPayInfoCount.newInstance();
	    countObj.setCount(count);
		return countObj;
	}

	public BossPayInfoCount getCount4ExactQuery(BossPayInfoQueryForm params) {
	    Query query = new Query(buildCriteria4ExactQuery(params));
	    long count = count(query);
	    BossPayInfoCount countObj = BossPayInfoCount.newInstance();
	    countObj.setCount(count);
		return countObj;
	}
}