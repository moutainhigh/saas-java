package com.hq.storeMS.service.leaguerRecord.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerRecordCacheDAO {

	public static LeaguerRecordCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerRecordCacheDAO.class);
	}

	final private String suffix = "leaguerRecord";

	public void saveList(LeaguerRecordQueryForm queryForm, List<LeaguerRecord> list) {
		LeaguerRecordRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<LeaguerRecord> getList(LeaguerRecordQueryForm queryForm) {
		return LeaguerRecordRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(LeaguerRecord target) {
		LeaguerRecordRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public LeaguerRecord get(long storeId, long id) {
		return LeaguerRecordRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(LeaguerRecord target) {
		LeaguerRecordRedisDAO.getInstance().delete(target.getId());
		LeaguerRecordRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
