package com.hq.chainMS.service.chainPackageProject.apiData;

public enum ChainPackageProjectUpdateType {
	AddPackageProject("新增套餐"),
	RemovePackageProject("删除套餐"),
	UpdatePackageProject("编辑套餐"),
	UpdPackageProjectState("修改套餐状态"),
	BatchUpdatePackageProjectState("批量修改套餐状态") ,// 批量上下架
	
	AddPackageProjectType("新增套餐分类"),
	RemovePackageProjectType("删除套餐分类"),
	UpdatePackageProjectType("编辑套餐分类"),
	
	AllotStore("分配到店"),
	BatchAllotStore("批量分配到店"),
	;
	
	private String mark;
	
	private ChainPackageProjectUpdateType(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}
	
	public static ChainPackageProjectUpdateType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
