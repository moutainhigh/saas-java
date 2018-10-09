package com.hq.storeMS.service.order.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.apiData.OrderAddApiForm;
import com.hq.orderRestClient.service.order.data.BuyItem;
import com.hq.orderRestClient.service.order.data.DelimitCardItem;
import com.hq.orderRestClient.service.order.data.DonationItem;
import com.hq.orderRestClient.service.order.data.OrderDataTypeEnum;
import com.hq.orderRestClient.service.order.data.OrderOriginEnum;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PreStoreCardItem;
import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.orderTrack.data.OrderTrackStatusEnum;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.DelimitCardRecord;
import com.hq.storeMS.service.workFlowData.data.GoodsRecord;
import com.hq.storeMS.service.workFlowData.data.MemCardInfo;
import com.hq.storeMS.service.workFlowData.data.PackagePrjRecord;
import com.hq.storeMS.service.workFlowData.data.PrdCardRecord;
import com.hq.storeMS.service.workFlowData.data.PreStoreCardRecord;
import com.hq.storeMS.service.workFlowData.data.ProdRecord;
import com.hq.storeMS.service.workFlowData.data.RecordTypeEnum;
import com.hq.storeMS.service.workFlowData.data.WfDataTypeEnum;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderBeanHelper {

	public static OrderBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(OrderBeanHelper.class);
	}
	
	public List<BonusInfoAddForm> buildBonusInfoAddForms(WorkFlowData data){
		List<BonusInfoAddForm> result = new ArrayList<BonusInfoAddForm>();
		Collection<BonusInfo> bonusInfos = data.getBonusInfoMap().values();
		for (BonusInfo bonusInfo : bonusInfos) {
			result.add(bonusInfo.toBonusInfoAddForm());
		}
		return result;
	}
	
	//物流状态与订单状态的对应关系
	public int trackStatus2OrderStatus(int trackStatus) {
		OrderTrackStatusEnum trackStatusEnum = OrderTrackStatusEnum.valueOf(trackStatus);
		if(trackStatusEnum == OrderTrackStatusEnum.New) {
			return OrderStatusEnum.NOT_PAY.ordinal();
		}else if(trackStatusEnum == OrderTrackStatusEnum.Cancel) {
			return OrderStatusEnum.CANCEL.ordinal();
		}else {
			return OrderStatusEnum.HAS_PAY.ordinal();
		}
	}
	
	public OrderAddApiForm buildOrderAddApiForm(OrderTypeEnum orderType, long creatorId, WorkFlowData data, LeaguerDetail leaguer) {
		OrderAddApiForm addApiForm = OrderAddApiForm.newInstance();
		float cost = 0.0f;
		if(orderType == OrderTypeEnum.PURCHASE) {
			addApiForm.setDelimitCardItems(takeDelimitCardItems(data));
			addApiForm.setPreStoreCardItems(takePreStoreCardItems(data));
			
			List<BuyItem> buyItems = takeBuyItems(data);
			addApiForm.setBuyItems(buyItems);
			for (BuyItem item : buyItems) {
				cost += item.getCost();
			}
			
			addApiForm.setDonationItems(takeDonationItems(data));
		}else if(orderType == OrderTypeEnum.RECHARGE) {
			List<RechargeItem> rechargeItems = takeRechargeItems(data);
			addApiForm.setRechargeItems(rechargeItems);
			for (RechargeItem item : rechargeItems) {
				cost += item.getPay();
			}
		}
		String leaguerId = data.getLeaguerInfo().getLeaguerId();
		String leaguerName = leaguer.getName();
		addApiForm.setCost(cost);
		addApiForm.setOrderType(orderType.ordinal());
		addApiForm.setCreatorId(creatorId);
		addApiForm.setLeaguerId(leaguerId);
		addApiForm.setStoreId(data.getStoreId());
		addApiForm.setName(leaguerName);
		addApiForm.setOrigin(OrderOriginEnum.BUSINESS.ordinal());
		
		addApiForm.setWorkFlowDataId(data.getId());// 订单需保存流程id
		String memberCardId = leaguer.getLeaguerMemberCard().getCardId();
		if(StringUtils.isNoneBlank(memberCardId)) {
			addApiForm.setMemberCardId(memberCardId);
		}
		
		//补单的情况
		if(data.getRecordType() == WfDataTypeEnum.OLD_RCD.ordinal()) {
			addApiForm.setRecordType(OrderDataTypeEnum.OLD_RCD.ordinal());
		}
		addApiForm.setOrderTime(data.getOrderTime());
		
		return addApiForm;
	}
	
	// 获取赠送消费项信息
	private List<DonationItem> takeDonationItems(WorkFlowData data){
		List<DonationItem> result = new ArrayList<DonationItem>();
		
		Collection<ProdRecord> prodRecords = data.getProdRecordMap().values();
		for (ProdRecord record : prodRecords) {
			if(record.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				result.add(record.toDonationItem());
			}
		}
		
		Collection<PrdCardRecord> prdCardRecords = data.getPrdCardRecordMap().values();
		for (PrdCardRecord record : prdCardRecords) {
			if(record.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				result.add(record.toDonationItem());
			}
		}
		
		Collection<GoodsRecord> goodsRecords = data.getGoodsRecordMap().values();
		for (GoodsRecord record : goodsRecords) {
			if(record.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				result.add(record.toDonationItem());
			}
		}
		
		Collection<PackagePrjRecord> packagePrjRecords = data.getPackagePrjRecordMap().values();
		for (PackagePrjRecord record : packagePrjRecords) {
			if(record.getRecordType() == RecordTypeEnum.Donation.ordinal()) {
				result.add(record.toDonationItem());
			}
		}
		return result;
	}
	
	// 获取购买消费项信息
	private List<BuyItem> takeBuyItems(WorkFlowData data){
		List<BuyItem> result = new ArrayList<BuyItem>();
		Collection<ProdRecord> prodRecords = data.getProdRecordMap().values();
		for (ProdRecord record : prodRecords) {
			if(record.getRecordType() == RecordTypeEnum.Buy.ordinal()) {
				result.add(record.toBuyItem());
			}
		}
		
		Collection<PrdCardRecord> prdCardRecords = data.getPrdCardRecordMap().values();
		for (PrdCardRecord record : prdCardRecords) {
			if(record.getRecordType() == RecordTypeEnum.Buy.ordinal()) {
				result.add(record.toBuyItem());
			}
		}
		
		Collection<GoodsRecord> goodsRecords = data.getGoodsRecordMap().values();
		for (GoodsRecord record : goodsRecords) {
			if(record.getRecordType() == RecordTypeEnum.Buy.ordinal()) {
				result.add(record.toBuyItem());
			}
		}
		
		Collection<PackagePrjRecord> packagePrjRecords = data.getPackagePrjRecordMap().values();
		for (PackagePrjRecord record : packagePrjRecords) {
			if(record.getRecordType() == RecordTypeEnum.Buy.ordinal()) {
				result.add(record.toBuyItem());
			}
		}
		return result;
	}
	
	// 获取划卡消费项信息
	private List<DelimitCardItem> takeDelimitCardItems(WorkFlowData data){
		List<DelimitCardItem> result = new ArrayList<DelimitCardItem>();
		Collection<DelimitCardRecord> values = data.getDelimitCardRecordMap().values();
		for (DelimitCardRecord record : values) {
			result.add(record.toDelimitCardItem());
		}
		return result;
	}
	
	// 获取预存卡消费项信息
	private List<PreStoreCardItem> takePreStoreCardItems(WorkFlowData data){
		List<PreStoreCardItem> result = new ArrayList<PreStoreCardItem>();
		Collection<PreStoreCardRecord> values = data.getPreStoreCardRecordMap().values();
		for (PreStoreCardRecord record : values) {
			result.add(record.toPreStoreCardItem());
		}
		return result;
	}
	
	// 获取会员卡充值信息
	private List<RechargeItem> takeRechargeItems(WorkFlowData data){
		List<RechargeItem> result = new ArrayList<RechargeItem>();
		MemCardInfo memCardInfo = data.getMemCardInfo();
		if(memCardInfo != null) {
			result.add(memCardInfo.toRechargeItem());
		}
		return result;
	}

}
