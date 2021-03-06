package com.hq.storeMS.service.chainDataSyn.data;

import com.hq.chainClient.service.chainCard.data.ProductCard;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class ProductCardSyn {
	private long storeId;
	private long chainId;

	private String id;
	// 编号
	private String number;
	// 名称
	private String name;
	// 分类Id
	private String typeId;
	// 售价
	private float sellPrice;
	// 默认图片
	private String imgPath;
	// 有效期 eg：365日/12月/1年
	private int validPeriod;
	// 有效期时间的单位 对应 ValidPeriodUnitEnum
	private int validPeriodUnit;
	
	// 同步状态 对应 ChainDataStatusEnum
	private int synStatus;

	public static ProductCardSyn newInstance() {
		return new ProductCardSyn();
	}
	
	public static ProductCardSyn newInstanceByProductCard(ProductCard data, long storeId, long chainId) {
		ProductCardSyn rs = newInstance();
		FastBeanCopyer.getInstance().copy(data, rs);
		rs.storeId=storeId;
		rs.chainId=chainId;
		return rs;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getSynStatus() {
		return synStatus;
	}

	public void setSynStatus(int synStatus) {
		this.synStatus = synStatus;
	}
}
