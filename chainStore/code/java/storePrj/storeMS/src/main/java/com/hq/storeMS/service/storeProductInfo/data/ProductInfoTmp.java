package com.hq.storeMS.service.storeProductInfo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ProductInfoTmp {

	@Id
	private long id;

	private String number;

	private String name;

	private String typeName;

	private float price;

	private float cost;

	private int state;

	private String descript;

	private List<String> imgPathList = new ArrayList<String>();

	public static ProductInfoTmp newInstance() {
		return new ProductInfoTmp();

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}

	public void addImgPath(String imgPath) {
		this.imgPathList.add(imgPath);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


}
