package com.hq.storeMS.service.buser.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeMS.common.constants.ServerConstants;

public class BUserCommQueryForm {
	/********************** 模糊条件 **********************/
	// 手机号或名称
	private String phoneOrName;

	/********************** 精确条件 **********************/
	private long buserId;
	private long chainId;
	private long phone;
	private String priAccountNum;
	private String wxUnionId;
	private String name;
	private int vipType;
	private Set<Integer> roleSet = new HashSet<Integer>();
	private Set<Long> buserIds = new HashSet<Long>();
	// 对应最小注册时间
	private long minTime;
	// 对应最大注册时间
	private long maxTime;

	private int pageItemCount;
	private int pageNo;

	public static BUserCommQueryForm newInstance() {
		BUserCommQueryForm data = new BUserCommQueryForm();
		data.vipType = ServerConstants.NONE_TYPE;
		data.pageNo = 1;
		return data;
	}

	public String getListId() {
		return String.valueOf(chainId);
	}

	public long getChainId() {
		return chainId;
	}

	public BUserCommQueryForm setChainId(long chainId) {
		this.chainId = chainId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public BUserCommQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public BUserCommQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getPhoneOrName() {
		return phoneOrName;
	}

	public BUserCommQueryForm setPhoneOrName(String phoneOrName) {
		this.phoneOrName = phoneOrName;
		return this;
	}

	public long getPhone() {
		return phone;
	}

	public BUserCommQueryForm setPhone(long phone) {
		this.phone = phone;
		return this;
	}

	public Set<Integer> getRoleSet() {
		return roleSet;
	}

	public BUserCommQueryForm setRoleSet(Set<Integer> roleSet) {
		this.roleSet = roleSet;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public BUserCommQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public BUserCommQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public String getName() {
		return name;
	}

	public BUserCommQueryForm setName(String name) {
		this.name = name;
		return this;
	}

	public int getVipType() {
		return vipType;
	}

	public BUserCommQueryForm setVipType(int vipType) {
		this.vipType = vipType;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public BUserCommQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public BUserCommQueryForm setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
		return this;
	}

	public String getPriAccountNum() {
		return priAccountNum;
	}

	public BUserCommQueryForm setPriAccountNum(String priAccountNum) {
		this.priAccountNum = priAccountNum;
		return this;
	}

	public String getWxUnionId() {
		return wxUnionId;
	}

	public BUserCommQueryForm setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
		return this;
	}

}
