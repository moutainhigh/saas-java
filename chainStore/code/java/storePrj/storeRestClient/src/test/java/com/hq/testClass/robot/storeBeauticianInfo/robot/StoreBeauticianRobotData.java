package com.hq.testClass.robot.storeBeauticianInfo.robot;

public class StoreBeauticianRobotData {

	private int mark;
	
	private long storeId;
	
	private long buserId;
	
	private int state;
	
	private String descript;
	
	private long id;

	public static StoreBeauticianRobotData newInstance(int mark){
		StoreBeauticianRobotData robotData = new StoreBeauticianRobotData();
		robotData.state = 0;
		robotData.descript = "医美师"+mark;
		return robotData;
	}
	
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
