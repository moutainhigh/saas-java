package com.hq.chainStore.service.storeProductInfo.data;


/**
 * @Deprecated 版本迭代，项目已不再关联耗材，相关实体类都已失效
 */
@Deprecated
public class ProductMaterial {

	private String materialId;
	
	private int count;

	public static ProductMaterial newInstance(){
		return new ProductMaterial();
	}
	
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
