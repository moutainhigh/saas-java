package com.hq.chainStore.service.message.apiData;

import java.util.HashSet;
import java.util.Set;

import com.hq.common.StringUtils4Client;
import com.zenmind.dao.rest.ReqMap;

public class MessageQueryForm {
	private long minTime;
	private long maxTime;

	// 类型 TriggerTypeEnum
	private Set<Integer> messageType = new HashSet<Integer>();
	// MessageStatusEnum
	private int status;

	private int pageItemCount;
	private int pageNo;

	public static MessageQueryForm newInstance() {
		return new MessageQueryForm();
	}

	public ReqMap toReqMap() {
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("minTime", minTime).add("maxTime", maxTime).add("status", status).add("messageType",
				StringUtils4Client.join(messageType, ","));
		return reqMap;
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
