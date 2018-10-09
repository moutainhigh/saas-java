package com.hq.chainMS.service.chainUser.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;
import com.zenmind.dataSyn.annotation.SynIgnoreField;

@SynClass
@Table(name = "chainUser")
public class ChainUser {
	@Id
	private long id;
	// 名称
	private String name;
	// 手机号码区号
	private String areaCode;
	// 手机号码
	@IndexField
	private long phone;
	@SynIgnoreField
	// 密码
	private String password;
	// 头像
	private String headImg;
	// 性别 对应 GenderEnum
	private int gender;
	// 出生日期 格式 yyyyMMdd
	private String birthday;
	// 给密码加点盐
	@SynIgnoreField
	private String salt;
	//是否是跨店员工 CrossClerkEnum
	private int crossClerk;
	// 连锁店、角色、分店的信息
	private Map<Long, ChainStoreUserRelative> chainSUMap = new HashMap<Long, ChainStoreUserRelative>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static ChainUser newInstance() {
		ChainUser data = new ChainUser();
		long curTime = System.currentTimeMillis();
		data.createdTime = curTime;
		data.lastUpdateTime = curTime;
		return data;
	}
	
	public boolean isBoss(long chainId) {
		ChainStoreUserRelative data = chainSUMap.get(chainId);
		return data.getRole() == ChainUserRoleEnum.BOSS.ordinal();
	}
	
	public Set<Long> takeChainIds(){
		return chainSUMap.keySet();
	}
	
	public Set<Long> takeStoreIds(){
		Set<Long> storeIds = new HashSet<Long>();
		Collection<ChainStoreUserRelative> values = chainSUMap.values();
		for (ChainStoreUserRelative val : values) {
			storeIds.addAll(val.getStoreIds());
		}
		return storeIds;
	}
	
	public void incrVer() {
		this.ver = ver + 1;
	}

	public String getCredentialsSalt() {
		return this.phone + this.salt;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Map<Long, ChainStoreUserRelative> getChainSUMap() {
		return chainSUMap;
	}

	public void setChainSUMap(Map<Long, ChainStoreUserRelative> chainSUMap) {
		this.chainSUMap = chainSUMap;
	}

	public int getCrossClerk() {
		return crossClerk;
	}

	public void setCrossClerk(int crossClerk) {
		this.crossClerk = crossClerk;
	}

}
