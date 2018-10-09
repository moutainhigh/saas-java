package com.hq.storeMS.service.bonusRecord.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.hq.storeMS.service.bonusRecord.data.BonusRecord;
import com.hq.storeMS.service.bonusRecord.data.BonusRecordCacheDAO;
import com.hq.storeMS.service.bonusRecord.data.BonusRecordDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusRecordDataHolder {
	
	public static BonusRecordDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BonusRecordDataHolder.class);
	}
	
	public void addAndReturnId(BonusRecord target) {
		BonusRecordDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		BonusRecordCacheDAO.getInstance().delete(target);
	}

	public void update(BonusRecord target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BonusRecordDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		BonusRecordCacheDAO.getInstance().delete(target);
	}
	
	public void delete(BonusRecord target) {
		BonusRecordDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		BonusRecordCacheDAO.getInstance().delete(target);
	}

	public BonusRecord get(long storeId, long id) {
		BonusRecord target = BonusRecordCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = BonusRecordDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				BonusRecordCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<BonusRecord> findBonusRecordList(BonusRecordQueryForm queryForm) {
		List<BonusRecord> list = BonusRecordCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = BonusRecordDAO.getInstance().findBonusRecordList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				BonusRecordCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
	
}
