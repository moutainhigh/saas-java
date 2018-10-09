package com.hq.chainClient.testClass.robot.chainProduct;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainGoods.data.GoodsStateEnum;
import com.hq.chainClient.service.chainProduct.apiData.AddProductInfoData;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChainProductRobotData {
	private long index;
	private String number;// 编号
	private String name;
	private String typeId;
	private float sellPrice;
	private int state;
	private String defaultImg;

	private float cost;// 成本
	private String descript;
	private List<String> imgPathList = new ArrayList<String>();

	public static ChainProductRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		ChainProductRobotData data = new ChainProductRobotData();
		data.name = "智美连锁店" + random;
		data.descript = "智美连锁店" + random;
		data.number = "number"+random;
		data.sellPrice = RandomUtils.nextFloat(100f, 1000f);
		data.cost = RandomUtils.nextFloat(100f, data.sellPrice);
		data.state = GoodsStateEnum.Open.ordinal();
		data.defaultImg = "defaultImg"+random;
		data.imgPathList.add("imgPath"+random);
		return data;
	}

	public AddProductInfoData toAddProductInfoData(long index, String typeId) {
		AddProductInfoData data = AddProductInfoData.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setIndex(index);
		data.setTypeId(typeId);
		return data;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
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

	public List<String> getImgPathList() {
		return imgPathList;
	}

	public void setImgPathList(List<String> imgPathList) {
		this.imgPathList = imgPathList;
	}
}
