package com.hq.chainStore.service.cardRecord.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class CardRecordSynDataHolder extends AbsDataSynDataHolder<CardRecord> {

	public static CardRecordSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(CardRecordSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.CardRecord;

	protected Class<CardRecord> getClazz() {
		return CardRecord.class;
	}

	protected RestDao<CardRecord> getDao() {
		return CardRecordDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
