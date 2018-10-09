package com.hq.storeClient.service.charge.data;

/**
 * description：收费渠道枚举
 * author： Xander
 * time： 2018/8/21 15:05
 */
public enum ChargeChannelEnum {
    CASH("现金"),
    ALIPAY("支付宝"),
    WECHAT("微信"),
    UNION_PAY("银联"),
    ;

    private String descript;

    ChargeChannelEnum(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public static ChargeChannelEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
