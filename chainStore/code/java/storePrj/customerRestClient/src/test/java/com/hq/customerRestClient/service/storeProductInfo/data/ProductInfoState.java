package com.hq.customerRestClient.service.storeProductInfo.data;

public enum ProductInfoState {

	New("未上架"),
	Open("已上架"),	 //上架
	Close("已下架"),   //下架
	Remove("删除"),  //删除项目
	;
	
	private String descript;
	
	private ProductInfoState(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static ProductInfoState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
