package com.hq.chainStore.service.storeIncomePay.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreIncomePaySynDataHolder extends AbsDataSynDataHolder<StoreIncomePay> {

    public static StoreIncomePaySynDataHolder getInstance() {
        return HotSwap.getInstance().getSingleton(StoreIncomePaySynDataHolder.class);
    }

    final private DataSynType synType = DataSynType.StoreIncomePay;

    protected Class<StoreIncomePay> getClazz() {
        return StoreIncomePay.class;
    }

    protected RestDao<StoreIncomePay> getDao() {
        return StoreIncomePayDAO.getInstance();
    }

    public DataSynType getSynType() {
        return synType;
    }
}
