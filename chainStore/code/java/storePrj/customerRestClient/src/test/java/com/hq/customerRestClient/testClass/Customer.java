package com.hq.customerRestClient.testClass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.hq.customerRestClient.service.cuser.apiData.CUserLoginApiForm;
import com.hq.customerRestClient.service.cuser.apiData.LoginResp;
import com.hq.customerRestClient.service.cuser.bs.CUserClientMgr;
import com.hq.customerRestClient.service.cuser.data.CUser;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class Customer {
	private List<Long> storeIds = new ArrayList<Long>();
	private long storeId;
	private CUser user;

	private long phone;
	private String password;

	public static Customer newCustomer(long phoneP, String passwordP) {
		Customer boss = new Customer();
		boss.phone = phoneP;
		boss.password = passwordP;
		return boss;
	}

	public boolean login() {
		CUserLoginApiForm loginForm = CUserLoginApiForm.newInstance();
		loginForm.setPhone(phone);
		loginForm.setPassword(password);
		RestResp restResp = CUserClientMgr.getInstance().login(loginForm);

		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		user = loginResp.getCuser();
		if(!CollectionUtils.isEmpty(user.getStoreIdSet())) {
			storeIds.addAll(user.getStoreIdSet());
			storeId = storeIds.get(0);
		}
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);
		return true;
	}

	public long getId() {
		return user.getId();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public List<Long> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
	}

}
