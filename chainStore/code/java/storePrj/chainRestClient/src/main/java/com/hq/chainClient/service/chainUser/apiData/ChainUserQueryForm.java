package com.hq.chainClient.service.chainUser.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.chainClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class ChainUserQueryForm {
	private long chainId;
	private String phoneOrName;
	private int roleId;
	private Set<Long> chainUserIds = new HashSet<Long>();
	private Set<Integer> crossClerks = new HashSet<Integer>();

	private int pageItemCount;
	private int pageNo;

	public static ChainUserQueryForm newInstance() {
		return new ChainUserQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap data = ReqMap.newInstance();
		data.add("chainId", chainId).add("phoneOrName", phoneOrName).add("roleId", roleId)
				.add("chainUserIds", StringUtils4Client.join(chainUserIds, ","))
				.add("crossClerks", StringUtils4Client.join(crossClerks, ","));
		return data;
	}

	public long getChainId() {
		return chainId;
	}

	public ChainUserQueryForm setChainId(long chainId) {
		this.chainId = chainId;
		return this;
	}

	public String getPhoneOrName() {
		return phoneOrName;
	}

	public ChainUserQueryForm setPhoneOrName(String phoneOrName) {
		this.phoneOrName = phoneOrName;
		return this;
	}

	public int getRoleId() {
		return roleId;
	}

	public ChainUserQueryForm setRoleId(int roleId) {
		this.roleId = roleId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ChainUserQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ChainUserQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Set<Long> getChainUserIds() {
		return chainUserIds;
	}

	public ChainUserQueryForm setChainUserIds(Set<Long> chainUserIds) {
		this.chainUserIds = chainUserIds;
		return this;
	}

	public Set<Integer> getCrossClerks() {
		return crossClerks;
	}

	public ChainUserQueryForm setCrossClerks(Set<Integer> crossClerks) {
		this.crossClerks = crossClerks;
		return this;
	}

}
