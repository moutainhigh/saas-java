package com.hq.testClass.robot.storeCardInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.productCardDetail.data.ProductCardItem;
import com.hq.chainStore.service.storeCardInfo.apiData.AddProductCardForm;
import com.hq.chainStore.service.storeCardInfo.apiData.UpdProductCardForm;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class ProductCardDetailRandomData {
	private int index;
	// 编号
	private String number;
	// 名称
	private String name;
	// 状态 对应CardStatusEnum
	private int status;
	// 售价
	private float sellPrice;
	// 默认图片
	private String imgPath;
	// 有效期 eg：365日/12月/1年
	private int validPeriod;
	// 有效期时间的单位 对应ValidPeriodUnitEnum
	private int validPeriodUnit;

	// 描述
	private String descript;
	// 图片列表
	private List<String> imgPaths = new ArrayList<String>();
	// 次卡内容
	private List<ProductCardItem> productCardItems = new ArrayList<ProductCardItem>();

	public static ProductCardDetailRandomData newInstance(int index) {
		ProductCardDetailRandomData data = new ProductCardDetailRandomData();
		data.index=index;
		data.descript="descript"+index;
		data.imgPath="imgPath"+index;
		data.imgPaths.add("imgPath"+index);
		data.name="name"+index;
		data.number="number"+index;
		data.sellPrice=RandomUtils.nextFloat(100f, 1000f);
		data.status = 2;
		data.validPeriod = 10;
		data.validPeriodUnit = 1;
		return data;
	}
	
	public AddProductCardForm toAddProductCardForm() {
		AddProductCardForm form = AddProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, form);
		return form;
	}
	
	public UpdProductCardForm toUpdProductCardForm() {
		UpdProductCardForm form = UpdProductCardForm.newInstance();
		FastBeanCopyer.getInstance().copy(this, form);
		return form;
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

	public List<ProductCardItem> getProductCardItems() {
		return productCardItems;
	}

	public void setProductCardItems(List<ProductCardItem> productCardItems) {
		this.productCardItems = productCardItems;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
