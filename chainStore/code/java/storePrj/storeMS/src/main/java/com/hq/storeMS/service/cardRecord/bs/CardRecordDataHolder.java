package com.hq.storeMS.service.cardRecord.bs;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.cardRecord.apiData.QueryCardRecordForm;
import com.hq.storeMS.service.cardRecord.data.CardRecord;
import com.hq.storeMS.service.cardRecord.data.CardRecordDAO;
import com.hq.storeMS.service.cardRecord.data.CardRecordRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class CardRecordDataHolder implements IntfDataHolder{
	
	public static CardRecordDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(CardRecordDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.CardRecord;
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addAndReturnId(CardRecord target) {
		CardRecordDAO.getInstance().addAndReturnId(target);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(CardRecord target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		CardRecordDAO.getInstance().updpate(target);
		
		CardRecordRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(long id) {
		CardRecordDAO.getInstance().delete(id);

		CardRecordRedisDAO.getInstance().delete(id);
	}

	public CardRecord get(long id) {
		CardRecord target = CardRecordRedisDAO.getInstance().get(id);
		if(target == null){
			target = CardRecordDAO.getInstance().get(id);
			if(target != null){
				CardRecordRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public CardRecord findOne(String cardId, String leaguerId) {
		return CardRecordDAO.getInstance().findByOne(cardId, leaguerId);
		
	}
	
	public List<CardRecord> findByCond(QueryCardRecordForm queryForm) {
		return CardRecordDAO.getInstance().findByCond(queryForm);
	}
	
	public List<CardRecord> findByCardId(String cardId) {
		return CardRecordDAO.getInstance().findByCardId(cardId);
	}
	
	public List<CardRecord> findLeaguerUsefulCards(List<String> cardIds, String leaguerId) {
		return CardRecordDAO.getInstance().findLeaguerUsefulCards(cardIds, leaguerId);
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		
		if(NumberUtils.isNumber(id)){
			long idL = Long.valueOf(id);
			CardRecord target = this.get(idL);			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.CardRecord, "CardRecordDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.CardRecord, "CardRecordDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
	
}
