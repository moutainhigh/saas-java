package com.hq.chainMS.service.chain.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "chain")
public class Chain {
	@Id
	private long id;

	private long bossId;
	// 编号
	@IndexField
	private String number;
	// 名称
	private String name;
	// 店铺描述
	private String descript;
	// 区域
	private String area;
	// 地址
	private String address;
	// 联系电话
	private String contactNumber;
	// 联系人
	private String contacts;

	// 已加入连锁店的店铺列表
	private Map<Long, ChainStore> chainStoreMap = new HashMap<Long, ChainStore>();
	// 申请加入连锁店的店铺列表
	private Map<Long, ChainStore> applyStoreMap = new HashMap<Long, ChainStore>();

	// 状态 对应 ChainState
	@IndexField
	private int state;
	private int entityState;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static Chain newInstance() {
		Chain chain = new Chain();
		chain.state = ChainState.Open.ordinal();
		long curTime = System.currentTimeMillis();
		chain.createdTime = curTime;
		chain.lastUpdateTime = curTime;
		return chain;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public void addChainStoreId(Long storeId) {
		chainStoreMap.put(storeId, ChainStore.newInstance(storeId));
	}

	public void removeChainStoreId(Long storeId) {
		chainStoreMap.remove(storeId);
	}

	public Set<Long> takeChainStoreIds() {
		return chainStoreMap.keySet();
	}

	public void addApplyStoreId(Long storeId) {
		applyStoreMap.put(storeId, ChainStore.newInstance(storeId));
	}

	public void removeApplyStoreId(Long storeId) {
		applyStoreMap.remove(storeId);
	}

	public Set<Long> takeApplyStoreIds() {
		return applyStoreMap.keySet();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBossId() {
		return bossId;
	}

	public void setBossId(long bossId) {
		this.bossId = bossId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Map<Long, ChainStore> getChainStoreMap() {
		return chainStoreMap;
	}

	public void setChainStoreMap(Map<Long, ChainStore> chainStoreMap) {
		this.chainStoreMap = chainStoreMap;
	}

	public Map<Long, ChainStore> getApplyStoreMap() {
		return applyStoreMap;
	}

	public void setApplyStoreMap(Map<Long, ChainStore> applyStoreMap) {
		this.applyStoreMap = applyStoreMap;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

}
