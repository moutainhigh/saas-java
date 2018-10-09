package com.hq.storeMS.service.storeClerkInfo.bs.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.apiData.RemoveStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateType;
import com.hq.storeMS.service.storeClerkInfo.apiData.UpdateStoreAdminRoleData;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.ClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfoBeanHelper;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRoleState;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreAdminRoleMgr {
	
	public static StoreAdminRoleMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreAdminRoleMgr.class);
	}
	
	//添加岗位
	public OperateTips addStoreAdminRole(AddStoreAdminRoleData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.AddStoreAdminRole.getDescript()+"失败");
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		if(StoreClerkInfoBeanHelper.getInstance().addStoreAdminRole(storeClerkInfo, inputData.getRole())){
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			tips.setSuccess(true);
			addLogger(storeId, inputData.getRole().getName(), StoreClerkInfoUpdateType.AddStoreAdminRole);
		}
		return tips;
	}
	
	//更新岗位信息
	public OperateTips updateStoreAdminRole(UpdateStoreAdminRoleData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.UpdateStoreAdminRole.getDescript()+"失败");
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		StoreAdminRole role = inputData.getRole();
		if(StoreClerkInfoBeanHelper.getInstance().updateStoreAdminRole(storeClerkInfo.getRoleMap(), role)){
			if(role.getState() == StoreAdminRoleState.Delete.ordinal()){//删除岗位
				deleteAdminRoleCallback(role.getId(),storeClerkInfo);
				addLogger(storeId, role.getName(), StoreClerkInfoUpdateType.RemoveStoreAdminRole);
			}else {
				addLogger(storeId, role.getName(), StoreClerkInfoUpdateType.UpdateStoreAdminRole);
			}
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//删除岗位信息   用于物理删除  请慎重使用   一般用于删除测试数据
	public OperateTips removeStoreAdminRole(RemoveStoreAdminRoleData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreClerkInfoUpdateType.RemoveStoreAdminRole.getDescript()+"失败");
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		StoreAdminRole storeAdminRole = storeClerkInfo.getRoleMap().get(inputData.getRoleId());
		List<Integer> permSet = new ArrayList<Integer>(storeAdminRole.getPermSet());
		if(permSet.size() == 1 && permSet.get(0) == 0){//老板的角色不能删除
			tips.setTips("老板的岗位不能删除");
			return tips;
		}
		if(StoreClerkInfoBeanHelper.getInstance().removeStoreAdminRole(storeClerkInfo.getRoleMap(), inputData.getRoleId())){
			deleteAdminRoleCallback(inputData.getRoleId(), storeClerkInfo);
			StoreClerkInfoMgr.getInstance().update(storeClerkInfo);
			tips.setSuccess(true);
			addLogger(storeId, storeAdminRole.getName(), StoreClerkInfoUpdateType.RemoveStoreAdminRole);
		}
		return tips;
	}
	
	private void addLogger(long storeId, String adminRoleName, StoreClerkInfoUpdateType clerkInfoUpdateType) {
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, adminRoleName, OpLogTypeEnum.Clerk, clerkInfoUpdateType.getDescript()));
	}

	/**
	 * 删除员工对应角色
	 * @param roleId
	 * @param storeClerkInfo
	 */
	private void deleteAdminRoleCallback(Integer roleId, StoreClerkInfo storeClerkInfo) {
		try {
			Map<Long, ClerkInfo> clerkInfoMap = storeClerkInfo.getClerkInfoMap();
			for (long clerkId : clerkInfoMap.keySet()) {
				ClerkInfo clerkInfo = clerkInfoMap.get(clerkId);
				Set<Integer> roleSet = clerkInfo.getRoleSet();
				Iterator<Integer> it = roleSet.iterator();
				while(it.hasNext()){
					Integer next = it.next();
					if(next.intValue() == roleId.intValue()){
						it.remove();
					}
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.StoreClerkInfo, "StoreAdminRoleHandle[deleteAdminRoleCallback]", "删除店铺员工岗位信息失败", e);
		}
	}
	
	
}
