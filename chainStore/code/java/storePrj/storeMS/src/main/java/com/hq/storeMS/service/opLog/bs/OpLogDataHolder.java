package com.hq.storeMS.service.opLog.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.opLog.apiData.OpLogQueryForm;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogCacheDAO;
import com.hq.storeMS.service.opLog.data.OpLogDAO;
import com.hq.storeMS.service.opLog.data.OpLogRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class OpLogDataHolder {
	
	public static OpLogDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(OpLogDataHolder.class);
	}
	
	public void addAndReturnId(OpLog target) {
		OpLogDAO.getInstance().addAndReturnId(target);
		OpLogCacheDAO.getInstance().delete(target);
	}

	public void update(OpLog target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		OpLogDAO.getInstance().updpate(target);
		OpLogCacheDAO.getInstance().delete(target);
	}
	
	public void delete(OpLog target) {
		OpLogDAO.getInstance().delete(target.getId());
		OpLogCacheDAO.getInstance().delete(target);
	}
	
	public OpLog get(long id) {
		OpLog target = OpLogCacheDAO.getInstance().get(id);
		if(target == null){
			target = OpLogDAO.getInstance().get(id);
			if(target != null){
				OpLogRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<OpLog> findByCond(OpLogQueryForm queryForm) {
		List<OpLog> list = OpLogCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = OpLogDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				OpLogCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
}
