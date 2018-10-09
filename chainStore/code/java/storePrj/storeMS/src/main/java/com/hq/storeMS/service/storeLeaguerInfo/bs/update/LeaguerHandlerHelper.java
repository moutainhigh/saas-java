package com.hq.storeMS.service.storeLeaguerInfo.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateApiForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.StoreLeaguerInfoUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerHandlerHelper {

	public static LeaguerHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerHandlerHelper.class);
	}

	private Map<StoreLeaguerInfoUpdateType, ILeaguerHandler> handleMapper = new HashMap<StoreLeaguerInfoUpdateType, ILeaguerHandler>();

	public LeaguerHandlerHelper() {
		handleMapper.put(StoreLeaguerInfoUpdateType.AddLeaguerLabelList, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerLabelMgr.getInstance().addLeaguerLabelList(updateForm.getStoreId(), updateForm.getLeaguerLabelAddForms());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.AddLeaguerLabel, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerLabelMgr.getInstance().addLeaguerLabel(updateForm.getStoreId(), updateForm.getLeaguerLabelAddForm());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.RemoveLeaguerLabel, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerLabelMgr.getInstance().removeLeaguerLabel(updateForm.getStoreId(), updateForm.getLeaguerLabelRemoveForm());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.UpdateLeaguerLabel, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerLabelMgr.getInstance().updateLeaguerLabel(updateForm.getStoreId(), updateForm.getLeaguerLabelUpdateForm());
			}
		});
		
		handleMapper.put(StoreLeaguerInfoUpdateType.AddAttention, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				return AttentionMgr.getInstance().addAttention(updateForm.getStoreId(), updateForm.getAddAttention());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.RemoveAttention, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				return AttentionMgr.getInstance().removeAttention(updateForm.getStoreId(), updateForm.getRemoveAttention());
			}
		});
		
		handleMapper.put(StoreLeaguerInfoUpdateType.AddLeaguerInfo, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerInfoMgr.getInstance().addLeaguerInfo(updateForm.getStoreId(), updateForm.getLeaguerAddInfoData());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.AddLeaguerInfoList, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return BatchLeaguerInfoMgr.getInstance().importListData(updateForm.getStoreId(), updateForm.getLeaguerAddInfoDataList());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.AddListFromExcel, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchLeaguerInfoMgr.getInstance().addListFromExcel(updateForm.getStoreId(), updateForm.getAddListFromExcel());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.AddListFromStore, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchLeaguerInfoMgr.getInstance().addListFromStore(updateForm);
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.AddListOfLeaguerIds, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return BatchLeaguerInfoMgr.getInstance().addListOfLeaguerIds(updateForm.getStoreId(), updateForm.getLeaguerIds());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.DelLeaguer, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerInfoMgr.getInstance().delLeaguer(updateForm.getStoreId(), updateForm.getLeaguerDelInfoData());
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.UpdateLeaguerInfo, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerInfoMgr.getInstance().updateLeaguerInfo(updateForm.getStoreId(), updateForm.getLeaguerUpdateInfoData());
			}
		});
		
		handleMapper.put(StoreLeaguerInfoUpdateType.PurchaseProductCard, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.ReduceProductCardCount, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.RechargeMemberCard, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreLeaguerInfoUpdateType.UpdateMemberCard, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerMemberCardMgr.getInstance().updateMemberCard(updateForm.getStoreId(), updateForm.getUpdateMemberCardForm());
			}
		});
		
		handleMapper.put(StoreLeaguerInfoUpdateType.SaveFollowUser, new ILeaguerHandler(){
			@Override
			public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm){
				BUserAuthUtils.getInstance().checkPermission(updateForm.getStoreId(), StoreAdminPermEnum.LEAGUER_ADMIN);
				return LeaguerInfoMgr.getInstance().saveFollowUserForm(updateForm.getStoreId(), updateForm.getSaveFollowUserForm());
			}
		});
	}

	public OperateTips update(StoreLeaguerInfoUpdateApiForm updateForm) {
		StoreLeaguerInfoUpdateType updateType = StoreLeaguerInfoUpdateType.valueOf(updateForm.getUpdateType());
		ILeaguerHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
