package com.hq.storeMS.service.storeClerkInfo.bs.update;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.buser.bs.BUserModifyMgr;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.bs.handler.BUserRoleHelper;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BUserBeanHelper;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.sms.bs.check.SmsCheckMgr;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.RemoveBeauticianInfoData;
import com.hq.storeMS.service.storeBeauticianInfo.bs.StoreBeauticianInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddRoleSet2ClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.ReomveRoleOfClerkData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfoBeanHelper;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class ClerkInfoMgr {
	
	public static ClerkInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ClerkInfoMgr.class);
	}
	
	public OperateTips addClerk(AddClerkInfoData inputData){
		OperateTips tips = OperateTips.newInstance(false, "加入店铺失败");
		
		if(StringUtils.isNoneBlank(inputData.getVerifyCode())){
			BUser buser = BUserQueryMgr.getInstance().getSimple(inputData.getbUserId());
			tips = SmsCheckMgr.getInstance().checkSmsCode(buser.getPhone(), inputData.getVerifyCode());
			if(!tips.isSuccess()){
				return tips;
			}
		}
		
		long storeId = inputData.getStoreId();
		//验证店铺员工数量
		if(StoreVipMgr.getInstance().isBuserLimited(storeId)){
			tips.setTips("当前店铺员工数量已达上限");
			return tips;
		}
		
		if(BUserRoleHelper.getInstance().isBossRole(BUserQueryMgr.getInstance().get(inputData.getbUserId()))){
			tips.setTips("用户是管理者，不能加入店铺");
			return tips;
		}
		
		ClerkInfo clerkInfo = ClerkInfo.newInstance(inputData.getbUserId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		if(StoreClerkInfoBeanHelper.getInstance().addClerkInfo(storeClerkInfo.getClerkInfoMap(), clerkInfo)){
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			addStoreId2Clerk(inputData);
			tips.setSuccess(true);
		}else{
			tips.setTips("已加入店铺,不能重复加入");
		}
		return tips;
	}
	
	
	//删除员工
	public OperateTips reomveClerk(ReomveClerkData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.ReomveClerk.getDescript()+"失败");
		long clerkBUserId = inputData.getBuserId();
		if(isBoss(storeId, clerkBUserId)) {
			tips.setTips("老板不能删除");
			return tips;
		}
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		if(StoreClerkInfoBeanHelper.getInstance().removeClerk(storeClerkInfo.getClerkInfoMap(), clerkBUserId)){
			//同时删除员工申请信息
			StoreClerkInfoBeanHelper.getInstance().removeApplyClerk(storeClerkInfo.getApplyClerkInfoMap(), clerkBUserId);
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			removeStoreIdOfClerk(inputData);
			
			//删除员工 也将医美师的信息移除掉
			RemoveBeauticianInfoData removeBeauticianInfoData = RemoveBeauticianInfoData.newInstance();
			removeBeauticianInfoData.setBuserId(clerkBUserId);
			StoreBeauticianInfoMgr.getInstance().removeBeauticianInfo(storeId, removeBeauticianInfoData);
			tips.setSuccess(true);
			addLogger(storeId, clerkBUserId, StoreClerkInfoUpdateType.ReomveClerk);
		}
		return tips;
	}
	
	private boolean isBoss(long storeId, long buserId) {
		StoreRO store = StoreMgr.getInstance().getReadOnly(storeId);
		if(store!=null && store.getBossId() == buserId) {
			return true;
		}
		
		return false;
	}
	
	private void removeStoreIdOfClerk(ReomveClerkData reomveClerkData) {
		BUser buser = BUserQueryMgr.getInstance().getSimple(reomveClerkData.getBuserId());
		BUserBeanHelper.getInstance().removeStoreId4BUser(buser, reomveClerkData.getStoreId());
		BUserModifyMgr.getInstance().update(buser);
	}
	
	//添加员工的岗位信息
	public OperateTips addRoleSet2Clerk(AddRoleSet2ClerkData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.AddRoleSet2Clerk.getDescript()+"失败");
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		long clerkBUserId = inputData.getBuserId();
		if(StoreClerkInfoBeanHelper.getInstance().addRoleSet2Clerk(storeClerkInfo.getClerkInfoMap(), clerkBUserId, inputData.getRoleIdSet())){
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			tips.setSuccess(true);
			addLogger(storeId, clerkBUserId, StoreClerkInfoUpdateType.AddRoleSet2Clerk);
		}
		return tips;
	}
	
	//移除员工的岗位信息
	public OperateTips reomveRoleOfClerk(ReomveRoleOfClerkData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.ReomveRoleOfClerk.getDescript()+"失败");
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		long clerkBUserId = inputData.getBuserId();
		StoreAdminRole role = storeClerkInfo.getRole(inputData.getRoleId());
		if(StoreClerkInfoBeanHelper.getInstance().removeRoleOfClerk(storeClerkInfo.getClerkInfoMap(), clerkBUserId, role)){
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			tips.setSuccess(true);
			addLogger(storeId, clerkBUserId, StoreClerkInfoUpdateType.ReomveRoleOfClerk);
		}
		return tips;
	}
	
	private void addLogger(long storeId, long buserId, StoreClerkInfoUpdateType clerkInfoUpdateType) {
		BUser bUser = BUserQueryMgr.getInstance().get(buserId);
		if(bUser!=null) {
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, bUser.getName(), OpLogTypeEnum.Clerk, clerkInfoUpdateType.getDescript()));
		}
	}
	
	private void addStoreId2Clerk(AddClerkInfoData addClerkInfoData) {
		BUser buser = BUserQueryMgr.getInstance().get(addClerkInfoData.getbUserId());
		buser.addStoreId(addClerkInfoData.getStoreId());
		//角色改为工作者
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(BuserRoleEnum.CLERK.ordinal());
		buser.setRoleSet(roleSet);
		BUserModifyMgr.getInstance().update(buser);
	}
}
