package com.hq.experienceData;

import java.util.List;

/**
 * 店铺实体  按模块生成需要的数据，暂时不用统一生成。
 * 暂时用不上这个实体
 */
public class TStore {
	// 老板
	private TBoss boss;
	//店铺
	private List<TStoreData> stores;
	// 店长
	private List<TShopKeeper> keepers;
	// 店员
	private List<TClerk> clerks;
	// 申请员工
	private List<TApplyClerk> applyClerks;
	// 产品
	private List<TProduct> products;
	// 客户
	private List<TCustomer> customers;
	// 耗材
	private List<TMaterial> materials;

	// 本地图片根路径
	private String imgDir;

	public TBoss getBoss() {
		return boss;
	}

	public void setBoss(TBoss boss) {
		this.boss = boss;
	}

	public List<TStoreData> getStores() {
		return stores;
	}

	public void setStores(List<TStoreData> stores) {
		this.stores = stores;
	}

	public List<TShopKeeper> getKeepers() {
		return keepers;
	}

	public void setKeepers(List<TShopKeeper> keepers) {
		this.keepers = keepers;
	}

	public List<TClerk> getClerks() {
		return clerks;
	}

	public void setClerks(List<TClerk> clerks) {
		this.clerks = clerks;
	}

	public List<TApplyClerk> getApplyClerks() {
		return applyClerks;
	}

	public void setApplyClerks(List<TApplyClerk> applyClerks) {
		this.applyClerks = applyClerks;
	}

	public List<TProduct> getProducts() {
		return products;
	}

	public void setProducts(List<TProduct> products) {
		this.products = products;
	}

	public List<TCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<TCustomer> customers) {
		this.customers = customers;
	}

	public List<TMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(List<TMaterial> materials) {
		this.materials = materials;
	}

	public String getImgDir() {
		return imgDir;
	}

	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}

}
