package com.hq.chainStore.service.message.data;


public enum MessageTypeEnum {
	UNKNOW("未知"),
	CLERK_MNG("店员管理"),
	APPOINTMENT_MNG("预约管理"),
	BEAUTICIAN_APPOINTMENT("我的预约"),
	ORDER("订单"),
	SCHEDULE("待办事项"),
	WORKFLOW_MNG("店务管理"),
	MY_WORKFLOW("我的店务"),
	MY_MESSAGE_CENTER("消息中心"),
	;
	
	private String mark;
	
	private MessageTypeEnum(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

	public static MessageTypeEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
	
	
}
