package com.hq.chainStore.service.storeConfig.bs;

import com.hq.chainStore.service.storeConfig.apiData.AppointTimeUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.BaseAttributeStatusForm;
import com.hq.chainStore.service.storeConfig.apiData.CancelAppointAddForm;
import com.hq.chainStore.service.storeConfig.apiData.CancelAppointRemoveForm;
import com.hq.chainStore.service.storeConfig.apiData.CancelAppointUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.ExpandAttributeAddForm;
import com.hq.chainStore.service.storeConfig.apiData.ExpandAttributeSortForm;
import com.hq.chainStore.service.storeConfig.apiData.ExpandAttributeStatusForm;
import com.hq.chainStore.service.storeConfig.apiData.ExpandAttributeUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.LeaguerOriginAddForm;
import com.hq.chainStore.service.storeConfig.apiData.LeaguerOriginRemoveForm;
import com.hq.chainStore.service.storeConfig.apiData.LeaguerOriginUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.LeaguerTypeUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.StoreConfigUpdateForm;
import com.hq.chainStore.service.storeConfig.apiData.StoreConfigUpdateType;
import com.hq.chainStore.service.storeConfig.data.StoreConfig;
import com.hq.chainStore.service.storeConfig.data.StoreConfigDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreConfigMgr {

	public static StoreConfigMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreConfigMgr.class);
	}

	public StoreConfig findStoreConfigByStoreId(long storeId) {
		return StoreConfigDAO.getInstance().get(storeId);
	}
	
	private void update(long storeId, StoreConfigUpdateForm updateForm) {
		StoreConfigDAO.getInstance().update(storeId, updateForm);
	}
	
	//新增来源
	public void addLeaguerOrigin(long storeId, LeaguerOriginAddForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.AddLeaguerOrigin.ordinal());
		updateForm.setLeaguerOriginAddForm(inputForm);
		update(storeId, updateForm);
	}
	
	//删除来源
	public void removeLeaguerOrigin(long storeId, LeaguerOriginRemoveForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.RemoveLeaguerOrigin.ordinal());
		updateForm.setLeaguerOriginRemoveForm(inputForm);
		update(storeId, updateForm);
	}
	
	//编辑来源
	public void updateLeaguerOrigin(long storeId, LeaguerOriginUpdateForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.UpdateLeaguerOrigin.ordinal());
		updateForm.setLeaguerOriginUpdateForm(inputForm);
		update(storeId, updateForm);
	}
	
	//新增客户扩展属性
	public void addExpandAttribute(long storeId, ExpandAttributeAddForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.AddExpandAttribute.ordinal());
		updateForm.setExpandAttributeAddForm(inputForm);
		update(storeId, updateForm);
	}
	
	//客户扩展属性升降序
	public void sortExpandAttribute(long storeId, ExpandAttributeSortForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.SortExpandAttribute.ordinal());
		updateForm.setExpandAttributeSortForm(inputForm);
		update(storeId, updateForm);
	}
	
	//必填 启用项
	public void statusExpandAttribute(long storeId, ExpandAttributeStatusForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.StatusExpandAttribute.ordinal());
		updateForm.setExpandAttributeStatusForm(inputForm);
		update(storeId, updateForm);
	}
	
	//修改客户扩展属性
	public void updateExpandAttribute(long storeId, ExpandAttributeUpdateForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.UpdateExpandAttribute.ordinal());
		updateForm.setExpandAttributeUpdateForm(inputForm);
		update(storeId, updateForm);
	}
	
	//设置客户类型
	public void updateLeaguerType(long storeId, LeaguerTypeUpdateForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.UpdateLeaguerType.ordinal());
		updateForm.setLeaguerTypeUpdateForm(inputForm);
		update(storeId, updateForm);
	}
	
	//设置客户基础属性
	public void updateBaseAttribute(long storeId, BaseAttributeStatusForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.UpdateBaseAttribute.ordinal());
		updateForm.setBaseAttributeStatusForm(inputForm);
		update(storeId, updateForm);
	}
	
	//新增取消预约的原因
	public void addCancelReason(long storeId, CancelAppointAddForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.AddCancelReason.ordinal());
		updateForm.setCancelAppointAddForm(inputForm);
		update(storeId, updateForm);
	}
	
	//删除取消预约的原因
	public void removeCancelReason(long storeId, CancelAppointRemoveForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.RemoveCancelReason.ordinal());
		updateForm.setCancelAppointRemoveForm(inputForm);
		update(storeId, updateForm);
	}
	
	//编辑取消预约的原因
	public void updateCancelReason(long storeId, CancelAppointUpdateForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.UpdateCancelReason.ordinal());
		updateForm.setCancelAppointUpdateForm(inputForm);
		update(storeId, updateForm);
	}
	
	//设置预约的时间段
	public void updateAppointTime(long storeId, AppointTimeUpdateForm inputForm){
		StoreConfigUpdateForm updateForm = StoreConfigUpdateForm.newInstance();
		updateForm.setUpdateType(StoreConfigUpdateType.UpdateAppointTime.ordinal());
		updateForm.setAppointTimeUpdateForm(inputForm);
		update(storeId, updateForm);
	}
	
}
