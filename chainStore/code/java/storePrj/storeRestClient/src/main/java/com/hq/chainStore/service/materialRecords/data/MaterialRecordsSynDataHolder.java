package com.hq.chainStore.service.materialRecords.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class MaterialRecordsSynDataHolder extends AbsDataSynDataHolder<MaterialRecords> {

	public static MaterialRecordsSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(MaterialRecordsSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.MaterialRecords;

	protected Class<MaterialRecords> getClazz() {
		return MaterialRecords.class;
	}

	protected RestDao<MaterialRecords> getDao() {
		return MaterialRecordsDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
