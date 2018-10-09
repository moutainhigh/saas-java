package com.hq.storeMS.service.storeClerkInfo.bs.update;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.message.trigger.MessageTriggerMgr;
import com.hq.storeMS.common.message.trigger.TriggerForm;
import com.hq.storeMS.common.message.trigger.TriggerTypeEnum;
import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.bs.handler.BUserRoleHelper;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.storeClerkInfo.apiData.ApplyClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.HandleApplyClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.HandleGroupApplyClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.ApplyClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.ApplyState;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfoBeanHelper;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ApplyClerkInfoMgr {
	
	public static ApplyClerkInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ApplyClerkInfoMgr.class);
	}
	
	//申请加入店铺
	public OperateTips applyClerk(ApplyClerkInfoData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.ApplyClerk.getDescript()+"失败");
		
		//验证店铺员工数量
		if(StoreVipMgr.getInstance().isBuserLimited(storeId)){
			tips.setTips("当前店铺员工数量已达上限");
			return tips;
		}
		
		if(BUserRoleHelper.getInstance().isBossRole(BUserQueryMgr.getInstance().getSimple(inputData.getbUserId()))){
			tips.setTips("当前用户是管理者，不能申请加入店铺");
			return tips;
		}
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		ApplyClerkInfo applyClerkInfo = ApplyClerkInfo.newInstance(inputData.getbUserId(), storeId);
		if(StoreClerkInfoBeanHelper.getInstance().applyClerkInfo(storeClerkInfo.getApplyClerkInfoMap(), applyClerkInfo)){
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			pushApplyMessage2UMeng(applyClerkInfo.getStoreId(), applyClerkInfo.getBuserId());
			tips.setSuccess(true);
		}else{
			tips.setSuccess(true);
			tips.setTips("已申请加入店铺,不能重复申请");
		}
		return tips;
	}
	
	//处理员工申请
	public OperateTips handleApplyClerk(HandleApplyClerkInfoData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.HandleApplyClerk.getDescript()+"失败");
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		ApplyClerkInfo applyClerkInfo = doApplyClerkInfo(storeClerkInfo, inputData);
		if(applyClerkInfo != null){
			doBUserStore(applyClerkInfo, inputData);
			tips.setSuccess(true);
		}else{
			BUser buser = BUserQueryMgr.getInstance().getSimple(inputData.getbUserId());
			tips.setTips(buser.getName()+"是管理者，不能加入店铺");
		}
		return tips;
	}
	
	//批量处理员工申请
	public OperateTips handleGroupApplyClerk(HandleGroupApplyClerkData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.HandleGroupApplyClerk.getDescript()+"失败");
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		Set<Long> buserIdSet = inputData.getBuserIdSet();
		StringBuilder sb = new StringBuilder();
		for (long buserId : buserIdSet) {
			HandleApplyClerkInfoData form = HandleApplyClerkInfoData.newInstance();
			form.setbUserId(buserId);
			form.setStoreId(storeId);
			form.setApproved(inputData.isApproved());
			ApplyClerkInfo applyClerkInfo = doApplyClerkInfo(storeClerkInfo, form);
			if(applyClerkInfo != null){
				doBUserStore(applyClerkInfo, form);
			}else{
				BUser buser = BUserQueryMgr.getInstance().getSimple(buserId);
				sb.append(buser.getName()).append(",");
			}
		}
		String buserNames = sb.toString();
		if(StringUtils.isNoneBlank(buserNames)){
			tips.setTips("["+buserNames.substring(0, buserNames.length()-1)+"]是管理者，不能加入店铺");
		}else{
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private ApplyClerkInfo doApplyClerkInfo(StoreClerkInfo storeClerkInfo, HandleApplyClerkInfoData inputData){
		if(!canApproved(inputData)){
			return null;
		}
		
		ApplyClerkInfo applyClerkInfo = StoreClerkInfoBeanHelper.getInstance().handleApplyClerkInfo(storeClerkInfo.getApplyClerkInfoMap(), inputData.getbUserId(), inputData.isApproved());
		if(applyClerkInfo != null){
			ClerkInfo clerkInfo = ClerkInfo.newInstance(inputData.getbUserId());
			if(applyClerkInfo.getStateEnum() == ApplyState.Approved){
				StoreClerkInfoBeanHelper.getInstance().addClerkInfo(storeClerkInfo.getClerkInfoMap(), clerkInfo);
			}
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
		}
		return applyClerkInfo;
	}
	
	private boolean canApproved(HandleApplyClerkInfoData inputData){
		if(inputData.isApproved()){
			BUser buser = BUserQueryMgr.getInstance().getSimple(inputData.getbUserId());
			//是管理者
			if(BUserRoleHelper.getInstance().isBossRole(buser)){
				return false;
			}
		}
		return true;
	}
	
	//通过的员工，修改buser的storeId信息
	private void doBUserStore(ApplyClerkInfo applyClerkInfo, HandleApplyClerkInfoData inputData){
		BUser bUser = BUserQueryMgr.getInstance().get(inputData.getbUserId());
		if(applyClerkInfo.getStateEnum() == ApplyState.Approved){
			bUser.addStoreId(inputData.getStoreId());
			
			if(CollectionUtils.isNotEmpty(bUser.getStoreIdSet())){
			    //角色改为工作者
			    Set<Integer> roleSet = new HashSet<Integer>();
			    roleSet.add(BuserRoleEnum.CLERK.ordinal());
			    bUser.setRoleSet(roleSet);
			}
			
			BUserModifyMgr.getInstance().update(bUser);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(inputData.getStoreId(), bUser.getName(), OpLogTypeEnum.Clerk, "通过员工审核"));
		}else {
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(inputData.getStoreId(), bUser.getName(), OpLogTypeEnum.Clerk, "不通过员工审核"));
		}
		pushDoApplyMessage2UMeng(applyClerkInfo.getStoreId(), applyClerkInfo.getBuserId(), applyClerkInfo.getStateEnum() == ApplyState.Approved);
	}
	
	/**
	 * 向友盟推送员工申请加入消息
	 * @param storeId
	 * @return
	 */
	private void pushApplyMessage2UMeng(long storeId, long applyClerkId) {
		StoreRO store = StoreMgr.getInstance().getReadOnly(storeId);
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		Collection<ClerkInfo> values = storeClerkInfo.getClerkInfoMap().values();
		
		Set<Long> buserIds = new HashSet<Long>();//待推送的buserId信息
		buserIds.add(store.getBossId());//老板是必须推送的[必定有店员管理权限]
		
		//店铺管理权限下标
		Integer clerkIndex = StoreAdminPermEnum.CLERK_ADMIN.ordinal();
		for (ClerkInfo clerkInfo : values) {
			if(clerkInfo.getEntityState() == EntityState.Normal.ordinal()) {
				Set<Integer> bUserPermission = storeClerkInfo.takeBUserPermission(clerkInfo.getBuserId());
				if(bUserPermission.contains(clerkIndex)) {
					buserIds.add(clerkInfo.getBuserId());
				}
			}
		}
		
		for (Long buserId : buserIds) {
			TriggerForm data = TriggerForm.newInstance(storeId, buserId);
			data.setTriggerType(TriggerTypeEnum.NEW_MY_CLERK.ordinal());
			data.setStoreName(store.getName());
			data.setId(String.valueOf(applyClerkId));
			MessageTriggerMgr.getInstance().triggerMessage(data);
		}
	}
	
	/**
	 * 向友盟推送员工审核消息
	 * @param appointment
	 * @return
	 */
	private void pushDoApplyMessage2UMeng(long storeId, long buserId, boolean pass) {
		StoreRO store = StoreMgr.getInstance().getReadOnly(storeId);
		TriggerForm data = TriggerForm.newInstance(storeId, buserId);
		if(pass) {
			data.setTriggerType(TriggerTypeEnum.APPLY_CLERK_SUCCESS.ordinal());
		}else {
			data.setTriggerType(TriggerTypeEnum.APPLY_CLERK_FAILURE.ordinal());
		}
		data.setStoreName(store.getName());
		//申请是否通过 暂时不做推送
//		MessageTriggerMgr.getInstance().triggerMessage(data);
	}
}
