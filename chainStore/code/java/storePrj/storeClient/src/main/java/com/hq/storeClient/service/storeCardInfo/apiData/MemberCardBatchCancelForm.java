package com.hq.storeClient.service.storeCardInfo.apiData;

import java.util.ArrayList;
import java.util.List;

public class MemberCardBatchCancelForm {
	private List<MemberCardCancelForm> cancelForms = new ArrayList<MemberCardCancelForm>();

	public static MemberCardBatchCancelForm newInstance() {
		return new MemberCardBatchCancelForm();
	}

	public List<MemberCardCancelForm> getCancelForms() {
		return cancelForms;
	}

	public void setCancelForms(List<MemberCardCancelForm> cancelForms) {
		this.cancelForms = cancelForms;
	}
}
