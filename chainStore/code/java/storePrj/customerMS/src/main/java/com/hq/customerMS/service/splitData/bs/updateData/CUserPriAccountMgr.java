package com.hq.customerMS.service.splitData.bs.updateData;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.customerMS.service.cuser.bs.CUserDataHolder;
import com.hq.customerMS.service.cuser.data.CUser;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 旧数据 phone作为唯一的账号， 升级priAccountNum为主账号
 * @author kevin
 *
 */
public class CUserPriAccountMgr {

	public static CUserPriAccountMgr getInstance(){
		return HotSwap.getInstance().getSingleton(CUserPriAccountMgr.class);
	}

	public void updateData() {
		List<CUser> list = getCUserDatas();
		if(CollectionUtils.isNotEmpty(list)) {
			for (CUser user : list) {
				update(user);
			}
		}
	}
	
	private void update(CUser user) {
		user.setPriAccountNum(user.getPhone()+"");
		CUserDataHolder.getInstance().updpate(user);
	}
	
	private List<CUser> getCUserDatas(){
		return CUserDataHolder.getInstance().findPriAccountNumNotExists();
	}
}
