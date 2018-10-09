package com.hq.storeMS.service.buser.bs;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.auth.buser.BUserPwdHelper;
import com.hq.storeMS.service.buser.apiData.BUserResetPasswordForm;
import com.hq.storeMS.service.buser.apiData.BUserUpdateInfoApiData;
import com.hq.storeMS.service.buser.bs.handler.BUserRoleHelper;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.storeVipType.data.StoreVipLevelEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserModifyMgr {

	public static BUserModifyMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserModifyMgr.class);
	}

	public void update(BUser target) {
		BUserDataHolder.getInstance().updpate(target);
	}

	public BUser updateInfo(BUserUpdateInfoApiData updateInfoData) {
		BUser buser = BUserQueryMgr.getInstance().getSimple(updateInfoData.getBuserId());
		updateInfoData.update(buser);
		update(buser);
		return buser;
	}
	
	public BUser createUser(BUser buser) {
		BUserDataHolder.getInstance().addAndReturnId(buser);
		//生成主账号(10位)与随机密码(6位)
		buser.setPriAccountNum(StringUtils.leftPad(buser.getId()+"", 10, "0"));
		// 加密密码
		BUserPwdHelper.getInstance().encryptUser(buser);
		BUserDataHolder.getInstance().updpate(buser);
		return buser;
	}
	
	public BUser addUser(BUser buser) {
		BUserDataHolder.getInstance().addAndReturnId(buser);
		return buser;
	}

	public BUser changePassword(BUser buser,String newPassword) {
		String encryptPassword = BUserPwdHelper.getInstance().getEncryptPassword(buser, newPassword);
		buser.setPassword(encryptPassword);
		update(buser);
		return buser;
	}
	
	public BUser resetPassword(BUserResetPasswordForm resetPasswordData) {
		long phone =  resetPasswordData.getPhone();
		String newPassword = resetPasswordData.getPassword();
		BUser buser = BUserQueryMgr.getInstance().findByPhone(phone);
		String encryptPassword = BUserPwdHelper.getInstance().getEncryptPassword(buser, newPassword);
		buser.setPassword(encryptPassword);
		update(buser);
		return buser;
	}
	
	//设置用户的有效时间与会员等级
	public void checkExpiredTime(BUser bUser) {
		try {
			if(bUser == null || BUserRoleHelper.getInstance().isInitRole(bUser)){
				return;
			}
			if(isBoss(bUser)){
				boolean updFlag = false;
				if(isOldVipType(bUser)){
					bUser.setVipType(StoreVipLevelEnum.StandardUser.ordinal());
					updFlag = true;
				}
				if(bUser.getBusinessPhone() == 0L){
					bUser.setBusinessPhone(ServerConstants.BUSINESS_PHONE);
					updFlag = true;
				}
				if(updFlag){
					BUserDataHolder.getInstance().updpate(bUser);
				}
			}else{
				BUser boss = findMyBoss(bUser.getStoreIdSet());
				if(boss!=null){
					bUser.setExpiredTime(boss.getExpiredTime());
					bUser.setVipType(boss.getVipType());
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.BUser, "BUserMgr[checkExpiredTime]", "", e);
		}
	}
	
	private boolean isBoss(BUser bUser){
		return BUserRoleHelper.getInstance().isBossRole(bUser);
	}
	
	//早期的会员类型 0-4
	private boolean isOldVipType(BUser bUser){
		int vipType = (int)bUser.getVipType();
		return vipType < StoreVipLevelEnum.HonKonUser.ordinal();
	}
	
	private BUser findMyBoss(Set<Long> storeIdSet){
		BUser boss = null;
		if(CollectionUtils.isNotEmpty(storeIdSet)){
			List<StoreRO> stores = StoreMgr.getInstance().findByIdList(storeIdSet, storeIdSet.size(), 1);
			if(CollectionUtils.isNotEmpty(stores)){
				long buserId = stores.get(0).getBossId();
				boss = BUserDataHolder.getInstance().get(buserId);
			}
		}
		return boss;
	}
}
