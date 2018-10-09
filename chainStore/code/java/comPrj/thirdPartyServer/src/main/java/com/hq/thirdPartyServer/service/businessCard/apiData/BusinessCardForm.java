package com.hq.thirdPartyServer.service.businessCard.apiData;


public class BusinessCardForm {
	private String imageBinary;

	public static BusinessCardForm newInstance() {
		return new BusinessCardForm();
	}
	
	public String getImageBinary() {
		return imageBinary;
	}

	public void setImageBinary(String imageBinary) {
		this.imageBinary = imageBinary;
	}
}
