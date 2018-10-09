package com.hq.copyData;

import java.util.Set;

import com.hq.chainStore.service.buser.bs.BUserMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.copyData.copyModule.CopyStoreDataHelper;
import com.hq.copyData.copyModule.StoreAndPhoneParam;
import com.hq.copyData.copyModule.buser.BUserLoginHelper;
import com.hq.copyData.copyModule.buser.CopyBossData;
import com.hq.copyData.copyModule.store.CopyStore;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;

public class CopyExperienceData {
	private static CopyExperienceData instance = new CopyExperienceData();
	
	public static CopyExperienceData getInstance(){
		return instance;
	}
	
	public void copyData(long sourcePhone, long targetPhone) throws Exception{
		BUser sourceBoss = BUserLoginHelper.getInstance().login(sourcePhone);
		CopyBossData.getInstance().createNewBossWithOldBoss(sourceBoss, targetPhone);
		BUser targetBoss = BUserLoginHelper.getInstance().login(targetPhone);
		
		Set<Long> storeIds = getBossStoreIds(sourceBoss);
		
		long sourceStoreId = 0L;
		long targetStoreId = 0L;
		for (Long sId : storeIds) {
			sourceStoreId = sId;
			targetStoreId = CopyStore.getInstance().copyStoreAndReturnNewStoreId(sourceBoss, targetBoss, sourceStoreId);
			CopyStoreDataHelper.getInstance().copyData(StoreAndPhoneParam.newInstance(sourceStoreId, sourcePhone), StoreAndPhoneParam.newInstance(targetStoreId, targetPhone));
		}
	}
	
	private Set<Long> getBossStoreIds(BUser bossP) {
		AccessTokenMgr.getInstance().setOpIdTL(bossP.getId());
		BUser bUser = BUserMgr.getInstance().get(bossP.getId());
		AccessTokenMgr.getInstance().removeOpIdTL();
		return bUser.getStoreIdSet();
	}
}
