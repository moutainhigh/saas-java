package com.hq.storeMS.service.incomePay.apiData;

import com.hq.storeMS.service.incomePay.data.IncomePay;
import com.zenmind.common.beanCopy.FastBeanCopyer;

/**
 * description：编辑收支 API 表单
 * author： Xander
 * time： 2018/08/13 11:47
 */
public class IncomePayUpdateInfoForm {
    private long id;

    private long storeId;

    /**
     * 类别 {@link com.hq.storeMS.service.incomePay.data.IncomePayCategoryEnum}
     */
    private int category;

    private long incomePayTime;//收支时间

    private long money;//金额

    private String typeId;//分类id

    private long buserId;//人员id

    private String remark;//备注

    public static IncomePayUpdateInfoForm newInstance() {
        IncomePayUpdateInfoForm instance = new IncomePayUpdateInfoForm();
        return instance;
    }
    
    public void updateIncomePay(IncomePay data) {
    	FastBeanCopyer.getInstance().copy(this, data);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getIncomePayTime() {
        return incomePayTime;
    }

    public void setIncomePayTime(long incomePayTime) {
        this.incomePayTime = incomePayTime;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public long getBuserId() {
        return buserId;
    }

    public void setBuserId(long buserId) {
        this.buserId = buserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
