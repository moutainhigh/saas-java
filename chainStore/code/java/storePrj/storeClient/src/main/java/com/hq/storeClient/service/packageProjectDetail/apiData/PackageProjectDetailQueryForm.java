package com.hq.storeClient.service.packageProjectDetail.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeClient.common.utils.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class PackageProjectDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 状态
	private Set<Integer> status = new HashSet<Integer>();
	// 名称或者编号
	private String nameOrNumber;
	// 分类
	private String typeId;

	private int pageItemCount;
	private int pageNo;

	public static PackageProjectDetailQueryForm newInstance() {
		return new PackageProjectDetailQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("storeId", storeId)
				.add("status", StringUtils4Client.join(status, ",")).add("typeId", typeId)
				.add("cardNameOrNumber", nameOrNumber);
		return reqMap;
	}

	public long getMinTime() {
		return minTime;
	}

	public PackageProjectDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public PackageProjectDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public PackageProjectDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public PackageProjectDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public PackageProjectDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public PackageProjectDetailQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}

	public Set<Integer> getStatus() {
		return status;
	}

	public PackageProjectDetailQueryForm setStatus(Set<Integer> status) {
		this.status = status;
		return this;
	}

	public String getNameOrNumber() {
		return nameOrNumber;
	}

	public PackageProjectDetailQueryForm setNameOrNumber(String nameOrNumber) {
		this.nameOrNumber = nameOrNumber;
		return this;
	}
}
