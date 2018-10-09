package com.hq.chainStore.service.chainGoods.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainGoodsSynDataHolder  extends AbsDataSynDataHolder<ChainGoods> {

	public static ChainGoodsSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainGoods;

	protected Class<ChainGoods> getClazz() {
		return ChainGoods.class;
	}

	protected RestDao<ChainGoods> getDao() {
		return ChainGoodsDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
