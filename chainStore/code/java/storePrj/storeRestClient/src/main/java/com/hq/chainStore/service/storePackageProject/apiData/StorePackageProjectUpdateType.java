package com.hq.chainStore.service.storePackageProject.apiData;

public enum StorePackageProjectUpdateType {
	AddPackageProject("新增套餐"),
	RemovePackageProject("删除套餐"),
	UpdatePackageProject("编辑套餐"),
	UpdPackageProjectState("修改套餐状态"),
	BatchUpdatePackageProjectState("批量修改套餐状态") ,// 批量上下架
	
	AddPackageProjectType("新增套餐分类"),
	RemovePackageProjectType("删除套餐分类"),
	UpdatePackageProjectType("编辑套餐分类"),
	
	/**********************************同步连锁店数据***************************************/
	PullPackageFromChain("拉取连锁店套餐"),
	CancelChainPackage("取消获取连锁店套餐"),
	BatchPullPackageFromChain("批量拉取连锁店套餐"),
	BatchCancelChainPackage("批量取消连锁店套餐"),
	
	AddPackageProjectTop("套餐置顶"),
	CancelPackageProjectTop("取消置顶"),
	;
	
	private String mark;
	
	private StorePackageProjectUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static StorePackageProjectUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
