package com.hq.storeMS.service.vipRechargeRecord.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "vipRechargeRecord")
public class VipRechargeRecord {
	@Id
	private long id;
	// 用户ID
	private long buserId;
	// 会员手机/账号
	@IndexField
	private long phone;
	// 会员姓名
	private String name;
	// 会员类型
	private int vipType;
	// 充值金额
	private float amount;
	@IndexField
	private long createdTime;
	private long ver;

	public static VipRechargeRecord newInstance() {
		VipRechargeRecord vipRechargeRecord = new VipRechargeRecord();
		long currentTimeMillis = System.currentTimeMillis();

		vipRechargeRecord.createdTime = currentTimeMillis;
		return vipRechargeRecord;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	public void incrVer() {
		this.ver = ver + 1;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

}
