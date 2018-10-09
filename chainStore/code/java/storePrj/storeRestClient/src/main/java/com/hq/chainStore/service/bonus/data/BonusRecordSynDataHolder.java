package com.hq.chainStore.service.bonus.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BonusRecordSynDataHolder extends AbsDataSynDataHolder<BonusRecord> {

	public static BonusRecordSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.BonusRecord;

	protected Class<BonusRecord> getClazz() {
		return BonusRecord.class;
	}

	protected RestDao<BonusRecord> getDao() {
		return BonusRecordDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
