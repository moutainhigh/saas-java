package com.hq.storeMS.service.specialData.apiData;


public class UpdateSpecialDataApiForm {
	private int updateType;

	private BeauticianSpecialData beauticianSpecialData;
	private CUserSpecialData cuserSpecialData;
	private ProductSpecialData productSpecialData;

	public static UpdateSpecialDataApiForm newInstance() {
		return new UpdateSpecialDataApiForm();
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public BeauticianSpecialData getBeauticianSpecialData() {
		return beauticianSpecialData;
	}

	public void setBeauticianSpecialData(BeauticianSpecialData beauticianSpecialData) {
		this.beauticianSpecialData = beauticianSpecialData;
	}

	public CUserSpecialData getCuserSpecialData() {
		return cuserSpecialData;
	}

	public void setCuserSpecialData(CUserSpecialData cuserSpecialData) {
		this.cuserSpecialData = cuserSpecialData;
	}

	public ProductSpecialData getProductSpecialData() {
		return productSpecialData;
	}

	public void setProductSpecialData(ProductSpecialData productSpecialData) {
		this.productSpecialData = productSpecialData;
	}

}
