package com.hq.storeClient.service.appointment.data;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class AppointProduct {
	// 项目ID
	private String productId;
	// 项目名称（作为冗余字段，勿删除）
	private String productName;
	// 项目数量
	private long productCount;

	private int operateType;// OperateTypeEnum 0 现结 1划卡
	// 次卡ID
	private String productCardId;
	// 客户次卡ID
	private String leaguerPrdCardId;

	// 服务人员
	private Set<Long> buserIds = new HashSet<Long>();

	public static AppointProduct newInstance() {
		AppointProduct data = new AppointProduct();
		return data;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
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

	public String getLeaguerPrdCardId() {
		return leaguerPrdCardId;
	}

	public void setLeaguerPrdCardId(String leaguerPrdCardId) {
		this.leaguerPrdCardId = leaguerPrdCardId;
	}

}
