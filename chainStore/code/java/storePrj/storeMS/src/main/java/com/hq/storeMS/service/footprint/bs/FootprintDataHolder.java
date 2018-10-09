package com.hq.storeMS.service.footprint.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.footprint.apiData.FootprintQueryForm;
import com.hq.storeMS.service.footprint.data.Footprint;
import com.hq.storeMS.service.footprint.data.FootprintCacheDAO;
import com.hq.storeMS.service.footprint.data.FootprintDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class FootprintDataHolder{
	
	public static FootprintDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(FootprintDataHolder.class);
	}
	
	public void addAndReturnId(Footprint target) {
		FootprintDAO.getInstance().addAndReturnId(target);
		FootprintCacheDAO.getInstance().delete(target);
	}

	public void update(Footprint target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		FootprintDAO.getInstance().updpate(target);
		FootprintCacheDAO.getInstance().delete(target);
	}
	
	public void delete(Footprint target) {
		FootprintDAO.getInstance().delete(target.getId());
		FootprintCacheDAO.getInstance().delete(target);
	}
	
	public Footprint get(long id) {
		Footprint target = FootprintCacheDAO.getInstance().get(id);
		if(target == null){
			target = FootprintDAO.getInstance().get(id);
			if(target != null){
				FootprintCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<Footprint> findByCond(FootprintQueryForm queryForm) {
		List<Footprint> list = FootprintCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = FootprintDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				FootprintCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
}
