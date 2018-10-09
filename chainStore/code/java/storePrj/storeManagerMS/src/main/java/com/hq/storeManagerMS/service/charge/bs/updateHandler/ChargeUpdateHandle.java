package com.hq.storeManagerMS.service.charge.bs.updateHandler;

import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateInfoForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdatePayInfoForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateStatusForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateType;
import com.hq.storeManagerMS.service.charge.bs.ChargeMgr;
import com.hq.storeManagerMS.service.charge.data.Charge;
import com.hq.storeManagerMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeUpdateHandle {

	public static ChargeUpdateHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeUpdateHandle.class);
	}

	public OperateTips updateInfo(ChargeUpdateApiForm updateForm) {
		ChargeUpdateInfoForm inputForm = updateForm.getChargeUpdateInfoForm();
		OperateTips tips = OperateTips.newInstance(false, ChargeUpdateType.UpdateInfo.getMark() + "失败");
		
		Charge charge = ChargeMgr.getInstance().get(inputForm.getId());
		if(charge == null) {
			tips.setTips("记录不存在，更新失败");
			return tips;
		}
		inputForm.updateCharge(charge);
		ChargeMgr.getInstance().updateCharge(charge);
		
//		if(canCascadeUpdate(charge, inputForm)) {
//			BUserMgr.getInstance().updateBuserRoleByCharge(charge);
//		}
		tips.setSuccess(true);
		return tips;
	}
	
	//级联更新BUser的会员等级信息条件： 1、管理后台添加 2、类型或者有效时间发生变化
//	private boolean canCascadeUpdate(Charge charge, ChargeUpdateInfoForm inputForm) {
//		ChargeOriginEnum chargeOriginEnum = ChargeOriginEnum.valueOf(charge.getOrigin());
//		if(chargeOriginEnum != ChargeOriginEnum.ManagerMS) {
//			return false;
//		}
//		
//    	if(charge.getVipLevelId() == inputForm.getVipLevelId() && charge.getExpiredTime() == inputForm.getExpiredTime()) {
//    		return false;
//    	}
//    	return true;
//	}
	
	public OperateTips updateStatus(ChargeUpdateApiForm updateForm) {
		ChargeUpdateStatusForm inputForm = updateForm.getChargeUpdateStatusForm();
		OperateTips tips = OperateTips.newInstance(false, ChargeUpdateType.UpdateStatus.getMark() + "失败");
		
		Charge charge = ChargeMgr.getInstance().get(inputForm.getId());
		if(charge == null) {
			tips.setTips("记录不存在，更新失败");
			return tips;
		}
		
		inputForm.updateCharge(charge);
		ChargeMgr.getInstance().updateCharge(charge);
		tips.setSuccess(true);
		return tips;
	}
	
	public OperateTips updatePayInfo(ChargeUpdateApiForm updateForm) {
		ChargeUpdatePayInfoForm inputForm = updateForm.getChargeUpdatePayInfoForm();
		OperateTips tips = OperateTips.newInstance(false, updateForm.getUpdateTypeEnum().getMark() + "失败");
		
		Charge charge = ChargeMgr.getInstance().get(inputForm.getId());
		if(charge == null) {
			tips.setTips("记录不存在，更新失败");
			return tips;
		}
		
		inputForm.updateCharge(charge);
		ChargeMgr.getInstance().updateCharge(charge);
		tips.setSuccess(true);
		return tips;
	}
	
}
