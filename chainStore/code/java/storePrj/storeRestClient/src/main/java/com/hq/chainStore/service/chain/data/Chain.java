package com.hq.chainStore.service.chain.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "chain")
public class Chain implements IntfSynData {
	@Id
	private long id;

	private long bossId;
	// 编号
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
	private int state;
	private int entityState;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static Chain newInstance() {
		return new Chain();
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

	public long getBossId() {
		return bossId;
	}

	public void setBossId(long bossId) {
		this.bossId = bossId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
