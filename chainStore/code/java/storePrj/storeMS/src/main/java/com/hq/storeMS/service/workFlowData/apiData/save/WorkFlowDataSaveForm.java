package com.hq.storeMS.service.workFlowData.apiData.save;

import java.util.ArrayList;
import java.util.List;

import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.storeMS.service.workFlowData.data.MemCardInfo;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataBeanHelper;

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

	public WorkFlowData toWorkFlowData(long creatorIdP, long workFlowTypeIdP) {
		WorkFlowData data = WorkFlowData.newInstance();
		data.setStoreId(storeId);
		data.setBuserId(creatorIdP);
		data.setWorkFlowTypeId(workFlowTypeIdP);
		data.setMemCardInfo(memCardInfo);
		
		data.setNumber(number);
		data.setOrderTime(orderTime);
		data.setRecordType(recordType);
		
		addWorkFlowDataItems(data);
		return data;
	}

	public void updateWorkFlowData(WorkFlowData data) {
		data.getDelimitCardRecordMap().clear();
		data.getProdRecordMap().clear();
		data.getPrdCardRecordMap().clear();
		data.getPackagePrjRecordMap().clear();
		data.getGoodsRecordMap().clear();
		data.getBonusInfoMap().clear();
		
		data.setNumber(number);
		data.setOrderTime(orderTime);
		data.setRecordType(recordType);

		addWorkFlowDataItems(data);
	}

	private void addWorkFlowDataItems(WorkFlowData data) {
		data.setLeaguerInfo(leaguerSaveForm.toLeaguerInfo());

		for (PreStoreCardRecordSaveForm saveForm : preStoreCardRecordSaveForms) {
			WorkFlowDataBeanHelper.getInstance().addPreStoreCardRecord(data, saveForm.toPreStoreCardRecordAddForm());
		}

		for (DelimitCardRecordSaveForm saveForm : delimitCardRecordSaveForms) {
			WorkFlowDataBeanHelper.getInstance().addDelimitCardRecord(data, saveForm.toDelimitCardRecordAddForm());
		}

		for (BuyItemSaveForm saveForm : buyItemSaveForms) {
			addBuyItem(data, saveForm);
		}

		for (DonateItemSaveForm saveForm : donateItemSaveForms) {
			addDonateItem(data, saveForm);
		}

		for (BonusInfoSaveForm saveForm : bonusInfoSaveForms) {
			WorkFlowDataBeanHelper.getInstance().addBonusInfo(data, saveForm.toBonusInfoAddForm());
		}
	}

	private void addBuyItem(WorkFlowData data, BuyItemSaveForm saveForm) {
		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(saveForm.getBuyType());
		switch (buyTypeEnum) {
		case GOODS:
			WorkFlowDataBeanHelper.getInstance().addGoodsRecord(data, saveForm.toGoodsRecordAddForm());
			break;
		case PACKAGE:
			WorkFlowDataBeanHelper.getInstance().addPackagePrjRecord(data, saveForm.toPackagePrjRecordAddForm());
			break;
		case PRDCARD:
			WorkFlowDataBeanHelper.getInstance().addPrdCard(data, saveForm.toPrdCardAddForm());
			break;
		case PRODUCT:
			WorkFlowDataBeanHelper.getInstance().addProdRecord(data, saveForm.toProdRecordAddForm());
			break;
		default:
			break;
		}
	}

	private void addDonateItem(WorkFlowData data, DonateItemSaveForm saveForm) {
		BuyTypeEnum buyTypeEnum = BuyTypeEnum.valueOf(saveForm.getBuyType());
		switch (buyTypeEnum) {
		case GOODS:
			WorkFlowDataBeanHelper.getInstance().addGoodsRecord(data, saveForm.toGoodsRecordAddForm());
			break;
		case PACKAGE:
			WorkFlowDataBeanHelper.getInstance().addPackagePrjRecord(data, saveForm.toPackagePrjRecordAddForm());
			break;
		case PRDCARD:
			WorkFlowDataBeanHelper.getInstance().addPrdCard(data, saveForm.toPrdCardAddForm());
			break;
		case PRODUCT:
			WorkFlowDataBeanHelper.getInstance().addProdRecord(data, saveForm.toProdRecordAddForm());
			break;
		default:
			break;
		}
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
