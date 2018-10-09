package com.hq.chainStore.service.storeProductInfo.data;

/**
 * @Deprecated 版本迭代，打包项目功能已经摒弃，相关实体类都已失效
 */
@Deprecated
public enum PackedProductInfoState {

	New("新项目"),	 //待上架
	Open("开放项目"),	 //上架
	Close("关闭项目"),   //下架
	;
	
	private String descript;
	
	private PackedProductInfoState(String descript) {
		this.descript = descript;
	}

	public String getDescript() {
		return descript;
	}

	public static PackedProductInfoState valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }
}
