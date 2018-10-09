package com.hq.copyData.copyModule.module;

import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.experience.data.storeClerk.GenerateStoreClerkDataB;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;

public class CopyStoreClerkInfo extends AbstractCopyModule{
	
	public static CopyStoreClerkInfo newInstance(){
		CopyStoreClerkInfo data = new CopyStoreClerkInfo();
		return data;
	}
	
	public void copy(){
		GenerateStoreClerkDataB generateStoreClerkData = new GenerateStoreClerkDataB();
		generateStoreClerkData.initEnv(getParams().getTargetBoss().getPhone());
		generateStoreClerkData.setStoreId(getTargetStoreId());
		
		AccessTokenMgr.getInstance().setOpIdTL(getSourceBossId());
		StoreClerkInfo storeClerk = StoreClerkInfoMgr.getInstance().getByStoreId(getSourceStoreId());
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		generateStoreClerkData.genStoreClerkData(storeClerk);
		System.out.println("Generate StoreClerk Data success");
	}
}
