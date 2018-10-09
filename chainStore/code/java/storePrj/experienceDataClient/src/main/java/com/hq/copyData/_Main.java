package com.hq.copyData;

import java.util.ArrayList;
import java.util.List;

import com.hq.StoreClientMgr;
import com.hq.chainStore.service.buser.data.BUser;
import com.hq.copyData.copyModule.CopyStoreDataHelper;
import com.hq.copyData.copyModule.StoreAndPhoneParam;
import com.hq.copyData.copyModule.buser.BUserLoginHelper;
import com.hq.copyData.copyModule.store.CopyStore;
import com.hq.zenmind.dao.rest.restSTImpl.CacheMgr4Test;
import com.hq.zenmind.dao.rest.restSTImpl.RestLogerImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestProxySTImpl;
import com.hq.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

public class _Main {
	public static void main(String[] args) throws Exception {
//		copyExperienceData();
		copyStoreData();
	}
	
	//拷贝phone的数据到targetPhone
	public static void copyExperienceData() throws Exception {
		long sourcePhone = 13660623953L;
		long targetPhone = 13660623909L;
		
		String storeService = "https://www.zhimeitimes.com/dev/storems/ws/v1";
//		String storeService = "http://192.168.40.220/storems/ws/v1";
//		String storeService = "http://127.0.0.1:9114/storems/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService);
		
		for (int i = 0; i < 10; i++) {
			CopyExperienceData.getInstance().copyData(sourcePhone, targetPhone + i * 10);
			System.out.println("["+(targetPhone + i * 10)+"] finish");
		}
//		CopyExperienceData.getInstance().copyData(sourcePhone, targetPhone);
	}
	
	//拷贝sourcePhone的sourceStoreId店铺数据到targetPhone的targetStoreId店铺下
	public static void copyStoreData() throws Exception {
		String storeService = "https://www.zhimeitimes.com/dev/storems/ws/v1";
//		String storeService = "http://192.168.40.220/storems/ws/v1";
//		String storeService = "http://127.0.0.1:9114/storems/ws/v1";
		RestTemplateMgr.getInstance().init();
		StoreClientMgr.init(new RestLogerImpl(), new RestProxySTImpl(), new CacheMgr4Test(), storeService);
		
		long sourcePhone=13660623953L;
		long targetPhone=13660623929L;
		
		BUser sourceBoss = BUserLoginHelper.getInstance().login(sourcePhone);
		List<Long> storeIds = new ArrayList<Long>(sourceBoss.getStoreIdSet());
		
		
		for (int j = 0; j < 10 && targetPhone <= 13660623999L; j++) {
			targetPhone = targetPhone + j * 10;
			BUser targetBoss = BUserLoginHelper.getInstance().login(targetPhone);
			List<Long> targetIds = new ArrayList<Long>(targetBoss.getStoreIdSet());
			
			long sourceStoreId = 0L;
			long targetStoreId = 0L;
			for (int i = 0; i < storeIds.size(); i++) {
				sourceStoreId = storeIds.get(i);
				targetStoreId = targetIds.get(i);
				CopyStoreDataHelper.getInstance().copyData(StoreAndPhoneParam.newInstance(sourceStoreId, sourcePhone), StoreAndPhoneParam.newInstance(targetStoreId, targetPhone));
			}
		}
//		CopyStoreDataHelper.getInstance().copyData(StoreAndPhoneParam.newInstance(sourceStoreId, sourcePhone), StoreAndPhoneParam.newInstance(targetStoreId, targetPhone));
	}
}
