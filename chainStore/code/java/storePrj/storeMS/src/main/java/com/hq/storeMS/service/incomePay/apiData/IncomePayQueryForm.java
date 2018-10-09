package com.hq.storeMS.service.incomePay.apiData;

import com.hq.storeMS.common.util.AppUtils;

public class IncomePayQueryForm {
    public static final int CATEGORY_INVALID = -1;

    private long storeId;

    /**
     * 类别 {@link com.hq.storeMS.service.incomePay.data.IncomePayCategoryEnum}
     */
    private int category = CATEGORY_INVALID;//类别

    private long minIncomePayTime;

    private long maxIncomePayTime;

    private long minMoney;

    private long maxMoney;

    private long buserId;//人员id

    private String typeId;//分类id

    private int pageItemCount;

    private int pageNo;

    public static IncomePayQueryForm newInstance() {
        IncomePayQueryForm instance = new IncomePayQueryForm();
        return instance;
    }

    public String getListId() {
        return AppUtils.joinByUnderline(minIncomePayTime, maxIncomePayTime, storeId);
    }

    public long getStoreId() {
        return storeId;
    }

    public IncomePayQueryForm setStoreId(long storeId) {
        this.storeId = storeId;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public IncomePayQueryForm setCategory(int category) {
        this.category = category;
        return this;
    }

    public long getMinIncomePayTime() {
        return minIncomePayTime;
    }

    public IncomePayQueryForm setMinIncomePayTime(long minIncomePayTime) {
        this.minIncomePayTime = minIncomePayTime;
        return this;
    }

    public long getMaxIncomePayTime() {
        return maxIncomePayTime;
    }

    public IncomePayQueryForm setMaxIncomePayTime(long maxIncomePayTime) {
        this.maxIncomePayTime = maxIncomePayTime;
        return this;
    }

    public long getMinMoney() {
        return minMoney;
    }

    public IncomePayQueryForm setMinMoney(long minMoney) {
        this.minMoney = minMoney;
        return this;
    }

    public long getMaxMoney() {
        return maxMoney;
    }

    public IncomePayQueryForm setMaxMoney(long maxMoney) {
        this.maxMoney = maxMoney;
        return this;
    }

    public String getTypeId() {
        return typeId;
    }

    public IncomePayQueryForm setTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public long getBuserId() {
        return buserId;
    }

    public IncomePayQueryForm setBuserId(long buserId) {
        this.buserId = buserId;
        return this;
    }

    public int getPageItemCount() {
        return pageItemCount;
    }

    public IncomePayQueryForm setPageItemCount(int pageItemCount) {
        this.pageItemCount = pageItemCount;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public IncomePayQueryForm setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }
}
