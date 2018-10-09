package com.hq.storeMS.service.buser.apiData;

import com.hq.storeMS.service.buser.data.BUser;

/**
 * 绑定微信UnionId
 * @author kevin
 *
 */
public class BUserBindingWeChatForm {
	private long buserId;
	private String wxUnionId;

	public static BUserBindingWeChatForm newInstance() {
		BUserBindingWeChatForm data = new BUserBindingWeChatForm();
		return data;
	}
	
	public void updateBuser(BUser buser) {
		buser.setWxUnionId(wxUnionId);
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public String getWxUnionId() {
		return wxUnionId;
	}

	public void setWxUnionId(String wxUnionId) {
		this.wxUnionId = wxUnionId;
	}

}
