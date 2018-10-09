package com.hq.chainStore.service.storeMaterialInfo.apiData;

public class UpdateMaterialInfoForm {
	
	private String id;

	private String name;
	
	//参考价格
	private float referencePrice;
	
	//阈值、库存临界值
	private int threshold;
	
	private String imgUrl;
	
	public static UpdateMaterialInfoForm newInstance(){
		return new UpdateMaterialInfoForm();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(float referencePrice) {
		this.referencePrice = referencePrice;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
