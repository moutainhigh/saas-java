package com.hq.storeMS.service.euser.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;


/**
 * @ClassName: EUser
 * @Description: 体验用户实体类
 * @author helen
 * @date 2018年1月19日 下午2:19:09
 */

@SynClass
@Table(name = "euser")
public class EUser {

	@Id
	private long id;
	@IndexField
	private String name;
	@IndexField
	private long phone;
	private int count;// 体验次数
	private long createTime;
	private long lastUpdateTime;
	private int ver;

	public static EUser newInstance() {
		EUser euser = new EUser();
		long curTime = System.currentTimeMillis();
		euser.createTime = curTime;
		euser.lastUpdateTime = curTime;
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

}
