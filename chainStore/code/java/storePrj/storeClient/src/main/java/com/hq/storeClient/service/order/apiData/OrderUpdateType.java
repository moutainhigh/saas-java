package com.hq.storeClient.service.order.apiData;

public enum OrderUpdateType {
	UpdateInfo("修改订单基本信息"),
	UpdateState("修改订单状态"),
	UpdateMaterial("修改订单耗材信息"),
	UpdatePayItem("支付订单"),
	DeleteOrder("删除订单"),
	UpdateChargeBackCost("更新订单退款金额"),
	PayOrderWithNote("支付订单,添加订单备注"),
	
	;
	
	private String mark;
	
	private OrderUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static OrderUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
