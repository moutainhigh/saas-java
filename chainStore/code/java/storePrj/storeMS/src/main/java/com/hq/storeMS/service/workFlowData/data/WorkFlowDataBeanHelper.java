package com.hq.storeMS.service.workFlowData.data;

import java.util.Map;

import com.hq.orderRestClient.service.order.data.BuyTypeEnum;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoUpdateForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.DelimitCardRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.GoodsRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.apiData.LeaguerInfoForm;
import com.hq.storeMS.service.workFlowData.apiData.MemCardInfoForm;
import com.hq.storeMS.service.workFlowData.apiData.OrderInfoForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PackagePrjRecordUpdateForm;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardAddForm;
import com.hq.storeMS.service.workFlowData.apiData.PrdCardUpdateForm;
import com.hq.storeMS.service.workFlowData.apiData.PreStoreCardRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordAddForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdInfoForm;
import com.hq.storeMS.service.workFlowData.apiData.ProdRecordUpdStatusForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataBeanHelper {

	public static WorkFlowDataBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataBeanHelper.class);
	}
	
	public String genDecPrdCardId(int status, String cardTypeId, long prdId){
		return StringFormatUtil.format("{}_{}_{}", status, cardTypeId, prdId);
	}
	
	//移除提成信息
	private void removeBonus(WorkFlowData workFlowData, String bonusId){
		if(workFlowData == null){
			return;
		}
		Map<String, BonusInfo> dataMap = workFlowData.getBonusInfoMap();
		if (dataMap.containsKey(bonusId)) {
			dataMap.remove(bonusId);
		}
	}
	
	/**
	 * ==============================套餐==============================
	 */
	public boolean updatePackagePrjRecord(WorkFlowData workFlowData, PackagePrjRecordUpdateForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, PackagePrjRecord> dataMap = workFlowData.getPackagePrjRecordMap();
		boolean success = false;
		if (dataMap.containsKey(data.getId())) {
			PackagePrjRecord record = dataMap.get(data.getId());
			data.updatePackagePrjRecord(record);
			success = true;
		}
		return success;
	}
	
	public boolean deletePackagePrjRecord(WorkFlowData workFlowData, String id) {
		if(workFlowData == null){
			return false;
		}
		Map<String, PackagePrjRecord> dataMap = workFlowData.getPackagePrjRecordMap();
		boolean success = false;
		if (dataMap.containsKey(id)) {
			PackagePrjRecord data = dataMap.get(id);
			String bonusId = "";
			String packagePrjId = data.getPackagePrjId();
			if(data.getRecordType() == RecordTypeEnum.Buy.ordinal()) {//非赠品
				bonusId = BonusInfo.genBonusIdByBuyItem(BuyTypeEnum.PACKAGE, packagePrjId);
			}else if(data.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				bonusId = BonusInfo.genBonusIdByDonationItem(BuyTypeEnum.PACKAGE, packagePrjId);
			}
			removeBonus(workFlowData, bonusId);
			dataMap.remove(id);
			success = true;
		}
		return success;
	}
	
	public boolean addPackagePrjRecord(WorkFlowData workFlowData, PackagePrjRecordAddForm data) {
		if(workFlowData == null){
			return false;
		}
		PackagePrjRecord record = data.toPackagePrjRecord();
		Map<String, PackagePrjRecord> dataMap = workFlowData.getPackagePrjRecordMap();
		boolean success = false;
		if (!dataMap.containsKey(record.getId())) {
			dataMap.put(record.getId(), record);
			success = true;
		}
		return success;
	}
	
	
	/**
	 * ==============================提成==============================
	 */
	public boolean updateBonusInfo(WorkFlowData workFlowData, BonusInfoUpdateForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, BonusInfo> dataMap = workFlowData.getBonusInfoMap();
		boolean success = false;
		if (dataMap.containsKey(data.getBonusId())) {
			BonusInfo record = dataMap.get(data.getBonusId());
			data.updateBonusInfo(record);
			success = true;
		}
		return success;
	}
	
	public boolean deleteBonusInfo(WorkFlowData workFlowData, String bonusId) {
		if(workFlowData == null){
			return false;
		}
		Map<String, BonusInfo> dataMap = workFlowData.getBonusInfoMap();
		boolean success = false;
		if (dataMap.containsKey(bonusId)) {
			dataMap.remove(bonusId);
			success = true;
		}
		return success;
	}
	
	public boolean addBonusInfo(WorkFlowData workFlowData, BonusInfoAddForm data) {
		if(workFlowData == null){
			return false;
		}
		BonusInfo record = data.toBonusInfo();
		Map<String, BonusInfo> dataMap = workFlowData.getBonusInfoMap();
		boolean success = false;
		if (!dataMap.containsKey(record.getBonusId())) {
			dataMap.put(record.getBonusId(), record);
			success = true;
		}
		return success;
	}
	
	/**
	 * ==============================订单==============================
	 */
	public boolean updateOrderInfo(WorkFlowData workFlowData, OrderInfoForm data) {
		if(workFlowData == null){
			return false;
		}
		workFlowData.setOrderInfo(data.toOrderInfo());
		return true;
	}
	
	/**
	 * ==============================会员卡充值==============================
	 */
	public boolean updateMemCardInfo(WorkFlowData workFlowData, MemCardInfoForm data) {
		if(workFlowData == null){
			return false;
		}
		workFlowData.setMemCardInfo(data.toMemCardInfo());
		return true;
	}
	
	/**
	 * ==============================商品==============================
	 */
	public boolean updateGoodsRecord(WorkFlowData workFlowData, GoodsRecordUpdateForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, GoodsRecord> dataMap = workFlowData.getGoodsRecordMap();
		boolean success = false;
		if (dataMap.containsKey(data.getId())) {
			GoodsRecord record = dataMap.get(data.getId());
			data.updateGoodsRecord(record);
			success = true;
		}
		return success;
	}
	
	public boolean deleteGoodsRecord(WorkFlowData workFlowData, String id) {
		if(workFlowData == null){
			return false;
		}
		Map<String, GoodsRecord> dataMap = workFlowData.getGoodsRecordMap();
		boolean success = false;
		if (dataMap.containsKey(id)) {
			GoodsRecord data = dataMap.get(id);
			String bonusId = "";
			String goodsId = data.getGoodsId();
			if(data.getRecordType() == RecordTypeEnum.Buy.ordinal()) {//非赠品
				bonusId = BonusInfo.genBonusIdByBuyItem(BuyTypeEnum.GOODS, goodsId);
			}else if(data.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				bonusId = BonusInfo.genBonusIdByDonationItem(BuyTypeEnum.GOODS, goodsId);
			}
			removeBonus(workFlowData, bonusId);
			dataMap.remove(id);
			success = true;
		}
		return success;
	}
	
	public boolean addGoodsRecord(WorkFlowData workFlowData, GoodsRecordAddForm data) {
		if(workFlowData == null){
			return false;
		}
		GoodsRecord record = data.toGoodsRecord();
		Map<String, GoodsRecord> dataMap = workFlowData.getGoodsRecordMap();
		boolean success = false;
		if (!dataMap.containsKey(record.getId())) {
			dataMap.put(record.getId(), record);
			success = true;
		}
		return success;
	}
	
	/**
	 * ==============================次卡==============================
	 */
	public boolean updatePrdCard(WorkFlowData workFlowData, PrdCardUpdateForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, PrdCardRecord> dataMap = workFlowData.getPrdCardRecordMap();
		boolean success = false;
		if (dataMap.containsKey(data.getId())) {
			PrdCardRecord record = dataMap.get(data.getId());
			data.updatePrdCardRecord(record);
			success = true;
		}
		return success;
	}
	
	public boolean deletePrdCard(WorkFlowData workFlowData, String id) {
		if(workFlowData == null){
			return false;
		}
		Map<String, PrdCardRecord> dataMap = workFlowData.getPrdCardRecordMap();
		boolean success = false;
		if (dataMap.containsKey(id)) {
			PrdCardRecord data = dataMap.get(id);
			String bonusId = "";
			String prdCardTypeId = data.getPrdCardTypeId();
			if(data.getRecordType() == RecordTypeEnum.Buy.ordinal()) {//非赠品
				bonusId = BonusInfo.genBonusIdByBuyItem(BuyTypeEnum.PRDCARD, prdCardTypeId);
			}else if(data.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				bonusId = BonusInfo.genBonusIdByDonationItem(BuyTypeEnum.PRDCARD, prdCardTypeId);
			}
			removeBonus(workFlowData, bonusId);
			dataMap.remove(id);
			success = true;
		}
		return success;
	}
	
	public boolean addPrdCard(WorkFlowData workFlowData, PrdCardAddForm data) {
		if(workFlowData == null){
			return false;
		}
		PrdCardRecord record = data.toPrdCardRecord();
		Map<String, PrdCardRecord> dataMap = workFlowData.getPrdCardRecordMap();
		boolean success = false;
		if (!dataMap.containsKey(record.getId())) {
			dataMap.put(record.getId(), record);
			success = true;
		}
		return success;
	}
	
	/**
	 * ==============================划卡==============================
	 */
	public boolean updateDelimitCardRecord(WorkFlowData workFlowData, DelimitCardRecordUpdateForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, DelimitCardRecord> dataMap = workFlowData.getDelimitCardRecordMap();
		boolean success = false;
		if (dataMap.containsKey(data.getDelimitCardId())) {
			DelimitCardRecord record = dataMap.get(data.getDelimitCardId());
			data.updateDelimitCardRecord(record);
			success = true;
		}
		return success;
	}
	
	public boolean deleteDelimitCardRecord(WorkFlowData workFlowData, String delimitCardId) {
		if(workFlowData == null){
			return false;
		}
		Map<String, DelimitCardRecord> dataMap = workFlowData.getDelimitCardRecordMap();
		boolean success = false;
		if (dataMap.containsKey(delimitCardId)) {
			// 移除划卡信息时，也需要移除对应的提成信息
			DelimitCardRecord record = dataMap.get(delimitCardId);
			String bonusId = BonusInfo.genBonusIdByDecreasePrdCard(record.getPgId(), record.getLeaguerPrdCardId());
			removeBonus(workFlowData, bonusId);
			dataMap.remove(delimitCardId);
			success = true;
		}
		return success;
	}
	
	public boolean addDelimitCardRecord(WorkFlowData workFlowData, DelimitCardRecordAddForm data) {
		if(workFlowData == null){
			return false;
		}
		DelimitCardRecord delimitCardRecord = data.toDelimitCardRecord();
		Map<String, DelimitCardRecord> dataMap = workFlowData.getDelimitCardRecordMap();
		boolean success = false;
		if (!dataMap.containsKey(delimitCardRecord.getDelimitCardId())) {
			dataMap.put(delimitCardRecord.getDelimitCardId(), delimitCardRecord);
			success = true;
		}
		return success;
	}
	
	/**
	 * ==============================预存卡==============================
	 */
	public boolean addPreStoreCardRecord(WorkFlowData workFlowData, PreStoreCardRecordAddForm data) {
		if(workFlowData == null){
			return false;
		}
		PreStoreCardRecord record = data.toPreStoreCardRecord();
		Map<String, PreStoreCardRecord> dataMap = workFlowData.getPreStoreCardRecordMap();
		if (!dataMap.containsKey(record.getId())) {
			dataMap.put(record.getId(), record);
			return true;
		}
		return false;
	}
	
	/**
	 * ==============================项目==============================
	 */
	public boolean updateProdRecordInfo(WorkFlowData workFlowData, ProdRecordUpdInfoForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, ProdRecord> dataMap = workFlowData.getProdRecordMap();
		boolean success = false;
		if (dataMap.containsKey(data.getId())) {
			ProdRecord prodRecord = dataMap.get(data.getId());
			data.updateProdRecord(prodRecord);
			success = true;
		}
		return success;
	}
	
	public boolean updateProdRecordStatus(WorkFlowData workFlowData, ProdRecordUpdStatusForm data) {
		if(workFlowData == null){
			return false;
		}
		Map<String, ProdRecord> dataMap = workFlowData.getProdRecordMap();
		boolean success = false;
		if (dataMap.containsKey(data.getId())) {
			ProdRecord prodRecord = dataMap.get(data.getId());
			data.updateProdRecord(prodRecord);
			success = true;
		}
		return success;
	}
	
	public boolean deleteProdRecord(WorkFlowData workFlowData, String id) {
		if(workFlowData == null){
			return false;
		}
		Map<String, ProdRecord> dataMap = workFlowData.getProdRecordMap();
		boolean success = false;
		if (dataMap.containsKey(id)) {
			ProdRecord data = dataMap.get(id);
			String bonusId = "";
			String prdId = data.getProdId();
			if(data.getRecordType() == RecordTypeEnum.Buy.ordinal()) {//非赠品
				bonusId = BonusInfo.genBonusIdByBuyItem(BuyTypeEnum.PRODUCT, prdId);
			}else if(data.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				bonusId = BonusInfo.genBonusIdByDonationItem(BuyTypeEnum.PRODUCT, prdId);
			}
			removeBonus(workFlowData, bonusId);
			dataMap.remove(id);
			success = true;
		}
		return success;
	}
	
	public boolean addProdRecord(WorkFlowData workFlowData, ProdRecordAddForm data) {
		if(workFlowData == null){
			return false;
		}
		ProdRecord record = data.toProdRecord();
		Map<String, ProdRecord> dataMap = workFlowData.getProdRecordMap();
		boolean success = false;
		if (!dataMap.containsKey(record.getId())) {
			dataMap.put(record.getId(), record);
			success = true;
		}
		return success;
	}
	
	/**
	 * ==============================客户==============================
	 */
	public boolean updateLeaguerInfo(WorkFlowData workFlowData, LeaguerInfoForm data) {
		if(workFlowData == null){
			return false;
		}
		workFlowData.setLeaguerInfo(data.toLeaguerInfo());
		return true;
	}

}
