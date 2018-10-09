package com.hq.chainStore.service.storePackageProject.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StorePackageProjectSynDataHolder extends AbsDataSynDataHolder<StorePackageProject> {

	public static StorePackageProjectSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StorePackageProjectSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StorePackageProject;

	protected Class<StorePackageProject> getClazz() {
		return StorePackageProject.class;
	}

	protected RestDao<StorePackageProject> getDao() {
		return StorePackageProjectDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
}
