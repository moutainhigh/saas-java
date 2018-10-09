package com.hq.chainStore.service.euser.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;


@Table(name = "euser")
public class EUser implements IntfSynData {

	@Id
	private long id;
	private String name;
	private long phone;
	private int count;// 体验次数
	private long createTime;
	private long lastUpdateTime;
	private int ver;

	public static EUser newInstance() {
		EUser euser = new EUser();
		euser.createTime = System.currentTimeMillis();
		return euser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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

	public void incrVer() {
		this.ver = ver + 1;
	}
	
	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EUser [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", count=" + count + "]";
	}
	
	
}
