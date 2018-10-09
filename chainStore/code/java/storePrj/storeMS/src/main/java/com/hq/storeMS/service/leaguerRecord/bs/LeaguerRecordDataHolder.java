package com.hq.storeMS.service.leaguerRecord.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecord;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecordCacheDAO;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecordDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerRecordDataHolder {
	
	public static LeaguerRecordDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerRecordDataHolder.class);
	}
	
	public void addAndReturnId(LeaguerRecord target) {
		LeaguerRecordDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		LeaguerRecordCacheDAO.getInstance().delete(target);
	}

	public void update(LeaguerRecord target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		LeaguerRecordDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		LeaguerRecordCacheDAO.getInstance().delete(target);
	}
	
	public void delete(LeaguerRecord target) {
		LeaguerRecordDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		LeaguerRecordCacheDAO.getInstance().delete(target);
	}

	public LeaguerRecord get(long storeId, long id) {
		LeaguerRecord target = LeaguerRecordCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = LeaguerRecordDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				LeaguerRecordCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<LeaguerRecord> findLeaguerRecordList(LeaguerRecordQueryForm queryForm) {
		List<LeaguerRecord> list = LeaguerRecordCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = LeaguerRecordDAO.getInstance().findByCond(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				LeaguerRecordCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
	
}
