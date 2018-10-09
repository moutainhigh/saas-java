package com.hq.storeMS.service.incomePay.data;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description：收支
 * author： Xander
 * incomePayTime： 2018/8/10 17:59
 */
@SynClass
@Table(name = "incomePay")
public class IncomePay {
    @Id
    private long id;
    @IndexField
    private long storeId;

    /**
     * 类别 {@link IncomePayCategoryEnum}
     */
    private int category;

    @IndexField
    private long incomePayTime;//收支时间

    private long money;//金额

    private String typeId;//分类id

    private long buserId;//人员id

    private String remark;//备注
    // 实体状态
    private int entityState;

    private long createdTime;
    private long lastUpdateTime;
    private long ver;


    public static IncomePay newInstance() {
        IncomePay instance = new IncomePay();
        long currentTime = System.currentTimeMillis();
        instance.createdTime = currentTime;
        instance.lastUpdateTime = currentTime;
        return instance;
    }

    public void incrVer() {
        this.ver = this.ver + 1;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getVer() {
        return ver;
    }

    public void setVer(long ver) {
        this.ver = ver;
    }

    public int getEntityState() {
        return entityState;
    }

    public void setEntityState(int entityState) {
        this.entityState = entityState;
    }
}
