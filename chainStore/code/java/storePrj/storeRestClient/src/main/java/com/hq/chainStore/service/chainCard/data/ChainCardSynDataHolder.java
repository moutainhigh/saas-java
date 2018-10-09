package com.hq.chainStore.service.chainCard.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainCardSynDataHolder  extends AbsDataSynDataHolder<ChainCard> {

	public static ChainCardSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainCard;

	protected Class<ChainCard> getClazz() {
		return ChainCard.class;
	}

	protected RestDao<ChainCard> getDao() {
		return ChainCardDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
