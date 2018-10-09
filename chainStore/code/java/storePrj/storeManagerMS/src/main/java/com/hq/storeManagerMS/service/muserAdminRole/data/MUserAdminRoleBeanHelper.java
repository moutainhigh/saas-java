package com.hq.storeManagerMS.service.muserAdminRole.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.common.hotSwap.HotSwap;

public class MUserAdminRoleBeanHelper {
	public static MUserAdminRoleBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(MUserAdminRoleBeanHelper.class);
	}
	
	public MUserAdminRole getAdminRole(){
		MUserAdminRole target = MUserAdminRole.newInstance();
		target.setDescript(SysAdminRoleEnum.MNG_ADMIN.getMark());
		target.setName(SysAdminRoleEnum.MNG_ADMIN.getMark());
		Set<Integer> permSet = new HashSet<Integer>();
		permSet.add(MUserAdminPermEnum.MNG_ADMIN.ordinal());
		target.setPermSet(permSet);
		return target;
	}
	
	public MUserAdminRole getOperateRole(){
		MUserAdminRole target = MUserAdminRole.newInstance();
		target.setDescript(SysAdminRoleEnum.MNG_OPERATE.getMark());
		target.setName(SysAdminRoleEnum.MNG_OPERATE.getMark());
		Set<Integer> permSet = new HashSet<Integer>();
		permSet.add(MUserAdminPermEnum.MNG_FEES.ordinal());
		permSet.add(MUserAdminPermEnum.MNG_FUNC.ordinal());
		permSet.add(MUserAdminPermEnum.MNG_PROXY.ordinal());
		target.setPermSet(permSet);
		return target;
	}
}