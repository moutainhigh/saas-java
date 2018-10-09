package com.hq.experienceData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hq.experienceData.service.DataConstants;
import com.hq.experienceData.service.RandomUtils;

public class TLeaguerAffair {
	private long id;
	private long beauticianId;

	private String beauticianName;
	private String title;
	private String content;
	private int hours;

	public TLeaguerAffair() {
	}

	public TLeaguerAffair(String beauticianName, String title, String content,
			int hours) {
		this.beauticianName = beauticianName;
		this.title = title;
		this.content = content;
		this.hours = hours;
	}

	public TLeaguerAffair(String content) {
		this.content = content;
	}

	public static List<TLeaguerAffair> buildAddAlarmClockList() {
		List<TLeaguerAffair> list = new ArrayList<TLeaguerAffair>();
		list.add(new TLeaguerAffair("医美师", "客户闹钟1", "内容1", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟2", "内容2", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟3", "内容3", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟4", "内容4", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟5", "内容5", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟6", "内容1", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟7", "内容2", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟8", "内容3", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟9", "内容4", RandomUtils.nextInt(1, 5)));
		list.add(new TLeaguerAffair("医美师", "客户闹钟10", "内容5", RandomUtils.nextInt(1, 5)));
		Collections.shuffle(list);
		return list.subList(0, DataConstants.OTHER_COUNT);
	}

	public static List<TLeaguerAffair> buildAddArchivesList() {
		List<TLeaguerAffair> list = new ArrayList<TLeaguerAffair>();
		list.add(new TLeaguerAffair("档案内容1"));
		list.add(new TLeaguerAffair("档案内容2"));
		list.add(new TLeaguerAffair("档案内容3"));
		list.add(new TLeaguerAffair("档案内容4"));
		list.add(new TLeaguerAffair("档案内容5"));
		list.add(new TLeaguerAffair("档案内容6"));
		list.add(new TLeaguerAffair("档案内容7"));
		list.add(new TLeaguerAffair("档案内容8"));
		list.add(new TLeaguerAffair("档案内容9"));
		list.add(new TLeaguerAffair("档案内容10"));
		Collections.shuffle(list);
		return list.subList(0, DataConstants.OTHER_COUNT);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
