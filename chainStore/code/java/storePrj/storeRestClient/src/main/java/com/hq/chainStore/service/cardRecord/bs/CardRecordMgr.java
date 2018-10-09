package com.hq.chainStore.service.cardRecord.bs;

import java.util.List;
import java.util.Set;

import com.hq.chainStore.service.cardRecord.apiData.PlusCardRecordForm;
import com.hq.chainStore.service.cardRecord.apiData.QueryCardRecordForm;
import com.hq.chainStore.service.cardRecord.data.CardRecord;
import com.hq.chainStore.service.cardRecord.data.CardRecordDAO;
import com.hq.chainStore.service.common.ClientConstants;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class CardRecordMgr {

	public static CardRecordMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CardRecordMgr.class);
	}
	
	public List<CardRecord> findByCond(QueryCardRecordForm queryForm) {
		final String findPath = "findByCond";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", queryForm.getMinTime()).add("maxTime", queryForm.getMaxTime())
			.add("storeId", queryForm.getStoreId());
		return CardRecordDAO.getInstance().findWithReqParam(findPath, reqMap, queryForm.getPageItemCount(), queryForm.getPageNo());
	}
	
	public List<CardRecord> findByCardId(String cardId) {
		final String findPath = "findByCardId";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("cardId", cardId);
		return CardRecordDAO.getInstance().findWithReqParam(findPath, reqMap, 50, 1);
	}
	
	public List<CardRecord> findLeaguerUsefulCards(Set<String> cardIds, String leaguerId) {
		final String findPath = "findLeaguerUsefulCards";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("cardIds", StringUtils4Client.join(cardIds, ClientConstants.COMMA_SYMBOL)).add("leaguerId", leaguerId);
		return CardRecordDAO.getInstance().findWithReqParam(findPath, reqMap, 50, 1);
	}
	
	/**
	 * 
	 * @Deprecated 获取的方法 已经移到CardRecordSynDataHolder
	 *
	 */
	@Deprecated
	public CardRecord findLeaguerCardById(String cardId, String leaguerId) {
		final String findPath = "findLeaguerCardById";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("cardId", cardId).add("leaguerId", leaguerId);
		return CardRecordDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public CardRecord plusCount(PlusCardRecordForm plusCardRecordForm) {
		final String findPath = "plusCount";
		RestResp restResp = CardRecordDAO.getInstance().rawReq(findPath, plusCardRecordForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), CardRecord.class);
	}

}
