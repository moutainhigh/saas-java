package com.hq.storeMS.service.message.apiData;

import java.util.ArrayList;
import java.util.List;

public class BUserMessageAddListForm {
	private List<BUserMessageAddForm> messageAddForms = new ArrayList<BUserMessageAddForm>();
	
	public static BUserMessageAddListForm newInstance() {
		BUserMessageAddListForm data = new BUserMessageAddListForm();
		return data;
	}

	public List<BUserMessageAddForm> getMessageAddForms() {
		return messageAddForms;
	}

	public void setMessageAddForms(List<BUserMessageAddForm> messageAddForms) {
		this.messageAddForms = messageAddForms;
	}

}
