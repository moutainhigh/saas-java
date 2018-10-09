package com.hq.storeMS.service.cardRecord.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.cardRecord.apiData.PlusCardRecordForm;
import com.hq.storeMS.service.cardRecord.apiData.QueryCardRecordForm;
import com.hq.storeMS.service.cardRecord.data.CardRecord;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class CardRecordHandler {

	public static CardRecordHandler getInstance() {
		return HotSwap.getInstance().getSingleton(CardRecordHandler.class);
	}
	
	private final LogModule logModule = LogModule.CardRecord;
	
	public ReqResult<CardRecord> findByCond(QueryCardRecordForm queryForm) {
		ReqResult<CardRecord> result = ReqResult.newInstance(false, CardRecord.class);
		try {
			List<CardRecord> list = CardRecordMgr.getInstance().findByCond(queryForm);
			result.setTargetList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CardRecordHandler[findByStoreId]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<CardRecord> findByCardId(String cardId) {
		ReqResult<CardRecord> result = ReqResult.newInstance(false, CardRecord.class);
		try {
			List<CardRecord> list = CardRecordMgr.getInstance().findByCardId(cardId);
			result.setTargetList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CardRecordHandler[findByCardId]";
			final String reason = LogHelper.getInstance().exceptionReason(cardId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<CardRecord> findLeaguerCardById(String cardId, String leaguerId) {
		ReqResult<CardRecord> result = ReqResult.newInstance(false, CardRecord.class);
		try {
			CardRecord cardRecord = CardRecordMgr.getInstance().findOne(cardId, leaguerId);
			result.setTarget(cardRecord);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CardRecordHandler[findLeaguerCardById]";
			final String reason = LogHelper.getInstance().exceptionReason(cardId, leaguerId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<CardRecord> findLeaguerUsefulCards(String cardIds, String leaguerId) {
		ReqResult<CardRecord> result = ReqResult.newInstance(false, CardRecord.class);
		try {
			String[] ids = cardIds.split(ServerConstants.COMMA_SYMBOL);
			List<String> list = new ArrayList<String>(ids.length);
			Collections.addAll(list, ids);
			List<CardRecord> cardRecords = CardRecordMgr.getInstance().findLeaguerUsefulCards(list, leaguerId);
			result.setTargetList(cardRecords);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CardRecordHandler[findUsefulCards]";
			final String reason = LogHelper.getInstance().exceptionReason(cardIds, leaguerId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<CardRecord> plusCount(PlusCardRecordForm plusForm) {
		ReqResult<CardRecord> result = ReqResult.newInstance(false, CardRecord.class);
		try {
			Long productId = plusForm.getProductId();
			String leaguerId = plusForm.getLeaguerId();
			List<CardRecord> list = CardRecordMgr.getInstance().findLeaguerUsefulCards(plusForm.getCardIds(), leaguerId);
			//只统计次数
			for (CardRecord cardRecord : list) {
				if(cardRecord.getCardId().startsWith(ServerConstants.STORE_PRODUCTCARD_ID_SUFFFIX)){//耗卡
					Map<Long, Long> useMap = cardRecord.getUseCountMap();
					useMap.put(productId, MapUtils.getLong(useMap, productId, 0L) + 1);
					cardRecord.setUseCountMap(useMap);
				}else if(cardRecord.getCardId().startsWith(ServerConstants.STORE_MEMBERSHIPCARD_ID_SUFFFIX)){//会员卡
					cardRecord.setCount(cardRecord.getCount()+1);
				}else if(cardRecord.getCardId().startsWith(ServerConstants.STORE_DISCOUNTCARD_ID_SUFFFIX)){//优惠卷
					cardRecord.setCount(cardRecord.getCount()+1);
				}else {
					continue;
				}
				CardRecordMgr.getInstance().updateCardRecord(cardRecord);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "CardRecordHandler[plusCount]";
			final String reason = LogHelper.getInstance().exceptionReason(plusForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
}
