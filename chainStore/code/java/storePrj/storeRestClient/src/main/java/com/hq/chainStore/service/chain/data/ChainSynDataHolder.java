package com.hq.chainStore.service.chain.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainSynDataHolder extends AbsDataSynDataHolder<Chain> {

	public static ChainSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.Chain;

	protected Class<Chain> getClazz() {
		return Chain.class;
	}

	protected RestDao<Chain> getDao() {
		return ChainDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
