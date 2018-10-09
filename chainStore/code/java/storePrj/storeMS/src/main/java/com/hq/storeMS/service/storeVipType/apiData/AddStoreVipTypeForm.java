package com.hq.storeMS.service.storeVipType.apiData;

import com.hq.storeMS.service.storeVipType.data.StoreVipType;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class AddStoreVipTypeForm {
	
	//会员级别 对应StoreVipLevelEnum
	private int level;
	//对应名称
	private String name;
	//单位有效期对应价格
	private float price;
	//有效期时间 单位为天
	private int period;
	//店铺数
	private int storeLimit;
	//店员数
	private int buserLimit;
	//客户数
	private int leaguerLimit;
	//连锁店数
	private int chainLimit;
	//备注
	private String descript;

	public static AddStoreVipTypeForm newInstance(){
		return new AddStoreVipTypeForm();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getStoreLimit() {
		return storeLimit;
	}

	public void setStoreLimit(int storeLimit) {
		this.storeLimit = storeLimit;
	}

	public int getBuserLimit() {
		return buserLimit;
	}

	public void setBuserLimit(int buserLimit) {
		this.buserLimit = buserLimit;
	}

	public int getLeaguerLimit() {
		return leaguerLimit;
	}

	public void setLeaguerLimit(int leaguerLimit) {
		this.leaguerLimit = leaguerLimit;
	}

	public int getChainLimit() {
		return chainLimit;
	}

	public void setChainLimit(int chainLimit) {
		this.chainLimit = chainLimit;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public StoreVipType toStoreVipType(){
		StoreVipType storeVipType = StoreVipType.newInstance(this.level);
		FastBeanCopyer.getInstance().copy(this, storeVipType);
		return storeVipType;
	}

}
