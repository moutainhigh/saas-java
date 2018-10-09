package com.hq.chainMS.service.chainClerk.bs.updateHandle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.apiData.AdminRoleAddForm;
import com.hq.chainMS.service.chainClerk.apiData.AdminRoleRemoveForm;
import com.hq.chainMS.service.chainClerk.apiData.AdminRoleUpdateForm;
import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateType;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkMgr;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainClerk.data.ChainClerkBeanHelper;
import com.hq.chainMS.service.chainClerk.data.ClerkInfo;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminRole;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class AdminRoleHandle {
	
	public static AdminRoleHandle getInstance() {
		return HotSwap.getInstance().getSingleton(AdminRoleHandle.class);
	}
	
	//添加岗位
	public OperateTips addAdminRole(long chainId, AdminRoleAddForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.AddAdminRole.getDescript()+"失败");
		
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		if(ChainClerkBeanHelper.getInstance().addAdminRole(chainClerk, inputForm.getRole())){
			ChainClerkMgr.getInstance().update(chainClerk);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//更新岗位信息
	public OperateTips updateAdminRole(long chainId, AdminRoleUpdateForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.UpdateAdminRole.getDescript()+"失败");
		
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		if(ChainClerkBeanHelper.getInstance().updateAdminRole(chainClerk, inputForm.getRole())){
			ChainClerkMgr.getInstance().update(chainClerk);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//删除岗位信息
	public OperateTips removeAdminRole(long chainId, AdminRoleRemoveForm inputForm){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.CHAIN_CLERK_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainClerkUpdateType.RemoveAdminRole.getDescript()+"失败");
		
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
		AdminRole adminRole = chainClerk.getRoleMap().get(inputForm.getRoleId());
		List<Integer> permSet = new ArrayList<Integer>(adminRole.getPermSet());
		if(permSet.size() == 1 && permSet.get(0) == 0){//老板的角色不能删除
			tips.setTips("老板的岗位不能删除");
			return tips;
		}
		if(ChainClerkBeanHelper.getInstance().removeAdminRole(chainClerk, inputForm.getRoleId())){
			deleteAdminRoleCallback(inputForm.getRoleId(), chainClerk);
			ChainClerkMgr.getInstance().update(chainClerk);
			tips.setSuccess(true);
		}
		return tips;
	}

	//删除员工对应角色
	private void deleteAdminRoleCallback(Integer roleId, ChainClerk chainClerk) {
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		Collection<ClerkInfo> values = clerkInfoMap.values();
		for (ClerkInfo clerkInfo : values) {
			Set<Integer> roleSet = clerkInfo.getRoleSet();
			roleSet.remove(roleId);
		}
	}
}
