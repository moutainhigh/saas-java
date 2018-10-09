package com.hq.chainClient.testClass.robot.chainCard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainClient.service.chainCard.apiData.AddProductCardForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ProductCardRobotData {
	private int index;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 状态 对应 CardStatusEnum
	private int status;
	// 售价
	private float sellPrice;
	// 默认图片
	private String imgPath;
	// 有效期 eg：365日/12月/1年
	private int validPeriod;
	// 有效期时间的单位 对应 ValidPeriodUnitEnum
	private int validPeriodUnit;

	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();

	public static ProductCardRobotData newRandomInstance() {
		int random = RandomUtils.nextInt(1, 100);
		ProductCardRobotData data = new ProductCardRobotData();
		data.name = "次卡" + random;
		data.descript = "次卡" + random;
		data.number = "number" + random;
		data.sellPrice = RandomUtils.nextFloat(100f, 1000f);
		data.validPeriod = RandomUtils.nextInt(1, 10);
		data.status = 0;
		data.imgPath = "defaultImg" + random;
		data.validPeriodUnit=RandomUtils.nextInt(1, 4);
		data.imgPaths.add("defaultImg" + random);
		return data;
	}

	public AddProductCardForm toAddProductCardForm(int index, String typeId) {
		AddProductCardForm data = AddProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, data);
		data.setIndex(index);
		data.setTypeId(typeId);
		return data;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getValidPeriod() {
		return validPeriod;
	}

	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}

	public int getValidPeriodUnit() {
		return validPeriodUnit;
	}

	public void setValidPeriodUnit(int validPeriodUnit) {
		this.validPeriodUnit = validPeriodUnit;
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
