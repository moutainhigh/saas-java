package com.hq.storeMS.service.storeIncomePay.apiData;

/**
 * description：店铺收支 update 表单
 * author： Xander
 * time： 2018/8/13 16:24
 */
public class StoreIncomePayUpdateForm {
    /**
     * update 类型
     * {@link com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateType}
     */
    private int updateType;//update 类型

    private IncomePayTypeAddForm incomePayTypeAddForm;
    private IncomePayTypeRemoveForm incomePayTypeRemoveForm;
    private IncomePayTypeUpdateForm incomePayTypeUpdateForm;

    public static StoreIncomePayUpdateForm newInstance() {
        return new StoreIncomePayUpdateForm();
    }

    public StoreIncomePayUpdateType getStoreIncomePayUpdateType() {
        return StoreIncomePayUpdateType.valueOf(updateType);
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public IncomePayTypeAddForm getIncomePayTypeAddForm() {
        return incomePayTypeAddForm;
    }

    public void setIncomePayTypeAddForm(IncomePayTypeAddForm incomePayTypeAddForm) {
        this.incomePayTypeAddForm = incomePayTypeAddForm;
    }

    public IncomePayTypeRemoveForm getIncomePayTypeRemoveForm() {
        return incomePayTypeRemoveForm;
    }

    public void setIncomePayTypeRemoveForm(IncomePayTypeRemoveForm incomePayTypeRemoveForm) {
        this.incomePayTypeRemoveForm = incomePayTypeRemoveForm;
    }

    public IncomePayTypeUpdateForm getIncomePayTypeUpdateForm() {
        return incomePayTypeUpdateForm;
    }

    public void setIncomePayTypeUpdateForm(IncomePayTypeUpdateForm incomePayTypeUpdateForm) {
        this.incomePayTypeUpdateForm = incomePayTypeUpdateForm;
    }
}
