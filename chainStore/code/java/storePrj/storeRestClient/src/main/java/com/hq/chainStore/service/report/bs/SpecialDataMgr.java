package com.hq.chainStore.service.report.bs;

import com.hq.chainStore.service.report.apiData.BeauticianSpecialData;
import com.hq.chainStore.service.report.apiData.CUserSpecialData;
import com.hq.chainStore.service.report.apiData.ProductSpecialData;
import com.hq.chainStore.service.report.apiData.UpdateSpecialDataApiForm;
import com.hq.chainStore.service.report.apiData.UpdateSpecialDataType;
import com.hq.chainStore.service.report.data.SpecialData;
import com.hq.chainStore.service.report.data.SpecialDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SpecialDataMgr {

	public static SpecialDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataMgr.class);
	}
	
	public SpecialData getSpecialData(String specialDataId) {
		return SpecialDataDAO.getInstance().get(specialDataId);
	}
	
	public void updateSpecialData(String specialDataId, UpdateSpecialDataApiForm updateForm) {
		SpecialDataDAO.getInstance().update(specialDataId, updateForm);
	}
	
	//====================具体的更新操作====================
	public void addBeauticianSpecialData(String specialDataId, BeauticianSpecialData data) {
		UpdateSpecialDataApiForm updateForm = UpdateSpecialDataApiForm.newInstance();
		updateForm.setBeauticianSpecialData(data);
		updateForm.setUpdateType(UpdateSpecialDataType.AddBeauticianSpecialData.ordinal());
		updateSpecialData(specialDataId, updateForm);
	}
	
	public void delBeauticianSpecialData(String specialDataId, BeauticianSpecialData data) {
		UpdateSpecialDataApiForm updateForm = UpdateSpecialDataApiForm.newInstance();
		updateForm.setBeauticianSpecialData(data);
		updateForm.setUpdateType(UpdateSpecialDataType.DelBeauticianSpecialData.ordinal());
		updateSpecialData(specialDataId, updateForm);
	}
	
	public void addProductSpecialData(String specialDataId, ProductSpecialData data) {
		UpdateSpecialDataApiForm updateForm = UpdateSpecialDataApiForm.newInstance();
		updateForm.setProductSpecialData(data);
		updateForm.setUpdateType(UpdateSpecialDataType.AddProductSpecialData.ordinal());
		updateSpecialData(specialDataId, updateForm);
	}
	
	public void delProductSpecialData(String specialDataId, ProductSpecialData data) {
		UpdateSpecialDataApiForm updateForm = UpdateSpecialDataApiForm.newInstance();
		updateForm.setProductSpecialData(data);
		updateForm.setUpdateType(UpdateSpecialDataType.DelProductSpecialData.ordinal());
		updateSpecialData(specialDataId, updateForm);
	}
	
	
	public void addCUserSpecialData(String specialDataId, CUserSpecialData data) {
		UpdateSpecialDataApiForm updateForm = UpdateSpecialDataApiForm.newInstance();
		updateForm.setCuserSpecialData(data);
		updateForm.setUpdateType(UpdateSpecialDataType.AddCUserSpecialData.ordinal());
		updateSpecialData(specialDataId, updateForm);
	}
	
	public void delCUserSpecialData(String specialDataId, CUserSpecialData data) {
		UpdateSpecialDataApiForm updateForm = UpdateSpecialDataApiForm.newInstance();
		updateForm.setCuserSpecialData(data);
		updateForm.setUpdateType(UpdateSpecialDataType.DelCUserSpecialData.ordinal());
		updateSpecialData(specialDataId, updateForm);
	}

}
