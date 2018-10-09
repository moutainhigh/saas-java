package com.hq.storeManagerMS.service.charge.data;

public enum ChargeStatusEnum {
	NOT_PAY("未支付"),
	HAS_PAY("已支付"),
	CANCEL("已取消"),
    ;

    private String descript;

    private ChargeStatusEnum(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public static ChargeStatusEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
