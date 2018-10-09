package com.hq.storeMS.service.euser.bs;

import java.util.List;

import com.hq.storeMS.service.euser.apiData.EUserUpdateCountData;
import com.hq.storeMS.service.euser.data.EUser;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * @ClassName: EUserMgr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年1月19日 下午2:49:39
 * 
 */
public class EUserMgr {

	public static EUserMgr getInstance() {
		return HotSwap.getInstance().getSingleton(EUserMgr.class);
	}

	public EUser createEUser(EUser euser) {
		EUserDataHolder.getInstance().addAndReturnId(euser);
		return euser;
	}

	public EUser updateCount(EUserUpdateCountData updateCountData) {
		EUser euser = get(updateCountData.getEuserId());
		updateCountData.toEUser(euser);
		euser.setCount(updateCountData.getCount());
		updateEUser(euser);
		return euser;
	}

	public EUser get(long euserId) {
		EUser euser = EUserDataHolder.getInstance().get(euserId);
		return euser;
	}

	public void updateEUser(EUser target) {
		EUserDataHolder.getInstance().update(target);
	}

	public EUser findByPhone(long phone) {
		EUser euser = EUserDataHolder.getInstance().findByPhone(phone);
		return euser;
	}

	public List<EUser> getList(int pageItemCount, int pageNo) {
		return EUserDataHolder.getInstance().getList(pageItemCount, pageNo);
	}

}
