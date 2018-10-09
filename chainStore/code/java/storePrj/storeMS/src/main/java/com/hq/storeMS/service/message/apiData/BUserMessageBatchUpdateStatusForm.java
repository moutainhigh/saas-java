package com.hq.storeMS.service.message.apiData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BUserMessageBatchUpdateStatusForm {
	private Set<Long> messageIds = new HashSet<Long>();
	private int status;

	public static BUserMessageBatchUpdateStatusForm newInstance() {
		return new BUserMessageBatchUpdateStatusForm();
	}
	
	public List<BUserMessageUpdateStatusForm> toUpdateStatusForms(){
		List<BUserMessageUpdateStatusForm> list = new ArrayList<BUserMessageUpdateStatusForm>();
		for (Long id : messageIds) {
			list.add(BUserMessageUpdateStatusForm.newInstance(id, status));
		}
		return list;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Long> getMessageIds() {
		return messageIds;
	}

	public void setMessageIds(Set<Long> messageIds) {
		this.messageIds = messageIds;
	}

}
