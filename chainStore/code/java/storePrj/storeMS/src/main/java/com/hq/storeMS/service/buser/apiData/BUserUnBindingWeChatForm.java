package com.hq.storeMS.service.buser.apiData;

import com.hq.storeMS.service.buser.data.BUser;

/**
 * 解绑微信UnionId
 * @author kevin
 *
 */
public class BUserUnBindingWeChatForm {
	private long buserId;

	public static BUserUnBindingWeChatForm newInstance() {
		BUserUnBindingWeChatForm data = new BUserUnBindingWeChatForm();
		return data;
	}
	
	public void updateBuser(BUser buser) {
		buser.setWxUnionId("");
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

}
