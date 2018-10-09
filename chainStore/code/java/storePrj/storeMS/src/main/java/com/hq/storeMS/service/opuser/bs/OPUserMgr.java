package com.hq.storeMS.service.opuser.bs;

import java.util.List;

import com.hq.storeMS.service.auth.opUser.OPUserAuthInfo;
import com.hq.storeMS.service.auth.opUser.OPUserPwdHelper;
import com.hq.storeMS.service.opuser.apiData.OPUserChangePasswordApiData;
import com.hq.storeMS.service.opuser.apiData.OPUserUpdateInfoApiData;
import com.hq.storeMS.service.opuser.apiData.OPUserUpdateRoleApiData;
import com.hq.storeMS.service.opuser.apiData.OPuserQueryApiForm;
import com.hq.storeMS.service.opuser.data.OPUser;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminRole;
import com.hq.storeMS.service.opuser.data.adminRole.OPRolePermInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class OPUserMgr {

	public static OPUserMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OPUserMgr.class);
	}

	// 超级管理员账户密码
	String SUPER_OP_NAME = "admin";
	String SUPER_OP_PASSWORD = "admin";

	public void init(OPAdminRole superRole) {
		OPUser opUserRO = findByName(SUPER_OP_NAME);
		if (opUserRO == null) {
			OPUser opuser = OPUser.newInstance(SUPER_OP_NAME, SUPER_OP_PASSWORD);
			opuser.addOpAdminRole(superRole.getId());
			createUser(opuser);
		}
	}

	/**
	 * 业务层一定要区分是add还是update
	 * 
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(OPUser target) {
		OPUserDataHolder.getInstance().updpate(target);
	}

	public OPUser get(long id) {
		return OPUserDataHolder.getInstance().get(id);
	}

	public OPUser findByName(String name) {
		return OPUserDataHolder.getInstance().findByName(name);
	}

	public List<OPUser> findOPuserList(OPuserQueryApiForm queryForm) {
		return OPUserDataHolder.getInstance().findOPuserList(queryForm);
	}

	public OPUser findByPhone(long phone) {
		return OPUserDataHolder.getInstance().findByPhone(phone);
	}

	public boolean updateInfo(OPUserUpdateInfoApiData updateInfoData) {
		OPUser opuser = get(updateInfoData.getOpuserId());
		updateInfoData.update(opuser);
		update(opuser);
		return true;
	}

	public boolean updateRole(OPUserUpdateRoleApiData updateRoleData) {
		OPUser opuser = get(updateRoleData.getOpuserId());
		updateRoleData.update(opuser);
		update(opuser);
		return true;
	}

	public OPUser createUser(OPUser opuser) {
		// 加密密码
		OPUserPwdHelper.getInstance().encryptUser(opuser);
		OPUserDataHolder.getInstance().addAndReturnId(opuser);
		return opuser;
	}

	public boolean changePassword(OPUserChangePasswordApiData changePasswordData) {

		long userId = changePasswordData.getBuserId();
		String newPassword = changePasswordData.getPassword();
		OPUser buser = get(userId);
		String encryptPassword = OPUserPwdHelper.getInstance().getEncryptPassword(buser, newPassword);
		buser.setPassword(encryptPassword);
		update(buser);
		return true;
	}

	public OPUserAuthInfo findRolePermInfoByPhone(long phone) {
		OPUser opuser = findByPhone(phone);

		long opId = opuser.getId();
		OPUserAuthInfo authInfo = OPUserAuthInfo.newInstance(opId);

		OPRolePermInfo rolePermInfo = OPRolePermInfo.newInstance(opuser);
		authInfo.setRolePermInfo(rolePermInfo);

		return authInfo;
	}
}
