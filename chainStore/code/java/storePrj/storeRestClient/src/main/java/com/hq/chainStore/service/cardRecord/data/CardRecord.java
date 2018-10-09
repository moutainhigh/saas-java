package com.hq.chainStore.service.cardRecord.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "cardRecord")
public class CardRecord implements IntfSynData {
	@Id
	private long id;
	
	private long storeId;
	private String cardId;//卡片ID
	private String leaguerId;//客户
	
	private long bindingTime;//绑卡时间
	private long releaseTime;//解绑时间
	private long endTime;//结束时间
	private int count;//使用次数
	//耗卡的信息  prdId:userCount
	private Map<Long, Long> useCountMap = new HashMap<Long, Long>();
	
	private int status;//是否有效 
	
	private long createdTime;
	private long lastUpdateTime;
	private long ver;
	
	public static CardRecord newInstance(){
		CardRecord data = new CardRecord();
		return data;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(long bindingTime) {
		this.bindingTime = bindingTime;
	}

	public long getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public Map<Long, Long> getUseCountMap() {
		return useCountMap;
	}

	public void setUseCountMap(Map<Long, Long> useCountMap) {
		this.useCountMap = useCountMap;
	}
}
