package com.hq.chainClient.testClass.robot.chainPackageProject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainGoods.data.GoodsStateEnum;
import com.hq.chainClient.service.chainPackageProject.apiData.PackageProjectAddForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChainPackageProjectRobotData {
	private long index;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态 对应PackageStateEnum
	private int state;
	// 售价
	private float sellPrice;
	// 成本
	private float cost;
	// 默认图片
	private String defaultImg;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static ChainPackageProjectRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		ChainPackageProjectRobotData data = new ChainPackageProjectRobotData();
		data.name = "套餐名称" + random;
		data.descript = "套餐描述" + random;
		data.number = "number"+random;
		data.sellPrice = RandomUtils.nextFloat(100f, 1000f);
		data.cost = RandomUtils.nextFloat(100f, data.sellPrice);
		data.state = GoodsStateEnum.Open.ordinal();
		data.defaultImg = "defaultImg"+random;
		data.imgPaths.add("imgPath"+random);
		return data;
	}

	public PackageProjectAddForm toPackageProjectAddForm(long index, String typeId) {
		PackageProjectAddForm data = PackageProjectAddForm.newInstance();
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

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}
}
