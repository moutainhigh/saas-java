package com.hq.customerRestClient.service.cuser.apiData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.customerRestClient.service.cuser.data.CUser;
import com.hq.customerRestClient.service.cuser.data.ReceiverAddress;
import com.zenmind.common.beanCopy.FastBeanCopyer;

/**
 * storeMS 平台可以直接修改cuser绝大多数的属性
 * @author kevin
 *
 */
public class CuserUpdate4Ms {
	private long id;
	// 系统生成的内部账号
	private String priAccountNum;
	// 微信用户统一标识
	private String wxUnionId;
	// 名称
	private String name;
	// 手机号
	private long phone;
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
	// 用户关联店铺会员id列表
	private Set<String> leaguerIdSet = new HashSet<String>();
	// 用户关联的店铺id列表
	private Set<Long> storeIdSet = new HashSet<Long>();


	public static CuserUpdate4Ms newInstance() {
		CuserUpdate4Ms data = new CuserUpdate4Ms();
		return data;
	}

	public void update(CUser user) {
		FastBeanCopyer.getInstance().copy(this, user);
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

	public long getAddressIdIndex() {
		return addressIdIndex;
	}

	public void setAddressIdIndex(long addressIdIndex) {
		this.addressIdIndex = addressIdIndex;
	}

	public Map<String, ReceiverAddress> getAddressMap() {
		return addressMap;
	}

	public void setAddressMap(Map<String, ReceiverAddress> addressMap) {
		this.addressMap = addressMap;
	}

	public Set<String> getLeaguerIdSet() {
		return leaguerIdSet;
	}

	public void setLeaguerIdSet(Set<String> leaguerIdSet) {
		this.leaguerIdSet = leaguerIdSet;
	}

	public Set<Long> getStoreIdSet() {
		return storeIdSet;
	}

	public void setStoreIdSet(Set<Long> storeIdSet) {
		this.storeIdSet = storeIdSet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
