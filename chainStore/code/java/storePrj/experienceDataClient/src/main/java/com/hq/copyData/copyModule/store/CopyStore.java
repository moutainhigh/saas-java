package com.hq.copyData.copyModule.store;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.store.apiData.AlipayQrCodeApiData;
import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.apiData.WechatQrCodeApiData;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CopyStore {
	private static CopyStore instance = new CopyStore();
	
	public static CopyStore getInstance(){
		return instance;
	}
	
	private BUser sourceBoss;
	private BUser targetBoss;
	private long sourceStoreId;
	
	public long copyStoreAndReturnNewStoreId(BUser sourceBoss, BUser targetBoss, long sourceStoreId){
		init(sourceBoss, targetBoss, sourceStoreId);
		
		Store oldStore = getOldStore();
		
		long newStoreId = createNewStore(oldStore);
		
		uploadAlipayQrCode(newStoreId, oldStore);
		uploadWechatQrCode(newStoreId, oldStore);
		
		System.out.println("copy store info finish");
		return newStoreId;
	}
	
	private void init(BUser bossP, BUser newBossP, long storeIdP) {
		this.sourceBoss = bossP;
		this.targetBoss = newBossP;
		this.sourceStoreId = storeIdP;
	}
	
	private Store getOldStore() {
		AccessTokenMgr.getInstance().setOpIdTL(sourceBoss.getId());
		Store store = StoreMgr.getInstance().get(sourceStoreId);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return store;
	}
	
	private long createNewStore(Store oldStore) {
		AccessTokenMgr.getInstance().setOpIdTL(targetBoss.getId());
		StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
		FastBeanCopyer.getInstance().copy(oldStore, formInfo);
		formInfo.setBossId(targetBoss.getId());
		Store tmpStore = StoreMgr.getInstance().openStore(formInfo);
		AccessTokenMgr.getInstance().removeOpIdTL();
		return tmpStore.getId();
	}
	
	private void uploadAlipayQrCode(long newStoreId, Store oldStore) {
		AccessTokenMgr.getInstance().setOpIdTL(targetBoss.getId());
		AlipayQrCodeApiData info = AlipayQrCodeApiData.newInstance();
		info.setStoreId(newStoreId);
		info.setAlipayImg(oldStore.getAlipayImg());
		StoreMgr.getInstance().uploadAlipayQrCode(info);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	private void uploadWechatQrCode(long newStoreId, Store oldStore) {
		AccessTokenMgr.getInstance().setOpIdTL(targetBoss.getId());
		WechatQrCodeApiData info2 = WechatQrCodeApiData.newInstance();
		info2.setStoreId(newStoreId);
		info2.setWechatImg(oldStore.getWechatImg());
		StoreMgr.getInstance().uploadWechatQrCode(info2);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
}
