package com.hq.storeMS.service.store.bs.update;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.qrcode.bs.QrCodeMgr;
import com.hq.storeMS.service.store.apiData.AlipayQrCodeApiData;
import com.hq.storeMS.service.store.apiData.JoinStoreQrCodeApiData;
import com.hq.storeMS.service.store.apiData.StoreUpdateApiForm;
import com.hq.storeMS.service.store.apiData.StoreUpdateType;
import com.hq.storeMS.service.store.apiData.WechatQrCodeApiData;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreQrCodeMgr {

	public static StoreQrCodeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreQrCodeMgr.class);
	}
	
	public OperateTips genQrcode(StoreUpdateApiForm formInfo){
		OperateTips tips = OperateTips.newInstance(false, StoreUpdateType.JoinStoreQrCode.getDescript()+"失败");
		JoinStoreQrCodeApiData joinStoreQrCodeApiData = formInfo.getJoinStoreQrCodeApiData();
		String joinStoreImg;
		try {
			Store store = StoreMgr.getInstance().get(joinStoreQrCodeApiData.getStoreId());
			joinStoreImg = QrCodeMgr.getInstance().genJoinStoreQrCode(store.getId());
			store.setJoinStoreImg(joinStoreImg);
			StoreMgr.getInstance().update(store);
			tips.setSuccess(true);
		} catch (Exception e) {
			MainLog.error(LogModule.Store, "StoreQrCodeHandle[genQrcode]", "生成二维码失败", e);
		}
		return tips;
	}
	
	public OperateTips uploadAlipayQrCode(StoreUpdateApiForm formInfo){
		OperateTips tips = OperateTips.newInstance(false, StoreUpdateType.AlipayQrCodeApiData.getDescript()+"失败");
		AlipayQrCodeApiData alipayQrCodeApiData = formInfo.getAlipayQrCodeApiData();
		try {
			Store store = StoreMgr.getInstance().get(alipayQrCodeApiData.getStoreId());
			store.setAlipayImg(alipayQrCodeApiData.getAlipayImg());
			StoreMgr.getInstance().update(store);
			tips.setSuccess(true);
		} catch (Exception e) {
			MainLog.error(LogModule.Store, "StoreQrCodeHandle[uploadAlipayQrCode]", "保存支付宝二维码失败", e);
		}
		return tips;
	}
	
	public OperateTips uploadWechatQrCode(StoreUpdateApiForm formInfo){
		OperateTips tips = OperateTips.newInstance(false, StoreUpdateType.WechatQrCodeApiData.getDescript()+"失败");
		WechatQrCodeApiData wechatQrCodeApiData = formInfo.getWechatQrCodeApiData();
		try {
			Store store = StoreMgr.getInstance().get(wechatQrCodeApiData.getStoreId());
			store.setWechatImg(wechatQrCodeApiData.getWechatImg());
			StoreMgr.getInstance().update(store);
			tips.setSuccess(true);
		} catch (Exception e) {
			MainLog.error(LogModule.Store, "StoreQrCodeHandle[uploadWechatQrCode]", "保存微信二维码失败", e);
		}
		return tips;
	}
	
}
