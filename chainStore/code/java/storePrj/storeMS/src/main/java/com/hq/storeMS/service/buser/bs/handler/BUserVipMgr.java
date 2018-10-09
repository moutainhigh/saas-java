package com.hq.storeMS.service.buser.bs.handler;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;

import com.hq.storeMS.service.buser.apiData.BUserUpdateType;
import com.hq.storeMS.service.buser.apiData.BUserUpdateVipTypeData;
import com.hq.storeMS.service.buser.apiData.BUserVipRechargeData;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.hq.storeMS.service.buserRole.bs.BuserRoleMgr;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeVipType.data.StoreVipLevelEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserVipMgr {
	
	public static BUserVipMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserVipMgr.class);
	}
	
	public OperateTips updateVipType(BUserUpdateVipTypeData inputData){
		OperateTips tips = OperateTips.newInstance(false, BUserUpdateType.UpdateVipType.getDescript()+"失败");
		BUser buser = BUserQueryMgr.getInstance().get(inputData.getId());
		if(canSetBuserLevel(buser)){
			int vipType = inputData.getVipType();
			buser.setVipType(vipType);
			if(vipType == StoreVipLevelEnum.InnerBetaUser.ordinal()){
				buser.setExpiredTime(DateUtils.addYears(new Date(), 1).getTime());
			}
			setBuserBossRole(buser);
			BUserModifyMgr.getInstance().update(buser);
			tips.setSuccess(true);
		}
		return tips;
	}

	public OperateTips vipRecharge(BUserVipRechargeData inputData){
		OperateTips tips = OperateTips.newInstance(false, BUserUpdateType.VipRecharge.getDescript()+"失败");
		BUser buser = BUserQueryMgr.getInstance().get(inputData.getBuserId());
		if(canSetBuserLevel(buser)){
			buser.setVipType(inputData.getVipType());
			buser.setExpiredTime(inputData.getExpiredTime());
			setBuserBossRole(buser);
			BUserModifyMgr.getInstance().update(buser);
			//更新会员等级信息
			BuserRoleMgr.getInstance().updateByBUser(buser);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean canSetBuserLevel(BUser buser){
		if(buser!=null){
			BuserRoleEnum role = getBuserRoleEnum(buser.getRoleSet());
			return role == BuserRoleEnum.BOSS || role == BuserRoleEnum.INIT;
		}
		return false;
	}
	
	private BuserRoleEnum getBuserRoleEnum(Set<Integer> roleSet){
		BuserRoleEnum role = BuserRoleEnum.INIT;
		for (Integer idx : roleSet) {
			role = BuserRoleEnum.valueOf(idx);
			break;
		}
		return role;
	}
	
	private void setBuserBossRole(BUser buser){
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(BuserRoleEnum.BOSS.ordinal());
		buser.setRoleSet(roleSet);
	}
}
