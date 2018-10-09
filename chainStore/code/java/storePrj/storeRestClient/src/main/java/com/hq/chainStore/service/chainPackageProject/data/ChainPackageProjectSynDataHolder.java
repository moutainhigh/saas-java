package com.hq.chainStore.service.chainPackageProject.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainPackageProjectSynDataHolder  extends AbsDataSynDataHolder<ChainPackageProject> {

	public static ChainPackageProjectSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainPackageProjectSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainPackageProject;

	protected Class<ChainPackageProject> getClazz() {
		return ChainPackageProject.class;
	}

	protected RestDao<ChainPackageProject> getDao() {
		return ChainPackageProjectDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
