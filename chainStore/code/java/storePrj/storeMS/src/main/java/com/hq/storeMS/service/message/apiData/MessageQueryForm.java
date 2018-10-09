package com.hq.storeMS.service.message.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeMS.common.util.AppUtils;

public class MessageQueryForm {
	private long minTime;
	private long maxTime;
	private long buserId;

	// 类型 TriggerTypeEnum
	private Set<Integer> messageType = new HashSet<Integer>();
	// MessageStatusEnum
	private int status;

	private int pageItemCount;
	private int pageNo;

	public static MessageQueryForm newInstance() {
		return new MessageQueryForm();
	}

	public String getListId() {
		return AppUtils.joinByUnderline(minTime, maxTime, buserId);
	}

	public long getMinTime() {
		return minTime;
	}

	public MessageQueryForm setMinTime(long minTime) {
		this.minTime = minTime;
		return this;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public MessageQueryForm setMaxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public long getBuserId() {
		return buserId;
	}

	public MessageQueryForm setBuserId(long buserId) {
		this.buserId = buserId;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public MessageQueryForm setStatus(int status) {
		this.status = status;
		return this;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public MessageQueryForm setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
		return this;
	}

	public int getPageNo() {
		return pageNo;
	}

	public MessageQueryForm setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Set<Integer> getMessageType() {
		return messageType;
	}

	public MessageQueryForm setMessageType(Set<Integer> messageType) {
		this.messageType = messageType;
		return this;
	}
}
