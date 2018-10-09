package com.hq.storeMS.service.cardRecord.bs;

import java.util.List;

import com.hq.storeMS.service.cardRecord.apiData.QueryCardRecordForm;
import com.hq.storeMS.service.cardRecord.data.CardRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class CardRecordMgr {

	public static CardRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CardRecordMgr.class);
	}
	
	public void addAndReturnId(CardRecord target) {
		CardRecordDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void updateCardRecord(CardRecord target) {
		CardRecordDataHolder.getInstance().update(target);
	}
	
	public void delete(long id){
		CardRecordDataHolder.getInstance().delete(id);
	}
	
	public CardRecord get(long id){
		return CardRecordDataHolder.getInstance().get(id);
	}
	
	public CardRecord findOne(String cardId, String leaguerId) {
		return CardRecordDataHolder.getInstance().findOne(cardId, leaguerId);
	}
	
	public List<CardRecord> findByCond(QueryCardRecordForm queryForm) {
		return CardRecordDataHolder.getInstance().findByCond(queryForm);
	}
	
	public List<CardRecord> findByCardId(String cardId) {
		return CardRecordDataHolder.getInstance().findByCardId(cardId);
	}
	
	public List<CardRecord> findLeaguerUsefulCards(List<String> cardIds, String leaguerId) {
		return CardRecordDataHolder.getInstance().findLeaguerUsefulCards(cardIds, leaguerId);
	}
	
}
