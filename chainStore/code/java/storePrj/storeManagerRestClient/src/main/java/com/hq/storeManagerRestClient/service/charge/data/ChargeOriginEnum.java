package com.hq.storeManagerRestClient.service.charge.data;

public enum ChargeOriginEnum {
    ManagerMS("管理后台"),
    StoreMS("智美通"),
    ;

    private String descript;

    private ChargeOriginEnum(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public static ChargeOriginEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
