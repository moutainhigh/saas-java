package com.hq.storeManagerMS.service.buser.bs;

import com.hq.storeClient.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeClient.service.buser.apiData.BUserUpdateType;
import com.hq.storeClient.service.buser.apiData.BUserVipRechargeData;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeManagerMS.service.charge.data.Charge;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMgr {
	public static BUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserMgr.class);
	}
	
	public BUser findByPhone(long phone) {
		return BUserDataHolder.getInstance().findByPhone(phone);
	}
	
	public BUser get(long buserId) {
		return BUserDataHolder.getInstance().get(buserId);
	}

	public BUser update(long id, BUserUpdateApiForm updateForm){
		return BUserDataHolder.getInstance().update(id,updateForm);
	}
	
	//根据收费记录， 更新用户的会员等级信息
	public void updateBuserRoleByCharge(Charge target) {
		long buserId = target.getBuserId();
		BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
		vipRechargeData.setBuserId(buserId);
		vipRechargeData.setVipType(target.getVipLevelId());
		vipRechargeData.setExpiredTime(target.getExpiredTime());
		
		BUserUpdateApiForm updateForm = BUserUpdateApiForm.newInstance();
		updateForm.setVipRechargeData(vipRechargeData);
		updateForm.setUpdateType(BUserUpdateType.VipRecharge.ordinal());
		update(buserId, updateForm);
	}
	
}
