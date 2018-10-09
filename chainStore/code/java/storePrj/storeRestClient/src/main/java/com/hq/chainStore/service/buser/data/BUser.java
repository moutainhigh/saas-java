package com.hq.chainStore.service.buser.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainStore.service.common.GenderEnum;
import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "buser")
public class BUser implements IntfSynData {

	@Id
	private long id;

	private String name;
	//手机号码区号
	private String areaCode;

	private long phone;

	private String password;

	private String headImg;

	private int gender;

	private int age;

	// 用户关联的店铺id列表
	private Set<Long> storeIdSet = new HashSet<Long>();
	
	//用户绑定的仪器id
	private long  buserDeviceId;

	private long createdTime;

	private long ver;

	//对应BuserRoleEnum
	private Set<Integer> roleSet = new HashSet<Integer>();
	
	//会员类型 对应StoreVipType中的level   参考StoreVipLevelEnum 初始状态为体验会员
	private int vipType = 0;
	//会员到期时间
	private long expiredTime;
	//商务手机 
	private long businessPhone;

	public static BUser New(long id, String name, long phone) {
		BUser buser = new BUser();
		buser.id = id;
		buser.name = name;
		buser.phone = phone;
		long curTime = System.currentTimeMillis();

		buser.createdTime = curTime;
		return buser;
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

	public void addStoreId(long storeId) {
		this.storeIdSet.add(storeId);
	}

	public Set<Long> getStoreIdSet() {
		return storeIdSet;
	}

	public void setStoreIdSet(Set<Long> storeIdSet) {
		this.storeIdSet = storeIdSet;
	}

	public long getBuserDeviceId() {
		return buserDeviceId;
	}

	public void setBuserDeviceId(long buserDeviceId) {
		this.buserDeviceId = buserDeviceId;
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

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
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

	public GenderEnum getGenderEnum() {
		return GenderEnum.valueOf(gender);
	}

	public void setGenderEnum(GenderEnum gender) {
		this.gender = gender.ordinal();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
	}

	public void addRoleSet(int roleEnum){
		this.roleSet.add(roleEnum);
	}

	public int getVipType() {
		return vipType;
	}

	public void setVipType(int vipType) {
		this.vipType = vipType;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(long expiredTime) {
		this.expiredTime = expiredTime;
	}

	public long getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(long businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
