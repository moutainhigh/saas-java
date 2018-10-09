package com.hq.customerRestClient.service.cuser.apiData;

import com.hq.customerRestClient.service.cuser.data.CUser;

/**
 * storeMS 平台通过号码可以直接添加cuser
 * @author kevin
 *
 */
public class CuserAdd4Ms {
	// 手机号
	private long phone;

	public static CuserAdd4Ms newInstance() {
		CuserAdd4Ms data = new CuserAdd4Ms();
		return data;
	}

	public void toCUser() {
		CUser data = CUser.newInstance();
		data.setPhone(phone);
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

}
