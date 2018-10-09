package com.hq.customerRestClient.service.appointment.data;

import java.util.HashSet;
import java.util.Set;

/**
 * 预约项目的实体
 * 
 * @author Administrator
 *
 */
public class AppointProduct {
	// 项目ID
	private long productId;
	//项目名称（作为冗余字段，勿删除）
	private String productName;
	// 项目数量
	private long productCount;
	
	private int operateType;//OperateTypeEnum 0 现结 1划卡
	// 耗卡ID
	private String productCardId;
	
	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();

	public static AppointProduct newInstance() {
		AppointProduct data = new AppointProduct();
		return data;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(String productCardId) {
		this.productCardId = productCardId;
	}

	public Set<Long> getBuserIds() {
		return buserIds;
	}

	public void setBuserIds(Set<Long> buserIds) {
		this.buserIds = buserIds;
	}

	public long getProductCount() {
		return productCount;
	}

	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}

	
	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
