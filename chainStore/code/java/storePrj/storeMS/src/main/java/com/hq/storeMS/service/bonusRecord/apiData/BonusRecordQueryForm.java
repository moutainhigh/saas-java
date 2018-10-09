package com.hq.storeMS.service.bonusRecord.apiData;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.AppUtils;

public class BonusRecordQueryForm {
	private long storeId;
	private long maxTime;
	private long minTime;
	private long orderId;
	
	private String buserName;
	private Set<Integer> statusSet = new HashSet<Integer>();
	private String buyId;

	private int pageItemCount;
	private int pageNo;

	public static BonusRecordQueryForm newInstance() {
		return new BonusRecordQueryForm();
	}
	
	public static BonusRecordQueryForm newInstance(long orderIdP, long storeIdP) {
		BonusRecordQueryForm form = newInstance();
		form.orderId = orderIdP;
		form.storeId = storeIdP;
		return form;
	}

	public String getListId() {
		return AppUtils.joinByUnderline(storeId, maxTime, minTime, orderId);
	}

	public BonusRecordQueryForm setStatus(String status) {
		if (StringUtils.isNoneBlank(status)) {
			String[] ss = status.split(",");
			for (String s : ss) {
				statusSet.add(Integer.valueOf(s));
			}
		}
		return this;
	}

	public long getStoreId() {
		return storeId;
	}

	public BonusRecordQueryForm setStoreId(long storeId) {
		this.storeId = storeId;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public BonusRecordQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getMinTime() {
		return minTime;
	}

	public BonusRecordQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getOrderId() {
		return orderId;
	}

	public BonusRecordQueryForm setOrderId(long orderId) {
		this.orderId = orderId;
		return this;
	}

	public Set<Integer> getStatusSet() {
		return statusSet;
	}

	public BonusRecordQueryForm setStatusSet(Set<Integer> statusSet) {
		this.statusSet = statusSet;
		return this;
	}

	public String getBuyId() {
		return buyId;
	}

	public BonusRecordQueryForm setBuyId(String buyId) {
		this.buyId = buyId;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public BonusRecordQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public BonusRecordQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public String getBuserName() {
		return buserName;
	}

	public BonusRecordQueryForm setBuserName(String buserName) {
		this.buserName = buserName;
		return this;
	}

}
