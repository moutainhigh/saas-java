package com.hq.storeMS.service.order.bs.updateLeaguerInfo;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.RechargeItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataMgr;
import com.hq.storeMS.service.cuserChainData.data.ChainData;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.RechargeMemberCardForm;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.zenmind.common.hotSwap.HotSwap;

public class MemberCardBalanceUpdateFilter {
	
	public static MemberCardBalanceUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(MemberCardBalanceUpdateFilter.class);
	}
	
	//会员卡充值
	public OperateTips rechargeMemberCard(Order order, LeaguerDetail leaguer){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() == OrderTypeEnum.RECHARGE.ordinal()){//会员充值
			if(!rechargeMemberCard(order)) {
				operateTips.setSuccess(false);
				operateTips.setTips("会员卡充值失败");
				return operateTips;
			}
		}
		return operateTips;
	}
	
	//充值
	private boolean rechargeMemberCard(Order order){
		List<RechargeItem> rechargeItems = order.getRechargeItems();
		if(CollectionUtils.isEmpty(rechargeItems)) {
			return true;
		}
		
		long storeId = order.getStoreId();
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		RechargeItem rechargeItem = rechargeItems.get(0);
		LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(storeId, order.getLeaguerId());
		
		//本地会员卡充值
		rechargeZmtMemberCard(detail, storeCard, rechargeItem);
		//连锁店会员卡充值
		rechargeChainMemberCard(detail, storeCard, rechargeItem);
		
		return true;
	}
	
	private boolean rechargeZmtMemberCard(LeaguerDetail leaguer, StoreCardInfo storeCard, RechargeItem rechargeItem) {
		String leaguerId = leaguer.getId();
		LeaguerMemberCard memberCard = leaguer.getLeaguerMemberCard();
		
		boolean updateFlag = false;
		UpdateMemberCardForm updatForm = getUpdateMemberCardForm(leaguerId, rechargeItem);
		if(updatForm!=null) {//设置新的会员卡[连锁或者本地]
			LeaguerDetailBeanHelper.getInstance().updateMemberCard(memberCard, updatForm);
			updateFlag = true;
		}
		
		RechargeMemberCardForm rechargeForm = getRechargeMemberCardForm(leaguerId, rechargeItem);
		if(!storeCard.memberCardIsFromChain(memberCard.getCardId())) {//本地的会员卡
			LeaguerDetailBeanHelper.getInstance().rechargeMemberCard(memberCard, rechargeForm);
			updateFlag = true;
		}
		
		if(updateFlag) {
			LeaguerDetailMgr.getInstance().updateSimple(leaguer);
		}
		return true;
	}
	
	private boolean rechargeChainMemberCard(LeaguerDetail leaguer, StoreCardInfo storeCard, RechargeItem rechargeItem) {
		CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(leaguer.getCuserId());
		Store store = StoreMgr.getInstance().getSimple(leaguer.getStoreId());
		
		Long chainId = store.takeChainId();
		if(chainId == null) {
			return true;
		}
		
		boolean updateFlag = false;
		ChainData chainData = cuserChainData.takeChainData(chainId);
		if(chainData == null) {
			chainData = ChainData.newInstance();
			chainData.setChainId(chainId);
			cuserChainData.getChainDataMap().put(chainId, chainData);
			updateFlag = true;
		}
		
		LeaguerMemberCard zmtMemberCard = leaguer.getLeaguerMemberCard();
		LeaguerMemberCard chainMemberCard = cuserChainData.takeLeaguerMemberCard(chainId);
		
		UpdateMemberCardForm updatForm = getUpdateMemberCardForm(leaguer.getId(), rechargeItem);
		if(updatForm!=null && storeCard.memberCardIsFromChain(updatForm.getCardId())) {
			LeaguerDetailBeanHelper.getInstance().updateMemberCard(chainMemberCard, updatForm);
			updateFlag = true;
		}
		
		RechargeMemberCardForm rechargeForm = getRechargeMemberCardForm(leaguer.getId(), rechargeItem);
		if(storeCard.memberCardIsFromChain(zmtMemberCard.getCardId())) {
			LeaguerDetailBeanHelper.getInstance().rechargeMemberCard(chainMemberCard, rechargeForm);
			updateFlag = true;
		}
		
		if(updateFlag) {
			CuserChainDataMgr.getInstance().update(cuserChainData);
		}
		
		return true;
	}
	
	private UpdateMemberCardForm getUpdateMemberCardForm(String leaguerId, RechargeItem item) {
		if(StringUtils.isBlank(item.getMembershipCardId())) {
			return null;
		}
		UpdateMemberCardForm updateForm = UpdateMemberCardForm.newInstance();
		updateForm.setCardId(item.getMembershipCardId());
		updateForm.setLeaguerId(leaguerId);
		updateForm.setNumber(item.getNumber());
		updateForm.setLimitTime(item.getLimitTime());
		updateForm.setLimitUnit(item.getLimitUnit());
		return updateForm;
	}
	
	private RechargeMemberCardForm getRechargeMemberCardForm(String leaguerId, RechargeItem item) {
		RechargeMemberCardForm rechargeForm = RechargeMemberCardForm.newInstance();
		rechargeForm.setAmount(item.getAmount());
		rechargeForm.setCardId(item.getMembershipCardId());
		rechargeForm.setLeaguerId(leaguerId);
		return rechargeForm;
	}
	
	//会员卡余额抵扣
	public OperateTips reduceBalance(Order order, LeaguerDetail leaguer){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() == OrderTypeEnum.PURCHASE.ordinal()){//购买消费
			if(!reduceBalance(order, leaguer, order.getPayItems())) {
				operateTips.setSuccess(false);
				operateTips.setTips("会员卡余额抵扣失败");
				return operateTips;
			}
		}
		return operateTips;
	}
	
	//减少客户余额
	private boolean reduceBalance(Order order, LeaguerDetail leaguer, List<PayItem> payItems){
		float memshipCardCost=LeaguerDetailBeanHelper.getInstance().getMemshipCardCost(payItems);//会员卡抵扣金额
		if(memshipCardCost == 0){//没有使用会员卡余额抵扣
			return true;
		}
		long storeId = order.getStoreId();
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		
		if(!reduceZmtCardBalance(order, leaguer, storeCard, memshipCardCost)) {
			return false;
		}
		
		if(!reduceChainCardBalance(order, leaguer, storeCard, memshipCardCost)) {
			return false;
		}
		
		return true;
	}
	
	private boolean reduceZmtCardBalance(Order order, LeaguerDetail leaguer, StoreCardInfo storeCard, float memshipCardCost) {
		if(storeCard.memberCardIsFromChain(order.getMemberCardId())) {
			return true;
		}
		
		LeaguerDetail tmpDetail = LeaguerDetailMgr.getInstance().getSimple(order.getStoreId(), leaguer.getId());
		LeaguerMemberCard memberCard = tmpDetail.getLeaguerMemberCard();
		if(LeaguerDetailBeanHelper.getInstance().reduceBalance(memberCard, memshipCardCost)) {
			LeaguerDetailMgr.getInstance().updateSimple(tmpDetail);
			return true;
		}
		
		return false;
	}
	
	private boolean reduceChainCardBalance(Order order, LeaguerDetail leaguer, StoreCardInfo storeCard, float memshipCardCost) {
		if(!storeCard.memberCardIsFromChain(order.getMemberCardId())) {
			return true;
		}
		
		CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(leaguer.getCuserId());
		Store store = StoreMgr.getInstance().getSimple(order.getStoreId());
		Long chainId = store.takeChainId();
		if(chainId == null) {//店铺尚未加入连锁店
			return false;
		}
		
		LeaguerMemberCard memberCard = cuserChainData.takeLeaguerMemberCard(chainId);
		if(memberCard == null) {//连锁店的会员卡信息为空
			return false;
		}
		
		if(LeaguerDetailBeanHelper.getInstance().reduceBalance(memberCard, memshipCardCost)) {
			CuserChainDataMgr.getInstance().update(cuserChainData);
			return true;
		}
		return false;
	}
}
