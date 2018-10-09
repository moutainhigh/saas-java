package com.hq.storeClient.testClass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.hq.storeClient.service.buser.apiData.BUserLoginApiForm;
import com.hq.storeClient.service.buser.apiData.LoginResp;
import com.hq.storeClient.service.buser.bs.BUserClientMgr;
import com.hq.storeClient.service.buser.data.BUser;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class Boss {
	private List<Long> storeIds = new ArrayList<Long>();
	private BUser user;

	private long phone;
	private String password;

	public static Boss newBoss(long phoneP, String passwordP) {
		Boss boss = new Boss();
		boss.phone = phoneP;
		boss.password = passwordP;
		return boss;
	}

	public boolean login() {
		BUserLoginApiForm loginForm = BUserLoginApiForm.newInstance();
		loginForm.setPhone(phone);
		loginForm.setPassword(password);
		RestResp restResp = BUserClientMgr.getInstance().login(loginForm);

		LoginResp loginResp = JsonUtil.getInstance().fromJson(restResp.gettJson(), LoginResp.class);
		user = loginResp.getBuser();
		if(!CollectionUtils.isEmpty(user.getStoreIdSet())) {
			storeIds.addAll(user.getStoreIdSet());
		}
		String token = loginResp.getToken();
		AccessTokenMgr.getInstance().putToken(user.getId(), token);
		return true;
	}

	public long getId() {
		return user.getId();
	}

	public long getStoreId() {
		return storeIds.get(0);
	}

}
