package com.hq.copyData.copyModule;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.copyData.copyModule.buser.BUserLoginHelper;
import com.hq.copyData.copyModule.module.CopyStorePackageProject;
import com.zenmind.common.json.JsonUtil;

public class CopyStoreDataHelper {
	private static CopyStoreDataHelper instance = new CopyStoreDataHelper();
	
	public static CopyStoreDataHelper getInstance() {
		return instance;
	}
	
	private List<AbstractCopyModule> copyModules = new ArrayList<AbstractCopyModule>();
	
	private CopyStoreDataHelper() {
//		copyModules.add(CopyStoreClerkInfo.newInstance());
//		copyModules.add(CopyStoreBeauticianInfo.newInstance());
//		copyModules.add(CopyStoreLeaguerInfo.newInstance());
//		copyModules.add(CopyStoreProductInfo.newInstance());
//		copyModules.add(CopyStoreCardInfo.newInstance());
//		copyModules.add(CopyStoreGoodsInfo.newInstance());
//		copyModules.add(CopyBUserDevice.newInstance());
		copyModules.add(CopyStorePackageProject.newInstance());
	}
	
	public void copyData(StoreAndPhoneParam source, StoreAndPhoneParam target) throws Exception{
		BUser sourceBoss = BUserLoginHelper.getInstance().login(source.getPhone());
		BUser targetBoss = BUserLoginHelper.getInstance().login(target.getPhone());
		CopyParams params = CopyParams.newInstance();
		params.setSourceBoss(sourceBoss).setTargetBoss(targetBoss).setSourceStoreId(source.getStoreId()).setTargetStoreId(target.getStoreId());
		
		for (AbstractCopyModule copyModule : copyModules) {
			try {
				copyModule.copyDataWithParam(params);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(JsonUtil.getInstance().toJson(params));
			}
		}
	}
}
