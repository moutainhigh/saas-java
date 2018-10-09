package com.hq.chainStore.service.leaguerAffair.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "leaguerAffair")
public class LeaguerAffair implements IntfSynData{
	@Id
	private String id;

	private long storeId;
	private String leaguerId;
	
	private long archivesIndex = 0L;
	private long alarmClockIndex = 0L;
	
	private Set<String> membershipCardIds = new HashSet<String>();//会员卡绑卡的信息
	private Set<String> discountCardIds = new HashSet<String>();//优惠卷绑卡的信息
	private Set<String> productCardIds = new HashSet<String>();//耗卡绑卡的信息
	private Map<Long, Archives> archivesMap = new HashMap<Long, Archives>();//档案信息
	private Map<Long, AlarmClock> alarmClockMap = new HashMap<Long, AlarmClock>();//闹钟提醒

	private long lastUpdateTime;
	private long createdTime;
	private long ver;

	public static LeaguerAffair newInstance() {
		LeaguerAffair leaguerAffair = new LeaguerAffair();
		return leaguerAffair;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	@Override
	public String toString() {
		return "LeaguerAffair [id=" + id + ", storeId=" + storeId
				+ ", leaguerId=" + leaguerId + ", archivesIndex="
				+ archivesIndex + ", alarmClockIndex=" + alarmClockIndex
				+ ", membershipCardIds=" + membershipCardIds
				+ ", discountCardIds=" + discountCardIds + ", archivesMap="
				+ archivesMap + ", alarmClockMap=" + alarmClockMap
				+ ", lastUpdateTime=" + lastUpdateTime + ", createdTime="
				+ createdTime + ", ver=" + ver + "]";
	}

}
