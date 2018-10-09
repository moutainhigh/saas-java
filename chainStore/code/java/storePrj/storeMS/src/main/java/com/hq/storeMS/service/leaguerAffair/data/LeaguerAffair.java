package com.hq.storeMS.service.leaguerAffair.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "leaguerAffair")
public class LeaguerAffair {
	@Id
	private String id;

	private long storeId;
	private String leaguerId;

	private long archivesIndex = 0L;
	private long alarmClockIndex = 0L;

	private Set<String> membershipCardIds = new HashSet<String>();// 会员卡绑卡的信息
	private Set<String> discountCardIds = new HashSet<String>();// 优惠卷绑卡的信息
	private Set<String> productCardIds = new HashSet<String>();// 耗卡绑卡的信息
	private Map<Long, Archives> archivesMap = new HashMap<Long, Archives>();// 档案信息
	private Map<Long, AlarmClock> alarmClockMap = new HashMap<Long, AlarmClock>();// 闹钟提醒

	// 数据拆分标识
	private int splitMark;
	private long lastUpdateTime;
	private long createdTime;
	private long ver;

	public static LeaguerAffair newInstance() {
		LeaguerAffair leaguerAffair = new LeaguerAffair();
		long time = System.currentTimeMillis();
		leaguerAffair.createdTime = time;
		leaguerAffair.lastUpdateTime = time;
		return leaguerAffair;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public Set<String> getMembershipCardIds() {
		return membershipCardIds;
	}

	public void setMembershipCardIds(Set<String> membershipCardIds) {
		this.membershipCardIds = membershipCardIds;
	}

	public Set<String> getDiscountCardIds() {
		return discountCardIds;
	}

	public void setDiscountCardIds(Set<String> discountCardIds) {
		this.discountCardIds = discountCardIds;
	}

	public Map<Long, Archives> getArchivesMap() {
		return archivesMap;
	}

	public void setArchivesMap(Map<Long, Archives> archivesMap) {
		this.archivesMap = archivesMap;
	}

	public Map<Long, AlarmClock> getAlarmClockMap() {
		return alarmClockMap;
	}

	public void setAlarmClockMap(Map<Long, AlarmClock> alarmClockMap) {
		this.alarmClockMap = alarmClockMap;
	}

	public long getArchivesIndex() {
		return archivesIndex;
	}

	public void setArchivesIndex(long archivesIndex) {
		this.archivesIndex = archivesIndex;
	}

	public long getAlarmClockIndex() {
		return alarmClockIndex;
	}

	public void setAlarmClockIndex(long alarmClockIndex) {
		this.alarmClockIndex = alarmClockIndex;
	}

	public Set<String> getProductCardIds() {
		return productCardIds;
	}

	public void setProductCardIds(Set<String> productCardIds) {
		this.productCardIds = productCardIds;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
	}

}
