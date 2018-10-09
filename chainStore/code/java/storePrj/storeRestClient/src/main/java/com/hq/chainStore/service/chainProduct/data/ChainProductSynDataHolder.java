package com.hq.chainStore.service.chainProduct.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class ChainProductSynDataHolder  extends AbsDataSynDataHolder<ChainProduct> {

	public static ChainProductSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainProductSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainProduct;

	protected Class<ChainProduct> getClazz() {
		return ChainProduct.class;
	}

	protected RestDao<ChainProduct> getDao() {
		return ChainProductDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}

}
