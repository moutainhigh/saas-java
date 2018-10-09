package com.hq.chainStore.service.schedule.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;


@Table(name = "schedule")
public class Schedule implements IntfSynData {

	@Id
	private long id;
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
	
	public static Schedule newInstance(){
		Schedule schedule = new Schedule();
		long curTime = System.currentTimeMillis();
		
		schedule.statu = ScheduleStatusEnum.NEW.ordinal();
		schedule.createdTime = curTime;
		schedule.lastUpdateTime = curTime;
		
		return schedule;
	}
	
	public void incrVer() {
		this.ver = this.ver + 1;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", storeId=" + storeId + ", title="
				+ title + ", content=" + content + ", beauticianId="
				+ beauticianId + ", beauticianName=" + beauticianName
				+ ", noticeTime=" + noticeTime + ", leaguerId=" + leaguerId
				+ ", statu=" + statu + ", createdTime=" + createdTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", ver=" + ver + "]";
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}



	
}
