package com.hq.copyData.copyModule.buser;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.buser.apiData.BUserAddApiForm;
import com.hq.chainStore.service.buser.apiData.BUserVipRechargeData;
import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.experienceData.service.DataConstants;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestResp;

public class CopyBossData {
	private static CopyBossData instance = new CopyBossData();
	
	public static CopyBossData getInstance(){
		return instance;
	}
	
	private BUser sourceBoss;
	private long targetPhone;
	
	public void createNewBossWithOldBoss(BUser sourceBossP, long targetPhoneP) {
		init(sourceBossP, targetPhoneP);
		
		BUser bUser = getOldBUser();
		
		RestResp restResp = createNewBoss(bUser);

		if(restResp != null && restResp.gettJson() != null) {
			callBackUpdateNewBoss(restResp, bUser);
		}
		
		System.out.println("copy boss info finish");
	}
	
	private void init(BUser sourceBossP, long targetPhoneP) {
		this.sourceBoss = sourceBossP;
		this.targetPhone = targetPhoneP;
	}
	
	private BUser getOldBUser() {
		AccessTokenMgr.getInstance().setOpIdTL(sourceBoss.getId());
		BUser bUser = BUserMgr.getInstance().get(sourceBoss.getId());
		AccessTokenMgr.getInstance().removeOpIdTL();
		return bUser;
	}
	
	private RestResp createNewBoss(BUser bUser) {
		BUserAddApiForm formInfo = BUserAddApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(bUser, formInfo);
		formInfo.setPhone(targetPhone);
		formInfo.setPassword(DataConstants.PASSWORD);
		return BUserMgr.getInstance().reg(formInfo);
	}
	
	private void callBackUpdateNewBoss(RestResp restResp, BUser bUser) {
		BUser tmpUser = JsonUtil.getInstance().fromJson(restResp.gettJson(), BUser.class);

		AccessTokenMgr.getInstance().setOpIdTL(sourceBoss.getId());
		BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
		vipRechargeData.setAmount(RandomUtils.nextLong(1000, 5000));
		vipRechargeData.setVipType(bUser.getVipType());
		vipRechargeData.setExpiredTime(bUser.getExpiredTime());
		vipRechargeData.setBuserId(tmpUser.getId());
		BUserMgr.getInstance().vipRecharge(tmpUser.getId(), vipRechargeData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
