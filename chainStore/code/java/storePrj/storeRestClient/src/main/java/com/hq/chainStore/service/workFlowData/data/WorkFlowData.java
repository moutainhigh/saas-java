package com.hq.chainStore.service.workFlowData.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataDetial.bs.IntfDetailData;

@Table(name = "workFlowData")
public class WorkFlowData implements IntfDetailData {
	@Id
	private long id;
	// 店铺ID
	private long storeId;
	// 创建者ID
	private long buserId;
	// 工作流ID
	private long workFlowTypeId;
	// 状态 WorkflowDataStatusEnum
	private int status;
	private int entityState;

	// 流程编号
	private String number;
	// 开单类型 WfDataTypeEnum 补单、开单
	private int recordType;
	// 补单时间
	private long orderTime;

	// 预约信息
	private AppointInfo appointInfo;
	// 客户信息
	private LeaguerInfo leaguerInfo;

	// 预存卡
	private Map<String, PreStoreCardRecord> preStoreCardRecordMap = new HashMap<String, PreStoreCardRecord>();

	// 划卡
	private Map<String, DelimitCardRecord> delimitCardRecordMap = new HashMap<String, DelimitCardRecord>();

	// 项目
	private Map<String, ProdRecord> prodRecordMap = new HashMap<String, ProdRecord>();

	// 购买次卡
	private Map<String, PrdCardRecord> prdCardRecordMap = new HashMap<String, PrdCardRecord>();

	// 购买商品
	private Map<String, GoodsRecord> goodsRecordMap = new HashMap<String, GoodsRecord>();

	// 购买套餐
	private Map<String, PackagePrjRecord> packagePrjRecordMap = new HashMap<String, PackagePrjRecord>();

	// 会员充值信息
	private MemCardInfo memCardInfo;

	// 订单信息
	private OrderInfo orderInfo;

	// 提成信息
	private Map<String, BonusInfo> bonusInfoMap = new HashMap<String, BonusInfo>();

	// 流程作废信息
	private CancelReason cancelReason;

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	/*********************************** 遗留字段 ***********************************/
	// 客户备注信息
	private String leaguerInfoComment;
	// 划卡
	private Map<String, DecreasePrdCardRecord> decreasePrdCardRecordMap = new HashMap<String, DecreasePrdCardRecord>();
	// 划卡备注信息
	private String decreasePrdCardComment;
	// 项目备注信息
	private String productComment;
	// 次卡备注信息
	private String prdCardComment;
	// 商品备注信息
	private String goodsComment;
	// 会员充值备注信息
	private String memCardComment;
	// 订单备注信息
	private String orderInfoComment;
	// 提成备注信息
	private String bonusInfoComment;

	public static WorkFlowData newInstance() {
		return new WorkFlowData();
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public long getWorkFlowTypeId() {
		return workFlowTypeId;
	}

	public void setWorkFlowTypeId(long workFlowTypeId) {
		this.workFlowTypeId = workFlowTypeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LeaguerInfo getLeaguerInfo() {
		return leaguerInfo;
	}

	public void setLeaguerInfo(LeaguerInfo leaguerInfo) {
		this.leaguerInfo = leaguerInfo;
	}

	public String getLeaguerInfoComment() {
		return leaguerInfoComment;
	}

	public void setLeaguerInfoComment(String leaguerInfoComment) {
		this.leaguerInfoComment = leaguerInfoComment;
	}

	public Map<String, ProdRecord> getProdRecordMap() {
		return prodRecordMap;
	}

	public void setProdRecordMap(Map<String, ProdRecord> prodRecordMap) {
		this.prodRecordMap = prodRecordMap;
	}

	public String getProductComment() {
		return productComment;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

	public Map<String, DecreasePrdCardRecord> getDecreasePrdCardRecordMap() {
		return decreasePrdCardRecordMap;
	}

	public void setDecreasePrdCardRecordMap(Map<String, DecreasePrdCardRecord> decreasePrdCardRecordMap) {
		this.decreasePrdCardRecordMap = decreasePrdCardRecordMap;
	}

	public String getDecreasePrdCardComment() {
		return decreasePrdCardComment;
	}

	public void setDecreasePrdCardComment(String decreasePrdCardComment) {
		this.decreasePrdCardComment = decreasePrdCardComment;
	}

	public Map<String, PrdCardRecord> getPrdCardRecordMap() {
		return prdCardRecordMap;
	}

	public void setPrdCardRecordMap(Map<String, PrdCardRecord> prdCardRecordMap) {
		this.prdCardRecordMap = prdCardRecordMap;
	}

	public String getPrdCardComment() {
		return prdCardComment;
	}

	public void setPrdCardComment(String prdCardComment) {
		this.prdCardComment = prdCardComment;
	}

	public Map<String, GoodsRecord> getGoodsRecordMap() {
		return goodsRecordMap;
	}

	public void setGoodsRecordMap(Map<String, GoodsRecord> goodsRecordMap) {
		this.goodsRecordMap = goodsRecordMap;
	}

	public String getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(String goodsComment) {
		this.goodsComment = goodsComment;
	}

	public MemCardInfo getMemCardInfo() {
		return memCardInfo;
	}

	public void setMemCardInfo(MemCardInfo memCardInfo) {
		this.memCardInfo = memCardInfo;
	}

	public String getMemCardComment() {
		return memCardComment;
	}

	public void setMemCardComment(String memCardComment) {
		this.memCardComment = memCardComment;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getOrderInfoComment() {
		return orderInfoComment;
	}

	public void setOrderInfoComment(String orderInfoComment) {
		this.orderInfoComment = orderInfoComment;
	}

	public Map<String, BonusInfo> getBonusInfoMap() {
		return bonusInfoMap;
	}

	public void setBonusInfoMap(Map<String, BonusInfo> bonusInfoMap) {
		this.bonusInfoMap = bonusInfoMap;
	}

	public String getBonusInfoComment() {
		return bonusInfoComment;
	}

	public void setBonusInfoComment(String bonusInfoComment) {
		this.bonusInfoComment = bonusInfoComment;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public Map<String, PackagePrjRecord> getPackagePrjRecordMap() {
		return packagePrjRecordMap;
	}

	public void setPackagePrjRecordMap(Map<String, PackagePrjRecord> packagePrjRecordMap) {
		this.packagePrjRecordMap = packagePrjRecordMap;
	}

	public Map<String, DelimitCardRecord> getDelimitCardRecordMap() {
		return delimitCardRecordMap;
	}

	public void setDelimitCardRecordMap(Map<String, DelimitCardRecord> delimitCardRecordMap) {
		this.delimitCardRecordMap = delimitCardRecordMap;
	}

	public AppointInfo getAppointInfo() {
		return appointInfo;
	}

	public void setAppointInfo(AppointInfo appointInfo) {
		this.appointInfo = appointInfo;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Map<String, PreStoreCardRecord> getPreStoreCardRecordMap() {
		return preStoreCardRecordMap;
	}

	public void setPreStoreCardRecordMap(Map<String, PreStoreCardRecord> preStoreCardRecordMap) {
		this.preStoreCardRecordMap = preStoreCardRecordMap;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
}
