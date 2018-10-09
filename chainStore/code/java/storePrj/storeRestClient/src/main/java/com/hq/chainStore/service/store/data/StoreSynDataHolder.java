package com.hq.chainStore.service.store.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreSynDataHolder extends AbsDataSynDataHolder<Store> {

	public static StoreSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.Store;

	protected Class<Store> getClazz() {
		return Store.class;
	}
	
	public Store getData(Long ownerId,Long targetId){
		return super.getData(String.valueOf(ownerId), String.valueOf(targetId) );
	}

	protected RestDao<Store> getDao() {
		return StoreDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
