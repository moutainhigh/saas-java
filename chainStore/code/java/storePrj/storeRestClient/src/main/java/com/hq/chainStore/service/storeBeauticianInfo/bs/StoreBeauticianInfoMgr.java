package com.hq.chainStore.service.storeBeauticianInfo.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.storeBeauticianInfo.apiData.AddBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.RemoveBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateForm;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateType;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.UpdateBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianInfo;
import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoMgr {

	public static StoreBeauticianInfoMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoMgr.class);
	}

	public StoreBeauticianInfo get(long id) {
		return StoreBeauticianInfoDAO.getInstance().get(id);
	}
	
	public void update(long id, StoreBeauticianInfoUpdateForm updateForm) {
		StoreBeauticianInfoDAO.getInstance().update(id, updateForm);
	}
	
	public void addBeauticianInfoData(long storeId, AddBeauticianInfoData addBeauticianInfoData){
		StoreBeauticianInfoUpdateForm updateForm = StoreBeauticianInfoUpdateForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreBeauticianInfoUpdateType.AddBeauticianInfo.ordinal());
		updateForm.setAddBeauticianInfoData(addBeauticianInfoData);
		update(storeId, updateForm);
	}
	
	public void updateBeauticianInfoData(long storeId, UpdateBeauticianInfoData updateBeauticianInfoData){
		StoreBeauticianInfoUpdateForm updateForm = StoreBeauticianInfoUpdateForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreBeauticianInfoUpdateType.UpdateBeauticianInfo.ordinal());
		updateForm.setUpdateBeauticianInfoData(updateBeauticianInfoData);
		update(storeId, updateForm);
	}
	
	public void removeBeauticianInfoData(long storeId, RemoveBeauticianInfoData removeBeauticianInfoData){
		StoreBeauticianInfoUpdateForm updateForm = StoreBeauticianInfoUpdateForm.newInstance();
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(StoreBeauticianInfoUpdateType.RemoveBeauticianInfo.ordinal());
		updateForm.setRemoveBeauticianInfoData(removeBeauticianInfoData);
		update(storeId, updateForm);
	}
	
	/***********************beauticianInfo*************************/

	public BeauticianInfo getBeautician(long id,long beauticianId) {
		BeauticianInfo beautician = null;
		StoreBeauticianInfo storeBeauticianInfo = StoreBeauticianInfoDAO.getInstance().get(id);
		if(storeBeauticianInfo != null){
			Map<Long, BeauticianInfo> beauticianInfoMap = storeBeauticianInfo.getBeauticianInfoMap();
			if(beauticianInfoMap.containsKey(beauticianId)){
				beautician = beauticianInfoMap.get(beauticianId);
			}
		}
		return beautician;
	}
	
	public List<BeauticianInfo> getBeauticianList(long id) {
		List<BeauticianInfo> beauticianList = null;
		StoreBeauticianInfo storeBeauticianInfo = StoreBeauticianInfoDAO.getInstance().get(id);
		if(storeBeauticianInfo != null){
			Map<Long, BeauticianInfo> beauticianInfoMap = storeBeauticianInfo.getBeauticianInfoMap();
			Collection<BeauticianInfo> collection = beauticianInfoMap.values();
			beauticianList = new ArrayList<BeauticianInfo>(collection);
		}
		return beauticianList;
	}
	
}
