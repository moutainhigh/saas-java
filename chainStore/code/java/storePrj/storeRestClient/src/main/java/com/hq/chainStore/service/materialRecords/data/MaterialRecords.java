package com.hq.chainStore.service.materialRecords.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataSyn.bs.IntfSynData;

@Table(name = "materialRecords")
public class MaterialRecords implements IntfSynData{
	
	@Id
	private long id;
	
	private long storeId;
	
	private String materialId;

	private float price;
	
	private int count;
	//对应进货人
	private String userName;
	//对应进货图片url
	private String imgUrl;
	
	private long createdTime;
	
	private long ver;
	
	public static MaterialRecords newInstance(){
		MaterialRecords materialRecords = new MaterialRecords();
		materialRecords.createdTime = System.currentTimeMillis();
		
		return materialRecords;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public long getVer() {
		return ver;
	}
	
	public void setVer(long ver) {
		this.ver = ver;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	@Override
	public long targetVer() {
		return this.ver;
	}
	
}
