package com.hq.storeClient.service.buserRole.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "buserRole")
public class BuserRole {
	// cuserId
	@Id
	private long id;
	private long buserId;

	// 当前会员等级的内容
	private VipContent vipContent = new VipContent();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static BuserRole newInstance() {
		BuserRole data = new BuserRole();
		return data;
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

}
