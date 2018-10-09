package com.hq.customerMS.service.cuser.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.customerMS.service.common.EntityState;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "cuser")
public class CUser {
	@Id
	private long id;

	// 系统生成的内部账号
	@IndexField(groupName="priAccountNum")
	private String priAccountNum;

	// 微信用户统一标识
	@IndexField(groupName="wxUnionId")
	private String wxUnionId;
	// 名称
	@IndexField(groupName="name")
	private String name;
	// 手机号
	@IndexField(groupName="phone")
	private long phone;
	@SynIgnoreField
	// 密码
	private String password;
	// 头像
	private String headImg;
	// 性别
	private int gender;
	// 年龄
	private int age;

	// 收货人地址ID
	private long addressIdIndex = 0L;
	// 收货人地址集合
	private Map<String, ReceiverAddress> addressMap = new HashMap<String, ReceiverAddress>();

	// 给密码加点盐
	@SynIgnoreField
	private String salt;

	// 用户关联店铺会员id列表
	private Set<String> leaguerIdSet = new HashSet<String>();
	// 用户关联的店铺id列表
	private Set<Long> storeIdSet = new HashSet<Long>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	// 实体状态
	private int state;

	public static CUser newInstance() {
		CUser user = new CUser();
		user.state = EntityState.Normal.ordinal();
		long curTime = System.currentTimeMillis();

		user.createdTime = curTime;
		user.lastUpdateTime = curTime;
		return user;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void addLeaguerId(String leaguerId) {
		this.leaguerIdSet.add(leaguerId);
	}

	public Set<String> getLeaguerIdSet() {
		return leaguerIdSet;
	}

	public void setLeaguerIdSet(Set<String> leaguerIdSet) {
		this.leaguerIdSet = leaguerIdSet;
	}

	public void addStoreId(long storeId) {
		this.storeIdSet.add(storeId);
	}

	public Set<Long> getStoreIdSet() {
		return storeIdSet;
	}

	public void setStoreIdSet(Set<Long> storeIdSet) {
		this.storeIdSet = storeIdSet;
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

	public void incrVer() {
		this.ver = ver + 1;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCredentialsSalt() {
		return this.priAccountNum + this.salt;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, ReceiverAddress> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String, ReceiverAddress> addressMap) {
		this.addressMap = addressMap;
	}

	public long getAddressIdIndex() {
		return addressIdIndex;
	}

	public void setAddressIdIndex(long addressIdIndex) {
		this.addressIdIndex = addressIdIndex;
	}

	public String getPriAccountNum() {
		return priAccountNum;
	}

	public void setPriAccountNum(String priAccountNum) {
		this.priAccountNum = priAccountNum;
	}

	public String getWxUnionId() {
		return wxUnionId;
	}

	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}

}
