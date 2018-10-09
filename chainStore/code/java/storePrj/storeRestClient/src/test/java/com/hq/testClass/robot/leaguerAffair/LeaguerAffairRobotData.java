package com.hq.testClass.robot.leaguerAffair;

import org.apache.commons.lang3.RandomUtils;

public class LeaguerAffairRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private String title;
	private String content;
	private long beauticianId;
	private String beauticianName;
	private int hours;

	public static LeaguerAffairRobotData newInstance(int mark) {
		LeaguerAffairRobotData data = new LeaguerAffairRobotData();
		data.title = "title-" + mark;
		data.content = "content-" + mark;
		data.beauticianName = "beauticianName-" + mark;
		data.beauticianId = RandomUtils.nextLong(35L, 100L);
		data.hours = RandomUtils.nextInt(5, 24);
		return data;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
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

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

}
