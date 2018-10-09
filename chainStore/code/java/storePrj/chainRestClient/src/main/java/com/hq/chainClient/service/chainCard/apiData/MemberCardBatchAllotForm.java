package com.hq.chainClient.service.chainCard.apiData;

import java.util.ArrayList;
import java.util.List;

public class MemberCardBatchAllotForm {
	private List<MemberCardAllotForm> memberCardAllotForms = new ArrayList<MemberCardAllotForm>();

	public static MemberCardBatchAllotForm newInstance() {
		return new MemberCardBatchAllotForm();
	}

	public List<MemberCardAllotForm> getMemberCardAllotForms() {
		return memberCardAllotForms;
	}

	public void setMemberCardAllotForms(List<MemberCardAllotForm> memberCardAllotForms) {
		this.memberCardAllotForms = memberCardAllotForms;
	}
}
