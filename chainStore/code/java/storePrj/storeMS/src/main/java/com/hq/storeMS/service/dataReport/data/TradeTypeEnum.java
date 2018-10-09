package com.hq.storeMS.service.dataReport.data;

public enum TradeTypeEnum {

    RECEIPT("收款"),
    REFUND("退款");
    private String remark;

    TradeTypeEnum(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public static TradeTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
