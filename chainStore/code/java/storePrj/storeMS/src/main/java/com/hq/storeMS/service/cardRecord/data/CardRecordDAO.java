package com.hq.storeMS.service.cardRecord.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.cardRecord.apiData.QueryCardRecordForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class CardRecordDAO extends MongodbDao<CardRecord> {

	public static CardRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CardRecordDAO.class);
	}
	
	public CardRecord findByOne(String cardId, String leaguerId){
		Criteria criteria = new Criteria();
		criteria.and("cardId").is(cardId);
		criteria.and("leaguerId").is(leaguerId);
		criteria.and("status").is(CardRecordStatusEnum.VALID.ordinal());
		Query query = new Query(criteria);
		return findOne(query);
	}
	
	public List<CardRecord> findByCond(QueryCardRecordForm queryForm) {
		Criteria criteria = buildCriteria(queryForm);
	    Query query = new Query(criteria);
		return super.find(query);
	}

	private Criteria buildCriteria(QueryCardRecordForm queryForm) {
		Criteria criteria = new Criteria();
		if(queryForm.getStoreId() > 0){
			criteria.and("storeId").is(queryForm.getStoreId());
		}
		if(queryForm.getStatus() > 0){
			criteria.and("status").is(queryForm.getStatus());
		}
		if(StringUtils.isNoneBlank(queryForm.getCardId())){
			criteria.and("cardId").is(queryForm.getCardId());
		}
		if(StringUtils.isNoneBlank(queryForm.getLeaguerId())){
			criteria.and("leaguerId").is(queryForm.getLeaguerId());
		}
		//时间段判断
		if(queryForm.getMinTime() > 0L && queryForm.getMaxTime() > 0L){
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else if(queryForm.getMaxTime() > 0L){
			criteria.and("createdTime").lte(queryForm.getMaxTime());
		}else if(queryForm.getMinTime() > 0L){
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		return criteria;
	}
	
	public List<CardRecord> findByCardId(String cardId) {
		Criteria criteria = new Criteria();
		criteria.and("cardId").is(cardId);
	    Query query = new Query(criteria);
	    Long count = CardRecordDAO.getInstance().count(query);
		return findPage(query, count.intValue(), 1);
	}
	
	public List<CardRecord> findLeaguerUsefulCards(List<String> cardIds, String leaguerId) {
		Criteria criteria = new Criteria();
		criteria.and("cardId").in(cardIds);
		criteria.and("leaguerId").is(leaguerId);
		criteria.and("status").is(CardRecordStatusEnum.VALID.ordinal());
		Query query = new Query(criteria);
		return findPage(query, cardIds.size(), 1);
	}
	
}
