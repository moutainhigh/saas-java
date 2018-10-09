package com.hq.storeMS.service.arrearage.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.arrearage.data.ArrearageCacheDAO;
import com.hq.storeMS.service.arrearage.data.ArrearageDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageDataHolder {
	
	public static ArrearageDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ArrearageDataHolder.class);
	}
	
	public void addAndReturnId(Arrearage target) {
		ArrearageDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		ArrearageCacheDAO.getInstance().delete(target);
	}

	public void updpate(Arrearage target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ArrearageDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		ArrearageCacheDAO.getInstance().delete(target);
	}

	public void delete(Arrearage target) {
		ArrearageDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		ArrearageCacheDAO.getInstance().delete(target);
	}

	public Arrearage get(long storeId, long id) {
		Arrearage target = ArrearageCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = ArrearageDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				ArrearageCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<Arrearage> findArrearageList(ArrearageQueryForm queryForm) {
		List<Arrearage> list = ArrearageCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ArrearageDAO.getInstance().findArrearageList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ArrearageCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	public Arrearage findArrearageByOrderId(long storeId, long orderId) {
		return ArrearageDAO.getInstance().findArrearageByOrderId(getBossId(storeId), orderId);
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}
