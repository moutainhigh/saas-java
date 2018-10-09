package com.hq.chainStore.service.detailDataVersion.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class DetailDataVersionSynDataHolder extends AbsDataSynDataHolder<DetailDataVersion> {

	public static DetailDataVersionSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(DetailDataVersionSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.DetailDataVersion;

	protected Class<DetailDataVersion> getClazz() {
		return DetailDataVersion.class;
	}

	protected RestDao<DetailDataVersion> getDao() {
		return DetailDataVersionDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
}
