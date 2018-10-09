package com.hq.chainStore.service.order.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataDetial.bs.IntfDetailData;

@Table(name = "order")
public class Order implements IntfDetailData {
	@Id
	private long id;
	// 订单编号
	private String number;
	// 订单类型 OrderTypeEnum
	private int orderType;
	// 客户ID
	private String leaguerId;
	// 客户会员卡ID
	private String memberCardId;
	// 应结金额
	private float cost;
	// 实付金额
	private float realPay;
	// 订单的状态 OrderStatusEnum
	private int status;
	// 实体状态
	private int entityState;
	// 创建时间
	private long createdTime;
	// 付款时间
	private long payTime;
	// 已退金额
	private float chargeBackCost;

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
	// 支付明细
	private List<PayItem> payItems = new ArrayList<PayItem>();

	// 客户名称
	private String name;
	// 店铺ID
	private long storeId;
	// 创建者ID
	private long creatorId;
	// 流程id
	private long workFlowDataId;
	// 订单类型 OrderDataTypeEnum 补单、开单
	private int recordType;
	// 订单来源 OrderOriginEnum
	private int origin;

	private long lastUpdateTime;
	private int ver;

	/** =========================遗留的字段========================= **/
	// C端用户ID
	private long cuserId;
	// 顾客信息
	private int sex;
	private long phone;
	// 医美师信息
	private long beauticianId;
	private String beauticianName;
	// 产品信息
	private long productId;
	private String productName;
	private float price;
	private String imgUrl;
	private int payType;
	// 创建者名称
	private String creatorName;
	// 耗材列表
	@Deprecated
	private List<SimpleMaterial> materials = new ArrayList<SimpleMaterial>();
	// 评论状态
	private int orderCommentStatus;

	public static Order newInstance() {
		Order order = new Order();
		order.status = OrderStatusEnum.NOT_PAY.ordinal();

		long curTime = System.currentTimeMillis();
		order.createdTime = curTime;
		order.lastUpdateTime = curTime;
		return order;
	}

	@Override
	public Object targetId() {
		return this.id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
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

	public List<PayItem> getPayItems() {
		return payItems;
	}

	public void setPayItems(List<PayItem> payItems) {
		this.payItems = payItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
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

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Deprecated
	public List<SimpleMaterial> getMaterials() {
		return materials;
	}

	@Deprecated
	public void setMaterials(List<SimpleMaterial> materials) {
		this.materials = materials;
	}

	public int getOrderCommentStatus() {
		return orderCommentStatus;
	}

	public void setOrderCommentStatus(int orderCommentStatus) {
		this.orderCommentStatus = orderCommentStatus;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getPayTime() {
		return payTime;
	}

	public void setPayTime(long payTime) {
		this.payTime = payTime;
	}

	public float getRealPay() {
		return realPay;
	}

	public void setRealPay(float realPay) {
		this.realPay = realPay;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
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

	public float getChargeBackCost() {
		return chargeBackCost;
	}

	public void setChargeBackCost(float chargeBackCost) {
		this.chargeBackCost = chargeBackCost;
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

}
