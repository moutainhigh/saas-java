package com.hq.storeMS.service.storeVipType.data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeVipType")
public class StoreVipType {
	
	@Id
	private long id;
	//会员级别 对应 StoreVipLevelEnum
	@IndexField
	private int level;
	//对应名称
	@IndexField
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
	
	private long createdTime;
	private long lastUpdateTime;
	private long ver;
	
	public static StoreVipType newInstance(int storeVipLevel){
		StoreVipType storeVipType =  new StoreVipType();
		storeVipType.id = storeVipLevel+1;
		storeVipType.level = storeVipLevel;
		storeVipType.name = StoreVipLevelEnum.valueOf(storeVipLevel).getMark();
		storeVipType.descript = StoreVipLevelEnum.valueOf(storeVipLevel).getMark();
		switch (storeVipLevel) {
		case 0:
			storeVipType.price = 0;
			storeVipType.period = 30;
			storeVipType.storeLimit = 100;
			storeVipType.buserLimit = 100;
			storeVipType.leaguerLimit = 500;
			storeVipType.chainLimit = 0;
			break;
		case 1:
			storeVipType.price = 0;
			storeVipType.period = 365;
			storeVipType.storeLimit = 100;
			storeVipType.buserLimit = 100;
			storeVipType.leaguerLimit = 500;
			storeVipType.chainLimit = 0;
			break;
		default:
			storeVipType.price = 0;
			storeVipType.period = 0;
			storeVipType.storeLimit = 100;
			storeVipType.buserLimit = 100;
			storeVipType.leaguerLimit = 500;
			storeVipType.chainLimit = 0;
			break;
		}
		
		long currentTimeMillis = System.currentTimeMillis();
		
		storeVipType.createdTime = currentTimeMillis;
		storeVipType.lastUpdateTime = currentTimeMillis;
		return storeVipType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}
	
	public void incrVer() {
		this.ver = ver + 1;
	}
	
	
}
