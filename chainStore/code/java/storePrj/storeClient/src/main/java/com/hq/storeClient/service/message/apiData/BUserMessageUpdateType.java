package com.hq.storeClient.service.message.apiData;

public enum BUserMessageUpdateType {
	UpdateState("修改消息状态"),
	BatchUpdateState("批量修改消息状态"),
	;
	
	private String mark;
	
	private BUserMessageUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static BUserMessageUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
