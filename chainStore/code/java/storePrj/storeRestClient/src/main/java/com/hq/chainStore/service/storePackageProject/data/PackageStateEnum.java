package com.hq.chainStore.service.storePackageProject.data;

public enum PackageStateEnum {
	Open("已上架"),
	Close("已下架"),
	;
	
	private String descript;
	
	private PackageStateEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static PackageStateEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
