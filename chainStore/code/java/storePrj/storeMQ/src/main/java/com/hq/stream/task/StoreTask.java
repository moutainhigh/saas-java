package com.hq.stream.task;

public class StoreTask {
	//用户标识
	private long userId;
	//任务类型 StoreTaskType
	private int taskType;
	//任务内容 TaskAwakeData
	private String jsonData;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getTaskType() {
		return taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public StoreTaskType getTaskTypeEnum() {
		return StoreTaskType.valueOf(this.taskType);
	}

	public void setTaskTypeEnum(StoreTaskType taskType) {
		this.taskType = taskType.ordinal();
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
