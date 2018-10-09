package com.hq.storeMS.service.buserRole.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "buserRole")
public class BuserRole {
	@Id
	private long id;
	private long buserId;

	// 当前会员等级的内容
	private VipContent vipContent = new VipContent();

	// 上一会员等级的内容
	@SynIgnoreField
	private VipContent preVipContent = new VipContent();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static BuserRole newInstance(long buserId) {
		BuserRole data = new BuserRole();
		data.id = buserId;
		data.buserId = buserId;
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}

	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
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

	public VipContent getVipContent() {
		return vipContent;
	}

	public void setVipContent(VipContent vipContent) {
		this.vipContent = vipContent;
	}

	public VipContent getPreVipContent() {
		return preVipContent;
	}

	public void setPreVipContent(VipContent preVipContent) {
		this.preVipContent = preVipContent;
	}

}
