package com.hq.testClass.robot.schedule;

import org.apache.commons.lang3.RandomUtils;


public class ScheduleRobotData {


	// 随机数标记，用来生成电话号码等信息
	private int mark;
	private long storeId;

	private String title;
	private String content;
	private long beauticianId;
	private String beauticianName;
	private long noticeTime;//提醒时间
	private String leaguerId;
	private int statu;//0 新建；1未处理；2已处理
	
	private long createdTime;
	private long lastUpdateTime;
	private int ver;
	
	
	public static ScheduleRobotData newInstance(int mark){
		ScheduleRobotData data = new ScheduleRobotData();
		data.mark = mark;
		data.title = "待办事项"+mark;
		data.beauticianId = RandomUtils.nextLong(0, 5);
		data.beauticianName  = "医美师"+mark;
		data.noticeTime = System.currentTimeMillis() - RandomUtils.nextLong(3600 * 1000, 3600 * 1000 * 24);
		
		return data;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getBeauticianId() {
		return beauticianId;
	}
	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}
	public String getBeauticianName() {
		return beauticianName;
	}
	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}
	public long getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(long noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getLeaguerId() {
		return leaguerId;
	}
	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getVer() {
		return ver;
	}
	public void setVer(int ver) {
		this.ver = ver;
	}
	
	
	
}
