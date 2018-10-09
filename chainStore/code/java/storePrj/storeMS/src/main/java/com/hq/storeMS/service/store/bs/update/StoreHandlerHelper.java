package com.hq.storeMS.service.store.bs.update;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.store.apiData.StoreUpdateApiForm;
import com.hq.storeMS.service.store.apiData.StoreUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreHandlerHelper{

	public static StoreHandlerHelper getInstance(){
		return HotSwap.getInstance().getSingleton(StoreHandlerHelper.class);
	}
	
	private Map<StoreUpdateType,IStoreHandler> handleMapper = new HashMap<StoreUpdateType,IStoreHandler>();
	
	public StoreHandlerHelper(){
		handleMapper.put(StoreUpdateType.UpdateStoreInfo, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return StoreUpdateMgr.getInstance().updateStoreInfo(formInfo);
			}
		});
		handleMapper.put(StoreUpdateType.StoreUpdateStatusData, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return StoreUpdateMgr.getInstance().updateStatus(formInfo);
			}
		});
		handleMapper.put(StoreUpdateType.Submit4Check, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreUpdateType.JoinStoreQrCode, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return StoreQrCodeMgr.getInstance().genQrcode(formInfo);
			}
		});
		handleMapper.put(StoreUpdateType.AlipayQrCodeApiData, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return StoreQrCodeMgr.getInstance().uploadAlipayQrCode(formInfo);
			}
		});
		handleMapper.put(StoreUpdateType.WechatQrCodeApiData, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return StoreQrCodeMgr.getInstance().uploadWechatQrCode(formInfo);
			}
		});
		handleMapper.put(StoreUpdateType.StoreUpdateChainData, new IStoreHandler() {
			@Override
			public OperateTips update(StoreUpdateApiForm formInfo) {
				return StoreUpdateMgr.getInstance().updateStoreChainData(formInfo.getUpdateChainData());
			}
		});
	}
	
	public OperateTips update(StoreUpdateApiForm formInfo){
		StoreUpdateType updateType = StoreUpdateType.valueOf(formInfo.getUpdateType());
		IStoreHandler handle = handleMapper.get(updateType);
		if(handle != null){
			return handle.update(formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
	
}
