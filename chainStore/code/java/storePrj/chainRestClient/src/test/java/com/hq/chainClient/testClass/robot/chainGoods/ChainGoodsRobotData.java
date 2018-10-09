package com.hq.chainClient.testClass.robot.chainGoods;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainGoods.apiData.GoodsAddForm;
import com.hq.chainClient.service.chainGoods.data.GoodsStateEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ChainGoodsRobotData {
	private long index;
	// 商品编号
	private String number;
	// 名称
	private String name;
	// 商品分类Id
	private String typeId;
	// 售价
	private float sellPrice;
	// 默认图片
	private String defaultImg;
	// 成本
	private float cost;
	// 状态 对应 GoodsStateEnum
	private int state;
	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static ChainGoodsRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		ChainGoodsRobotData data = new ChainGoodsRobotData();
		data.name = "智美连锁店" + random;
		data.descript = "智美连锁店" + random;
		data.number = "number"+random;
		data.sellPrice = RandomUtils.nextFloat(100f, 1000f);
		data.cost = RandomUtils.nextFloat(100f, data.sellPrice);
		data.state = GoodsStateEnum.Open.ordinal();
		data.defaultImg = "defaultImg"+random;
		data.imgPaths.add("imgPath"+random);
		return data;
	}

	public GoodsAddForm toGoodsAddForm(long index, String typeId) {
		GoodsAddForm data = GoodsAddForm.newInstance();
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
