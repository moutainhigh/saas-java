package com.hq.storeClient.service.workFlowData.apiData.save;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.workFlowData.data.MemCardInfo;

/**
 * 开单收银表单保存实体
 * 
 * @author kevin
 *
 */
public class WorkFlowDataSaveForm {
	private long id;
	// 店铺ID
	private long storeId;
	private int saveType;
	// 流程编号
	private String number;
	// 开单类型 WfDataTypeEnum 补单、开单
	private int recordType;
	// 补单时间
	private long orderTime;

	// 客户信息
	private LeaguerSaveForm leaguerSaveForm;

	// 预存卡信息
	private List<PreStoreCardRecordSaveForm> preStoreCardRecordSaveForms = new ArrayList<PreStoreCardRecordSaveForm>();

	// 划卡信息
	private List<DelimitCardRecordSaveForm> delimitCardRecordSaveForms = new ArrayList<DelimitCardRecordSaveForm>();

	// 消费项
	private List<BuyItemSaveForm> buyItemSaveForms = new ArrayList<BuyItemSaveForm>();

	// 赠送项
	private List<DonateItemSaveForm> donateItemSaveForms = new ArrayList<DonateItemSaveForm>();

	// 提成信息
	private List<BonusInfoSaveForm> bonusInfoSaveForms = new ArrayList<BonusInfoSaveForm>();

	// 会员充值
	private MemCardInfo memCardInfo;

	public static WorkFlowDataSaveForm newInstance() {
		return new WorkFlowDataSaveForm();
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

	public LeaguerSaveForm getLeaguerSaveForm() {
		return leaguerSaveForm;
	}

	public void setLeaguerSaveForm(LeaguerSaveForm leaguerSaveForm) {
		this.leaguerSaveForm = leaguerSaveForm;
	}

	public List<DelimitCardRecordSaveForm> getDelimitCardRecordSaveForms() {
		return delimitCardRecordSaveForms;
	}

	public void setDelimitCardRecordSaveForms(List<DelimitCardRecordSaveForm> delimitCardRecordSaveForms) {
		this.delimitCardRecordSaveForms = delimitCardRecordSaveForms;
	}

	public List<BuyItemSaveForm> getBuyItemSaveForms() {
		return buyItemSaveForms;
	}

	public void setBuyItemSaveForms(List<BuyItemSaveForm> buyItemSaveForms) {
		this.buyItemSaveForms = buyItemSaveForms;
	}

	public List<DonateItemSaveForm> getDonateItemSaveForms() {
		return donateItemSaveForms;
	}

	public void setDonateItemSaveForms(List<DonateItemSaveForm> donateItemSaveForms) {
		this.donateItemSaveForms = donateItemSaveForms;
	}

	public List<BonusInfoSaveForm> getBonusInfoSaveForms() {
		return bonusInfoSaveForms;
	}

	public void setBonusInfoSaveForms(List<BonusInfoSaveForm> bonusInfoSaveForms) {
		this.bonusInfoSaveForms = bonusInfoSaveForms;
	}

	public MemCardInfo getMemCardInfo() {
		return memCardInfo;
	}

	public void setMemCardInfo(MemCardInfo memCardInfo) {
		this.memCardInfo = memCardInfo;
	}

	public List<PreStoreCardRecordSaveForm> getPreStoreCardRecordSaveForms() {
		return preStoreCardRecordSaveForms;
	}

	public void setPreStoreCardRecordSaveForms(List<PreStoreCardRecordSaveForm> preStoreCardRecordSaveForms) {
		this.preStoreCardRecordSaveForms = preStoreCardRecordSaveForms;
	}

	public int getSaveType() {
		return saveType;
	}

	public void setSaveType(int saveType) {
		this.saveType = saveType;
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
