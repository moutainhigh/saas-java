package com.hq.storeMS.service.areaCode.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeMS.service.areaCode.data.AreaCode;
import com.hq.storeMS.service.areaCode.data.AreaCodeCacheDAO;
import com.hq.storeMS.service.areaCode.data.AreaCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeDataHolder {
	
	public static AreaCodeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeDataHolder.class);
	}
	
	public void addAndReturnId(AreaCode target) {
		AreaCodeDAO.getInstance().addAndReturnId(target);
		AreaCodeCacheDAO.getInstance().delete(target);
	}

	public void update(AreaCode target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		AreaCodeDAO.getInstance().updpate(target);
		AreaCodeCacheDAO.getInstance().delete(target);
	}
	
	public void delete(AreaCode target) {
		AreaCodeDAO.getInstance().delete(target.getId());
		AreaCodeCacheDAO.getInstance().delete(target);
	}
	
	public AreaCode get(long id) {
		AreaCode target = AreaCodeCacheDAO.getInstance().get(id);
		if(target == null){
			target = AreaCodeDAO.getInstance().get(id);
			if(target != null){
				AreaCodeCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		List<AreaCode> list = AreaCodeCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = AreaCodeDAO.getInstance().findList(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				AreaCodeCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
}
