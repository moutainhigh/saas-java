package com.hq.storeMS.service.dynamic.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.hq.storeMS.service.dynamic.data.DynamicCacheDAO;
import com.hq.storeMS.service.dynamic.data.DynamicDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class DynamicDataHolder{
	
	public static DynamicDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(DynamicDataHolder.class);
	}
	
	public void addAndReturnId(Dynamic target) {
		DynamicDAO.getInstance().addAndReturnId(target);
		DynamicCacheDAO.getInstance().delete(target);
	}

	public void update(Dynamic target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		DynamicDAO.getInstance().updpate(target);
		DynamicCacheDAO.getInstance().delete(target);
	}
	
	public void delete(Dynamic target) {
		DynamicDAO.getInstance().delete(target.getId());
		DynamicCacheDAO.getInstance().delete(target);
	}
	
	public Dynamic get(long id) {
		Dynamic target = DynamicCacheDAO.getInstance().get(id);
		if(target == null){
			target = DynamicDAO.getInstance().get(id);
			if(target != null){
				DynamicCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<Dynamic> findByCond(DynamicQueryForm queryForm) {
		List<Dynamic> list = DynamicCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = DynamicDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				DynamicCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
}
