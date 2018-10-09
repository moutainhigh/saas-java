package com.hq.storeMS.service.packageProjectDetail.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class PackageProjectDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 状态
	private Set<Integer> statusSet = new HashSet<Integer>();
	// 名称或者编号
	private String nameOrNumber;
	// 分类
	private String typeId;

	private int pageItemCount;
	private int pageNo;

	public static PackageProjectDetailQueryForm newInstance() {
		return new PackageProjectDetailQueryForm();
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
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

	public PackageProjectDetailQueryForm setStatus(String status) {
		if (StringUtils.isNoneBlank(status)) {
			String[] ss = status.split(",");
			for (String s : ss) {
				statusSet.add(Integer.valueOf(s));
			}
		}
		return this;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public PackageProjectDetailQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
		return this;
	}

	public String getNameOrNumber() {
		return nameOrNumber;
	}

	public PackageProjectDetailQueryForm setNameOrNumber(String nameOrNumber) {
		this.nameOrNumber = nameOrNumber;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public PackageProjectDetailQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}
}
