package com.hq.chainStore.service.order.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.order.data.BuyItem;
import com.hq.chainStore.service.order.data.DelimitCardItem;
import com.hq.chainStore.service.order.data.DonationItem;
import com.hq.chainStore.service.order.data.PreStoreCardItem;
import com.hq.chainStore.service.order.data.RechargeItem;

public class OrderAddApiForm {
	// 订单类型 OrderTypeEnum
	private int orderType;
	// 客户ID
	private String leaguerId;
	// 客户会员卡ID
	private String memberCardId;
	// 客户名称
	private String name;
	// 应结金额
	private float cost;

	// 会员充值清单
	private List<RechargeItem> rechargeItems = new ArrayList<RechargeItem>();
	// 购买消费清单
	private List<BuyItem> buyItems = new ArrayList<BuyItem>();
	// 划卡消费清单
	private List<DelimitCardItem> delimitCardItems = new ArrayList<DelimitCardItem>();
	// 预存卡消费清单
	private List<PreStoreCardItem> preStoreCardItems = new ArrayList<PreStoreCardItem>();
	// 赠送消费清单
	private List<DonationItem> donationItems = new ArrayList<DonationItem>();

	// 店铺ID
	private long storeId;
	// 创建者ID
	private long creatorId;
	// 流程id
	private long workFlowDataId;
	// 开单类型 OrderDataTypeEnum 补单、开单
	private int recordType;
	// 补单时间
	private long orderTime;
	// 订单来源 OrderOriginEnum
	private int origin;

	/** =========================遗留的字段========================= **/
	private int sex;
	private long phone;

	private long beauticianId;
	private String beauticianName;

	private long productId;
	private String productName;
	private float price;

	// 暂时不用这两信息
	private String creatorName;

	private String imgUrl;

	public static OrderAddApiForm newInstance() {
		return new OrderAddApiForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public List<RechargeItem> getRechargeItems() {
		return rechargeItems;
	}

	public void setRechargeItems(List<RechargeItem> rechargeItems) {
		this.rechargeItems = rechargeItems;
	}

	public List<BuyItem> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(List<BuyItem> buyItems) {
		this.buyItems = buyItems;
	}

	public List<DelimitCardItem> getDelimitCardItems() {
		return delimitCardItems;
	}

	public void setDelimitCardItems(List<DelimitCardItem> delimitCardItems) {
		this.delimitCardItems = delimitCardItems;
	}

	public List<DonationItem> getDonationItems() {
		return donationItems;
	}

	public void setDonationItems(List<DonationItem> donationItems) {
		this.donationItems = donationItems;
	}

	public long getWorkFlowDataId() {
		return workFlowDataId;
	}

	public void setWorkFlowDataId(long workFlowDataId) {
		this.workFlowDataId = workFlowDataId;
	}

	public List<PreStoreCardItem> getPreStoreCardItems() {
		return preStoreCardItems;
	}

	public void setPreStoreCardItems(List<PreStoreCardItem> preStoreCardItems) {
		this.preStoreCardItems = preStoreCardItems;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

}
