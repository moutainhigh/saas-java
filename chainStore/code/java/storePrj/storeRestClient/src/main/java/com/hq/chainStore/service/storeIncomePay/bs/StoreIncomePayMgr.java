package com.hq.chainStore.service.storeIncomePay.bs;

import com.hq.chainStore.service.storeIncomePay.apiData.*;
import com.hq.chainStore.service.storeIncomePay.apiData.StoreIncomePayUpdateForm;
import com.hq.chainStore.service.storeIncomePay.apiData.StoreIncomePayUpdateType;
import com.hq.chainStore.service.storeIncomePay.data.StoreIncomePay;
import com.hq.chainStore.service.storeIncomePay.data.StoreIncomePayDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreIncomePayMgr {

    public static StoreIncomePayMgr getInstance() {
        return HotSwap.getInstance().getSingleton(StoreIncomePayMgr.class);
    }

    public StoreIncomePay getStoreIncomePay(long storeId) {
        return StoreIncomePayDAO.getInstance().get(storeId);
    }

    public void update(long storeId, StoreIncomePayUpdateForm updateForm) {
        StoreIncomePayDAO.getInstance().update(storeId, updateForm);
    }

    /**
     * 添加 收支 分类
     *
     * @param storeId
     * @param dataForm
     */
    public void addIncomePayType(long storeId, IncomePayTypeAddForm dataForm) {
        StoreIncomePayUpdateForm updateForm = StoreIncomePayUpdateForm.newInstance();
        updateForm.setUpdateType(StoreIncomePayUpdateType.AddIncomePayType.ordinal());
        updateForm.setIncomePayTypeAddForm(dataForm);
        update(storeId, updateForm);
    }

    /**
     * 删除 收支 分类
     *
     * @param storeId
     * @param dataForm
     */
    public void removeIncomePayType(long storeId, IncomePayTypeRemoveForm dataForm) {
        StoreIncomePayUpdateForm updateForm = StoreIncomePayUpdateForm.newInstance();
        updateForm.setUpdateType(StoreIncomePayUpdateType.RemoveIncomePayType.ordinal());
        updateForm.setIncomePayTypeRemoveForm(dataForm);
        update(storeId, updateForm);
    }

    /**
     * 编辑 收支 分类
     *
     * @param storeId
     * @param dataForm
     */
    public void updateIncomePayType(long storeId, IncomePayTypeUpdateForm dataForm) {
        StoreIncomePayUpdateForm updateForm = StoreIncomePayUpdateForm.newInstance();
        updateForm.setUpdateType(StoreIncomePayUpdateType.UpdateIncomePayType.ordinal());
        updateForm.setIncomePayTypeUpdateForm(dataForm);
        update(storeId, updateForm);
    }


}
