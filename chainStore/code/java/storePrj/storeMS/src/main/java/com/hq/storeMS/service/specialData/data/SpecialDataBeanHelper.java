package com.hq.storeMS.service.specialData.data;

import com.hq.storeMS.service.specialData.apiData.BeauticianSpecialData;
import com.hq.storeMS.service.specialData.apiData.CUserSpecialData;
import com.hq.storeMS.service.specialData.apiData.ProductSpecialData;
import com.zenmind.common.hotSwap.HotSwap;

public class SpecialDataBeanHelper {

	public static SpecialDataBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(SpecialDataBeanHelper.class);
	}
	
	public boolean addBeauticianSpecialData(SpecialData specialData, BeauticianSpecialData data){
		if (!specialData.getBeauticianIds().contains(data.getBeauticianId())) {
			specialData.getBeauticianIds().add(data.getBeauticianId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addProductSpecialData(SpecialData specialData, ProductSpecialData data){
		if (!specialData.getProductIds().contains(data.getProductId())) {
			specialData.getProductIds().add(data.getProductId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addCUserSpecialData(SpecialData specialData, CUserSpecialData data){
		if (!specialData.getCuserIds().contains(data.getCuserId())) {
			specialData.getCuserIds().add(data.getCuserId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delBeauticianSpecialData(SpecialData specialData, BeauticianSpecialData data){
		if (specialData.getBeauticianIds().contains(data.getBeauticianId())) {
			specialData.getBeauticianIds().remove(data.getBeauticianId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delProductSpecialData(SpecialData specialData, ProductSpecialData data){
		if (specialData.getProductIds().contains(data.getProductId())) {
			specialData.getProductIds().remove(data.getProductId());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delCUserSpecialData(SpecialData specialData, CUserSpecialData data){
		if (specialData.getCuserIds().contains(data.getCuserId())) {
			specialData.getCuserIds().remove(data.getCuserId());
			return true;
		} else {
			return false;
		}
	}
	
}
