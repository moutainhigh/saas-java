package com.hq.customerRestClient.service.order.data;


/**
 * 简化信息的耗材实体
 * 
 * @author kevin
 */
@Deprecated
public class SimpleMaterial {
	private String id;
	private float referencePrice;
	private int count;

	public static SimpleMaterial newInstance() {
		SimpleMaterial data = new SimpleMaterial();
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(float referencePrice) {
		this.referencePrice = referencePrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
