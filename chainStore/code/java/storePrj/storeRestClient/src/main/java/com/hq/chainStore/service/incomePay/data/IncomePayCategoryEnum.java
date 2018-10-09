package com.hq.chainStore.service.incomePay.data;

/**
 * description：收支分类枚举
 * author： Xander
 * time： 2018/08/14 10:32
 */
public enum IncomePayCategoryEnum {
    PAY("支出"),
    INCOME("收入"),
    ;

    private String descript;

    IncomePayCategoryEnum(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public static IncomePayCategoryEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
