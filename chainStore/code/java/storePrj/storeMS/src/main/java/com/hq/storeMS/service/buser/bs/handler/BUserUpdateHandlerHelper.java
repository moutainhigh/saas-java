package com.hq.storeMS.service.buser.bs.handler;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.apiData.BUserBindingWeChatForm;
import com.hq.storeMS.service.buser.apiData.BUserChangePasswordApiData;
import com.hq.storeMS.service.buser.apiData.BUserUnBindingWeChatForm;
import com.hq.storeMS.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeMS.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.storeMS.service.buser.apiData.BUserUpdateType;
import com.hq.storeMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserUpdateHandlerHelper {

	public static BUserUpdateHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(BUserUpdateHandlerHelper.class);
	}
	
	private Map<BUserUpdateType, IBUserInfoHandler> handleMapper = new HashMap<BUserUpdateType, IBUserInfoHandler>();

	public BUserUpdateHandlerHelper() {
		handleMapper.put(BUserUpdateType.UnBindingWeChat, new IBUserInfoHandler(){
			@Override
			public OperateTips update(BUserUpdateApiForm formInfo){
				BUserUnBindingWeChatForm inputData = formInfo.getUnBindingWeChatForm();
				BUserAuthUtils.getInstance().checkBuser(inputData.getBuserId());
				return BUserUpdateMgr.getInstance().unBindingWeChat(inputData);
			}
		});
		handleMapper.put(BUserUpdateType.BindingWeChat, new IBUserInfoHandler(){
			@Override
			public OperateTips update(BUserUpdateApiForm formInfo){
				BUserBindingWeChatForm inputData = formInfo.getBindingWeChatForm();
				BUserAuthUtils.getInstance().checkBuser(inputData.getBuserId());
				return BUserUpdateMgr.getInstance().bindingWeChat(inputData);
			}
		});
		handleMapper.put(BUserUpdateType.updateInfo, new IBUserInfoHandler(){
			@Override
			public OperateTips update(BUserUpdateApiForm formInfo){
				BUserUpdateInfoApiData inputData = formInfo.getUpdateInfoData();
				BUserAuthUtils.getInstance().checkBuser(inputData.getBuserId());
				return BUserUpdateMgr.getInstance().updateInfo(inputData);
			}
		});
		handleMapper.put(BUserUpdateType.changePassword, new IBUserInfoHandler(){
			@Override
			public OperateTips update(BUserUpdateApiForm formInfo){
				BUserChangePasswordApiData inputData = formInfo.getChangePasswordData();
				BUserAuthUtils.getInstance().checkBuser(inputData.getBuserId());
				return BUserUpdateMgr.getInstance().changePassword(inputData);
			}
		});
		handleMapper.put(BUserUpdateType.VipRecharge, new IBUserInfoHandler(){
			@Override
			public OperateTips update(BUserUpdateApiForm formInfo){
				return BUserVipMgr.getInstance().vipRecharge(formInfo.getVipRechargeData());
			}
		});
		handleMapper.put(BUserUpdateType.UpdateVipType, new IBUserInfoHandler(){
			@Override
			public OperateTips update(BUserUpdateApiForm formInfo){
				return BUserVipMgr.getInstance().updateVipType(formInfo.getUpdateVipTypeData());
			}
		});
	}

	public OperateTips update(BUserUpdateApiForm formInfo) {
		BUserUpdateType updateType = BUserUpdateType.valueOf(formInfo.getUpdateType());
		IBUserInfoHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}

}
