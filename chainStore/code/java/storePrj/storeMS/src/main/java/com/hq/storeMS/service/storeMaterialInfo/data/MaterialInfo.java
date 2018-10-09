package com.hq.storeMS.service.storeMaterialInfo.data;

import javax.persistence.Id;

import com.hq.storeMS.service.storeMaterialInfo.apiData.AddMaterialInfoForm;
import com.hq.storeMS.service.storeMaterialInfo.apiData.UpdateMaterialInfoForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class MaterialInfo {
	
	@Id
	private String id;

	private String name;
	
	//参考价格
	private float referencePrice;
	
	//库存
	private int inventory;
	
	//阈值、库存临界值
	private int threshold;
	
	private String imgUrl;
	
	private long createdTime;
	
	public static MaterialInfo newInstance(String id){
		MaterialInfo materialInfo = new MaterialInfo();
		materialInfo.id = id;
		materialInfo.createdTime = System.currentTimeMillis();
		return materialInfo;
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

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
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

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	
	public void addInventory(int count) {
		this.inventory += count;
	}
	
	public void removeInventory(int count) {
		int n = this.inventory - count;
		this.inventory = n < 0 ? 0 : n;
	}
	
	public void toMaterial(AddMaterialInfoForm addForm){
		FastBeanCopyer.getInstance().copy(addForm, this);
	}
	
	public void updateInfo(UpdateMaterialInfoForm updateForm){
		FastBeanCopyer.getInstance().copy(updateForm, this);
	}
	
}
