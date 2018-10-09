package com.hq.chainStore.service.store.bs;

import java.util.List;

import com.hq.chainStore.service.store.apiData.AlipayQrCodeApiData;
import com.hq.chainStore.service.store.apiData.JoinStoreQrCodeApiData;
import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.apiData.StoreUpdateApiForm;
import com.hq.chainStore.service.store.apiData.StoreUpdateStatusData;
import com.hq.chainStore.service.store.apiData.StoreUpdateType;
import com.hq.chainStore.service.store.apiData.Submit4CheckApiData;
import com.hq.chainStore.service.store.apiData.UpdateStoreInfoApiData;
import com.hq.chainStore.service.store.apiData.WechatQrCodeApiData;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.store.data.StoreDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class StoreMgr {

	public static StoreMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreMgr.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public Store add(Store target) {
		return StoreDAO.getInstance().add(target);
	}

	public Store get(long id) {
		return StoreDAO.getInstance().get(id);
	}

	
	public List<Store> findBUserStores(long buserId, int pageItemCount, int pageNo ,int findType) {

		final String findPath = "findByBuser";
		ReqMap reqMap = ReqMap.newInstance().add("buserId", buserId).add("findType", findType);
		return StoreDAO.getInstance().findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
	}
	public List<Store> findByName(String name, int pageItemCount, int pageNo ) {
		
		final String findPath = "findByName";
		ReqMap reqMap = ReqMap.newInstance().add("name", name);
		return StoreDAO.getInstance().findWithReqParam(findPath, reqMap, pageItemCount, pageNo);
	}


	public void updateStoreInfo(long storeId,UpdateStoreInfoApiData inputData) {
		StoreUpdateApiForm storeUpdateApiForm = StoreUpdateApiForm.newInstance();
		storeUpdateApiForm.setUpdateTypeEnum(StoreUpdateType.UpdateStoreInfo);
		storeUpdateApiForm.setUpdateStoreInfoApiData(inputData);
		
		update(storeId, storeUpdateApiForm);
	}
	
	/**
	 * 提交审核
	 * @param storeId
	 * @param inputData
	 */
	public void submit4Check(long storeId){
		StoreUpdateApiForm storeUpdateApiForm = StoreUpdateApiForm.newInstance();
		storeUpdateApiForm.setUpdateTypeEnum(StoreUpdateType.Submit4Check);
		
		Submit4CheckApiData submit4CheckApiData = Submit4CheckApiData.newInstance();
		submit4CheckApiData.setStoreId(storeId);
		storeUpdateApiForm.setSubmit4CheckApiData(submit4CheckApiData );
		update(storeId, storeUpdateApiForm);
	}
	
	/**
	 * 生成二维码
	 * @param storeId
	 * @param inputData
	 */
	public void genJoinStoreQrCode(long storeId){
		StoreUpdateApiForm storeUpdateApiForm = StoreUpdateApiForm.newInstance();
		storeUpdateApiForm.setUpdateTypeEnum(StoreUpdateType.JoinStoreQrCode);
		JoinStoreQrCodeApiData joinStoreQrCodeApiData = JoinStoreQrCodeApiData.newInstance();
		joinStoreQrCodeApiData.setStoreId(storeId);
		storeUpdateApiForm.setJoinStoreQrCodeApiData(joinStoreQrCodeApiData);
		update(storeId, storeUpdateApiForm);
	}
	
	public void uploadAlipayQrCode(AlipayQrCodeApiData info){
		StoreUpdateApiForm storeUpdateApiForm = StoreUpdateApiForm.newInstance();
		storeUpdateApiForm.setUpdateTypeEnum(StoreUpdateType.AlipayQrCodeApiData);
		storeUpdateApiForm.setAlipayQrCodeApiData(info);
		update(info.getStoreId(), storeUpdateApiForm);
	}
	
	public void uploadWechatQrCode(WechatQrCodeApiData info){
		StoreUpdateApiForm storeUpdateApiForm = StoreUpdateApiForm.newInstance();
		storeUpdateApiForm.setUpdateTypeEnum(StoreUpdateType.WechatQrCodeApiData);
		storeUpdateApiForm.setWechatQrCodeApiData(info);
		update(info.getStoreId(), storeUpdateApiForm);
	}
	
	public void updateStatus(StoreUpdateStatusData updateStatusData){
		StoreUpdateApiForm storeUpdateApiForm = StoreUpdateApiForm.newInstance();
		storeUpdateApiForm.setUpdateTypeEnum(StoreUpdateType.StoreUpdateStatusData);
		storeUpdateApiForm.setUpdateStatusData(updateStatusData);
		update(updateStatusData.getStoreId(), storeUpdateApiForm);
	}
	
	private void update(long storeId, StoreUpdateApiForm updateForm) {
		StoreDAO.getInstance().update(storeId, updateForm);
	}

	public Store openStore(StoreAddApiForm formInfo) {
		Store store = StoreDAO.getInstance().add(formInfo);
		return store;
	}
}
