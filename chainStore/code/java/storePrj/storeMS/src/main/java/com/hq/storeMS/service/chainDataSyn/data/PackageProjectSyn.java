package com.hq.storeMS.service.chainDataSyn.data;

import com.hq.chainClient.service.chainPackageProject.data.PackageProject;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class PackageProjectSyn {
	private long storeId;
	private long chainId;

	private String id;
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
	
	// 同步状态 对应 ChainDataStatusEnum
	private int synStatus;

	public static PackageProjectSyn newInstance() {
		return new PackageProjectSyn();
	}
	
	public static PackageProjectSyn newInstanceByPackageProject(PackageProject data, long storeId, long chainId) {
		PackageProjectSyn rs = newInstance();
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

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public int getSynStatus() {
		return synStatus;
	}

	public void setSynStatus(int synStatus) {
		this.synStatus = synStatus;
	}
}
