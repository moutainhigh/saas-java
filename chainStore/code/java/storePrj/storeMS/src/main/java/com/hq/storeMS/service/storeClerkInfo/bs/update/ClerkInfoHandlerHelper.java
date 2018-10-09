package com.hq.storeMS.service.storeClerkInfo.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddRoleSet2ClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.apiData.HandleApplyClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.HandleGroupApplyClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.RemoveStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveRoleOfClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.SetMonthPayDaysData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.storeMS.service.storeClerkInfo.apiData.UpdateStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkInfoHandlerHelper {

	public static ClerkInfoHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkInfoHandlerHelper.class);
	}

	private Map<StoreClerkInfoUpdateType, IClerkInfoHandler> handleMapper = new HashMap<StoreClerkInfoUpdateType, IClerkInfoHandler>();

	public ClerkInfoHandlerHelper() {
		handleMapper.put(StoreClerkInfoUpdateType.ApplyClerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				return ApplyClerkInfoMgr.getInstance().applyClerk(formInfo.getApplyClerkInfoData());
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.HandleApplyClerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				HandleApplyClerkInfoData inputData = formInfo.getHandleApplyClerkInfoData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return ApplyClerkInfoMgr.getInstance().handleApplyClerk(inputData);
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.HandleGroupApplyClerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				HandleGroupApplyClerkData inputData = formInfo.getHandleGroupApplyClerkData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return ApplyClerkInfoMgr.getInstance().handleGroupApplyClerk(inputData);
			}
		});
		
		handleMapper.put(StoreClerkInfoUpdateType.AddClerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				return ClerkInfoMgr.getInstance().addClerk(formInfo.getAddClerkInfoData());
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.AddRoleSet2Clerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				AddRoleSet2ClerkData inputData = formInfo.getAddRoleSet2ClerkData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return ClerkInfoMgr.getInstance().addRoleSet2Clerk(inputData);
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.ReomveClerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				ReomveClerkData inputData = formInfo.getReomveClerkData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return ClerkInfoMgr.getInstance().reomveClerk(inputData);
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.ReomveRoleOfClerk, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				ReomveRoleOfClerkData inputData = formInfo.getReomveRoleOfClerkData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return ClerkInfoMgr.getInstance().reomveRoleOfClerk(inputData);
			}
		});
		
		handleMapper.put(StoreClerkInfoUpdateType.AddStoreAdminRole, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				AddStoreAdminRoleData inputData = formInfo.getAddStoreAdminRoleData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return StoreAdminRoleMgr.getInstance().addStoreAdminRole(inputData);
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.UpdateStoreAdminRole, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				UpdateStoreAdminRoleData inputData = formInfo.getUpdateStoreAdminRoleData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return StoreAdminRoleMgr.getInstance().updateStoreAdminRole(inputData);
			}
		});
		handleMapper.put(StoreClerkInfoUpdateType.RemoveStoreAdminRole, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				RemoveStoreAdminRoleData inputData = formInfo.getRemoveStoreAdminRoleData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return StoreAdminRoleMgr.getInstance().removeStoreAdminRole(inputData);
			}
		});
		
		handleMapper.put(StoreClerkInfoUpdateType.SetMonthPayDays, new IClerkInfoHandler(){
			@Override
			public OperateTips update(StoreClerkInfoUpdateForm formInfo){
				SetMonthPayDaysData inputData = formInfo.getSetMonthPayDaysData();
				BUserAuthUtils.getInstance().checkPermission(inputData.getStoreId(), StoreAdminPermEnum.CLERK_ADMIN);
				return StoreClerkMonthPayMgr.getInstance().setMonthPayDays(inputData);
			}
		});
	}

	public OperateTips update(StoreClerkInfoUpdateForm updateForm) {
		StoreClerkInfoUpdateType updateType = StoreClerkInfoUpdateType.valueOf(updateForm.getUpdateType());
		IClerkInfoHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
