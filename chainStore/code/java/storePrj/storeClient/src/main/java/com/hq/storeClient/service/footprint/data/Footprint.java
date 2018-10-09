package com.hq.storeClient.service.footprint.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 足迹实体
 * 冗余buserId与dynamicType 都是从动态过来的信息
 * 
 * @author kevin
 *
 */
@SynClass
@Table(name = "footprint")
public class Footprint {
	@Id
	private long id;

	// 动态ID
	private long dynamicId;
	// 动态类型 DynamicTypeEnum
	private int dynamicType;
	// B端用户 buserId
	private long buserId;
	// 浏览的C端用户
	private long cuserId;

	@IndexField
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static Footprint newInstance() {
		Footprint data = new Footprint();
		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
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

	public long getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(long dynamicId) {
		this.dynamicId = dynamicId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
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

	public int getDynamicType() {
		return dynamicType;
	}

	public void setDynamicType(int dynamicType) {
		this.dynamicType = dynamicType;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

}
