package com.hq.chainStore.service.storeProductInfo.data;


/**
 * @Deprecated 版本迭代，项目分类已被ProductType替代
 */
@Deprecated
public enum ProductInfoType {
	
	bodyCare("美体"),  //美体
	skinCare("美肤"),  //美肤
	faceCare("美容"),  //美容
	;
	private String descript;
	
	private ProductInfoType(String descript){
		this.descript = descript;
	}
	
	public String getDescript() {
		return descript;
	}
	
	public static ProductInfoType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
