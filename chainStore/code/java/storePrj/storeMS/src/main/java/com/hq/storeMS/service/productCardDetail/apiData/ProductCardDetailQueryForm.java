package com.hq.storeMS.service.productCardDetail.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class ProductCardDetailQueryForm {
	private long minTime;
	private long maxTime;
	private long storeId;

	// 分类
	private String typeId;
	// 卡状态
	private Set<Integer> statusSet = new HashSet<Integer>();
	// 卡名称或者编号
	private String cardNameOrNumber;

	private int pageItemCount;
	private int pageNo;

	public static ProductCardDetailQueryForm newInstance() {
		return new ProductCardDetailQueryForm();
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, storeId);
	}

	public long getMinTime() {
		return minTime;
	}

	public ProductCardDetailQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public ProductCardDetailQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public ProductCardDetailQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public ProductCardDetailQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public ProductCardDetailQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public ProductCardDetailQueryForm setStatus(String status) {
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

	public ProductCardDetailQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
		return this;
	}

	public String getCardNameOrNumber() {
		return cardNameOrNumber;
	}

	public ProductCardDetailQueryForm setCardNameOrNumber(String cardNameOrNumber) {
		this.cardNameOrNumber = cardNameOrNumber;
		return this;
	}

	public String getTypeId() {
		return typeId;
	}

	public ProductCardDetailQueryForm setTypeId(String typeId) {
		this.typeId = typeId;
		return this;
	}
}
