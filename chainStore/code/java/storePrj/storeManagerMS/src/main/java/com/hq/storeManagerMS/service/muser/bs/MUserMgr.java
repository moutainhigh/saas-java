package com.hq.storeManagerMS.service.muser.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeManagerMS.service.auth.muser.MUserAuthInfo;
import com.hq.storeManagerMS.service.auth.muser.MUserPwdHelper;
import com.hq.storeManagerMS.service.muser.data.GenderEnum;
import com.hq.storeManagerMS.service.muser.data.MUser;
import com.hq.storeManagerMS.service.muser.data.MUserBeanHelper;
import com.hq.storeManagerMS.service.muser.data.MUserRO;
import com.hq.storeManagerMS.service.muser.data.SysAdminEnum;
import com.hq.storeManagerMS.service.muserAdminRole.bs.MUserAdminRoleMgr;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserRolePermInfo;
import com.hq.storeManagerMS.service.muserAdminRole.data.SysAdminRoleEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserMgr {

	public static MUserMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MUserMgr.class);
	}
	
	public void init(){
		MUser admin = findByAccount(SysAdminEnum.ADMIN.getAccount());
		if(admin == null){
			List<String> names = new ArrayList<>();
			names.add(SysAdminRoleEnum.MNG_ADMIN.getMark());
			List<MUserAdminRole> adminRoles = MUserAdminRoleMgr.getInstance().findByNames(names);
			if(CollectionUtils.isNotEmpty(adminRoles)){
				MUserAdminRole adminRole=adminRoles.get(0);
				admin = MUser.newInstance();
				admin.setAccount(SysAdminEnum.ADMIN.getAccount());
				admin.setPassword(SysAdminEnum.ADMIN.getPassword());
				admin.setName("超级管理员");
				admin.getMuserAdminRoleIds().add(adminRole.getId());
				admin.setGender(GenderEnum.MALE.ordinal());
				createUser(admin);
			}
		}
	}

	public void addAndReturnId(MUser target) {
		MUserDataHolder.getInstance().addAndReturnId(target);
	}
	
	public void delete(MUser target) {
		MUserDataHolder.getInstance().delete(target);
	}
	
	public void update(MUser target) {
		MUserDataHolder.getInstance().update(target);
	}

	public MUser get(long id) {
		return MUserDataHolder.getInstance().get(id);
	}
	
	public MUser findByAccount(String account) {
		return MUserDataHolder.getInstance().findByAccount(account);
	}

	public MUser createUser(MUser muser) {
		// 加密密码
		MUserPwdHelper.getInstance().encryptUser(muser);
		MUserDataHolder.getInstance().addAndReturnId(muser);
		return muser;
	}
	
	public MUserAuthInfo findRolePermInfoByAccount(String account) {
		MUserRO muser = findByAccount(account);
		Map<Long, MUserAdminRole> roleMap = MUserAdminRoleMgr.getInstance().getAllMUserAdminRoleMap();
		
		Set<Long> roles = muser.getMuserAdminRoleIds();
		Set<MUserAdminRole> roleSet = MUserBeanHelper.getInstance().getRoleSet(roles, roleMap);
		long muserId = muser.getId();
		MUserAuthInfo authInfo = MUserAuthInfo.newInstance(muserId, MUserRolePermInfo.newInstance(muserId, roleSet));
		return authInfo;
	}
}
