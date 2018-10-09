package com.hq.storeMS.service.workFlowData.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.common.SplitMarkEnum;
import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "workFlowData")
public class WorkFlowData {
	@Id
	private long id;
	// 店铺ID
	@IndexField
	private long storeId;
	// 创建者ID
	private long buserId;
	// 工作流ID
	@IndexField
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

	@IndexField
	private long createdTime;
	private int splitMark;
	private long lastUpdateTime;
	private long ver;

	public static WorkFlowData newInstance() {
		WorkFlowData data = new WorkFlowData();
		data.status = WorkFlowDataStatusEnum.OPEN.ordinal();
		data.splitMark = SplitMarkEnum.FINISH.ordinal();
		long currentTime = System.currentTimeMillis();
		data.createdTime = currentTime;
		data.lastUpdateTime = currentTime;
		return data;
	}

	public void incrVer() {
		this.ver = this.ver + 1;
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

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public AppointInfo getAppointInfo() {
		return appointInfo;
	}

	public void setAppointInfo(AppointInfo appointInfo) {
		this.appointInfo = appointInfo;
	}

	public LeaguerInfo getLeaguerInfo() {
		return leaguerInfo;
	}

	public void setLeaguerInfo(LeaguerInfo leaguerInfo) {
		this.leaguerInfo = leaguerInfo;
	}

	public Map<String, PreStoreCardRecord> getPreStoreCardRecordMap() {
		return preStoreCardRecordMap;
	}

	public void setPreStoreCardRecordMap(Map<String, PreStoreCardRecord> preStoreCardRecordMap) {
		this.preStoreCardRecordMap = preStoreCardRecordMap;
	}

	public Map<String, DelimitCardRecord> getDelimitCardRecordMap() {
		return delimitCardRecordMap;
	}

	public void setDelimitCardRecordMap(Map<String, DelimitCardRecord> delimitCardRecordMap) {
		this.delimitCardRecordMap = delimitCardRecordMap;
	}

	public Map<String, ProdRecord> getProdRecordMap() {
		return prodRecordMap;
	}

	public void setProdRecordMap(Map<String, ProdRecord> prodRecordMap) {
		this.prodRecordMap = prodRecordMap;
	}

	public Map<String, PrdCardRecord> getPrdCardRecordMap() {
		return prdCardRecordMap;
	}

	public void setPrdCardRecordMap(Map<String, PrdCardRecord> prdCardRecordMap) {
		this.prdCardRecordMap = prdCardRecordMap;
	}

	public Map<String, GoodsRecord> getGoodsRecordMap() {
		return goodsRecordMap;
	}

	public void setGoodsRecordMap(Map<String, GoodsRecord> goodsRecordMap) {
		this.goodsRecordMap = goodsRecordMap;
	}

	public Map<String, PackagePrjRecord> getPackagePrjRecordMap() {
		return packagePrjRecordMap;
	}

	public void setPackagePrjRecordMap(Map<String, PackagePrjRecord> packagePrjRecordMap) {
		this.packagePrjRecordMap = packagePrjRecordMap;
	}

	public MemCardInfo getMemCardInfo() {
		return memCardInfo;
	}

	public void setMemCardInfo(MemCardInfo memCardInfo) {
		this.memCardInfo = memCardInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Map<String, BonusInfo> getBonusInfoMap() {
		return bonusInfoMap;
	}

	public void setBonusInfoMap(Map<String, BonusInfo> bonusInfoMap) {
		this.bonusInfoMap = bonusInfoMap;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public int getSplitMark() {
		return splitMark;
	}

	public void setSplitMark(int splitMark) {
		this.splitMark = splitMark;
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
