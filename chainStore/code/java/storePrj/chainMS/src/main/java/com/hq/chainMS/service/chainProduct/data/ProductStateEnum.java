package com.hq.chainMS.service.chainProduct.data;

public enum ProductStateEnum {
	Open("已上架"),
	Close("已下架"),
	;
	
	private String descript;
	
	private ProductStateEnum(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static ProductStateEnum valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
