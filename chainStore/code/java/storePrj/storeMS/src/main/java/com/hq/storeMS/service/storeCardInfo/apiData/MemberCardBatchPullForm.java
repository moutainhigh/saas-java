package com.hq.storeMS.service.storeCardInfo.apiData;

import java.util.ArrayList;
import java.util.List;

public class MemberCardBatchPullForm {
	private List<MemberCardPullForm> pullForms = new ArrayList<MemberCardPullForm>();

	public static MemberCardBatchPullForm newInstance() {
		return new MemberCardBatchPullForm();
	}

	public List<MemberCardPullForm> getPullForms() {
		return pullForms;
	}

	public void setPullForms(List<MemberCardPullForm> pullForms) {
		this.pullForms = pullForms;
	}

}
