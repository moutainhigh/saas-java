package com.hq.storeMS.service.opLog.data;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.index.Indexed;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "opLog")
public class OpLog {
	@Id
	private long id;
	@Indexed
	private long storeId;
	// 操作人
	private String buserName;
	// 会员名称、产品名称、员工名称
	private String newName;
	// OpLogTypeEnum
	private int type;
	// 操作内容
	private String content;

	@Indexed
	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static OpLog newInstance() {
		OpLog data = new OpLog();
		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
	}

	public static OpLog newInstance(long storeIdP, String newNameP, OpLogTypeEnum logTypeEnum, String contentP) {
		OpLog data = newInstance();
		data.storeId = storeIdP;
		data.newName = newNameP;
		data.content = contentP;
		data.type = logTypeEnum.ordinal();
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getBuserName() {
		return buserName;
	}

	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
