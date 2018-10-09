package com.hq.stream.task;

public enum StoreTaskType {
	UnKnown,  //未知事件
	GenExperienceData, //生成演示数据
	;

	public static StoreTaskType valueOf(int ordinal) {
		
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}

}
