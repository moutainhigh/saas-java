package com.hq.testClass.robot.storeCardInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeCardInfo.data.PrdInCard;

public class StoreCardInfoRobotData {
	// 随机数标记，用来生成电话号码等信息
	private int mark;

	private long storeId;
	private int type;//类型   
	private float sellPrice;
	private int time;//次数
	private List<PrdInCard> productList = new ArrayList<PrdInCard>();
	private int validPeriod;//有效期 eg：365日/12月/1年	
	private int validPeriodUnit;//有效期时间的单位 对应ValidPeriodUnitEnum	
	private String number;
	private float freeMoney;
	private float prodDiscount;
	private float goodsDiscount;
	private float prdCardDiscount;
	private String imgPath;
	private String name;
	private int status;
	private String descript;
	private long createdTime;
	private long lastUpdateTime;
	private int ver;
	
	/*****************************遗留字段*******************************/
	private int limitTime;
	private int cardType;
	private float percent;
	private float cash;
	private int consumeType;
	private int count;
	private long effectiveTime;
	private String backImg;


	public static StoreCardInfoRobotData newInstance(int mark) {
		StoreCardInfoRobotData data = new StoreCardInfoRobotData();
		data.name = "name" + mark;
		data.number = "cardNumber" + mark;
		data.descript = "description" + mark;
		data.imgPath = "img/product/140/2017_10_08_18_52f71779-29bd-45fd-bf72-c898bd9891d5.jpg";
		data.type = 0;
		data.validPeriod = RandomUtils.nextInt(1,20);
		data.validPeriodUnit = RandomUtils.nextInt(0,3);
		data.time = RandomUtils.nextInt(1,100);
		data.prodDiscount = RandomUtils.nextFloat(0.5f, 1.0f);
		data.goodsDiscount = RandomUtils.nextFloat(0.5f, 1.0f);
		data.prdCardDiscount = RandomUtils.nextFloat(0.5f, 1.0f);
		data.freeMoney = RandomUtils.nextInt(1000,10000);
		data.sellPrice = RandomUtils.nextInt(1000,10000);
		
		data.count = RandomUtils.nextInt(1, 20);
		PrdInCard prdInCard = PrdInCard.newInstance();
		        prdInCard.setPrdId(1l);
				prdInCard.setCount(data.count);
				prdInCard.setType(1);//限次数
		PrdInCard prdInCard1 = PrdInCard.newInstance();
		        prdInCard1.setPrdId(2l);
				prdInCard1.setCount(0);
				prdInCard1.setType(0);//永久
		data.productList.add(prdInCard);
		data.productList.add(prdInCard1);
		data.status = RandomUtils.nextInt(0, 3);
		return data;
	}
	
//	public static StoreCardInfoRobotData newInstance(int mark) {
//	StoreCardInfoRobotData data = new StoreCardInfoRobotData();
//	data.limitTime = RandomUtils.nextInt(0, 8);
//	data.name = "name" + mark;
//	data.descript = "description" + mark;
//	data.backImg = "img/product/140/2017_10_08_18_52f71779-29bd-45fd-bf72-c898bd9891d5.jpg";
//	data.cardType = RandomUtils.nextInt(1, 3);
//	data.percent = RandomUtils.nextFloat(0.5f, 1.0f);
//	data.cash = RandomUtils.nextFloat(3.0f, 5.0f);
//	data.consumeType = RandomUtils.nextInt(1, 3);
//	data.count = RandomUtils.nextInt(10, 30);
//	data.effectiveTime = System.currentTimeMillis() + 6L * 30 * 24 * 3600
//			* 1000;
//	data.status = RandomUtils.nextInt(0, 3);
//	return data;
//}


	public int getMark() {
		return mark;
	}


	public void setMark(int mark) {
		this.mark = mark;
	}


	public long getStoreId() {
		return storeId;
	}


	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public float getSellPrice() {
		return sellPrice;
	}


	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	public List<PrdInCard> getProductList() {
		return productList;
	}


	public void setProductList(List<PrdInCard> productList) {
		this.productList = productList;
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


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public float getFreeMoney() {
		return freeMoney;
	}


	public void setFreeMoney(float freeMoney) {
		this.freeMoney = freeMoney;
	}


	public float getProdDiscount() {
		return prodDiscount;
	}


	public void setProdDiscount(float prodDiscount) {
		this.prodDiscount = prodDiscount;
	}


	public float getGoodsDiscount() {
		return goodsDiscount;
	}


	public void setGoodsDiscount(float goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}


	public float getPrdCardDiscount() {
		return prdCardDiscount;
	}


	public void setPrdCardDiscount(float prdCardDiscount) {
		this.prdCardDiscount = prdCardDiscount;
	}


	public String getImgPath() {
		return imgPath;
	}


	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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


	public String getDescript() {
		return descript;
	}


	public void setDescript(String descript) {
		this.descript = descript;
	}


	public long getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}


	public long getLastUpdateTime() {
		return lastUpdateTime;
	}


	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}


	public int getVer() {
		return ver;
	}


	public void setVer(int ver) {
		this.ver = ver;
	}


	public int getLimitTime() {
		return limitTime;
	}


	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}


	public int getCardType() {
		return cardType;
	}


	public void setCardType(int cardType) {
		this.cardType = cardType;
	}


	public float getPercent() {
		return percent;
	}


	public void setPercent(float percent) {
		this.percent = percent;
	}


	public float getCash() {
		return cash;
	}


	public void setCash(float cash) {
		this.cash = cash;
	}


	public int getConsumeType() {
		return consumeType;
	}


	public void setConsumeType(int consumeType) {
		this.consumeType = consumeType;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public long getEffectiveTime() {
		return effectiveTime;
	}


	public void setEffectiveTime(long effectiveTime) {
		this.effectiveTime = effectiveTime;
	}


	public String getBackImg() {
		return backImg;
	}


	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}
	


	
}
