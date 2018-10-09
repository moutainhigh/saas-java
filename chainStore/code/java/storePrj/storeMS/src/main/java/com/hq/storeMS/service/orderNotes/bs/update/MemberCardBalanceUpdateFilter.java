package com.hq.storeMS.service.orderNotes.bs.update;

import java.util.List;

import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataMgr;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.RechargeMemberCardForm;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.zenmind.common.hotSwap.HotSwap;

public class MemberCardBalanceUpdateFilter {
	
	public static MemberCardBalanceUpdateFilter getInstance() {
		return HotSwap.getInstance().getSingleton(MemberCardBalanceUpdateFilter.class);
	}

	public OperateTips updateInfo(Order order, LeaguerDetail leaguer, ChargeBackRecordAddForm inputForm){
		OperateTips operateTips = OperateTips.newInstance(true);
		if(order.getOrderType() != OrderTypeEnum.PURCHASE.ordinal()){
			return operateTips;
		}
		List<PayItem> payItems = inputForm.getPayItems();
		float memshipCardCost = LeaguerDetailBeanHelper.getInstance().getMemshipCardCost(payItems);
		if(memshipCardCost == 0f) {
			return operateTips;
		}
		RechargeMemberCardForm rechargeMemberCardForm = RechargeMemberCardForm.newInstance();
		rechargeMemberCardForm.setAmount(memshipCardCost);
		rechargeMemberCardForm.setLeaguerId(leaguer.getId());
		
		long storeId = order.getStoreId();
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		
		rechargeZmtMemberCard(order, storeCard, rechargeMemberCardForm);
		rechargeChainMemberCard(leaguer, order, storeCard, rechargeMemberCardForm);
		return operateTips;
	}
	
	//退回本地会员卡余额
	private void rechargeZmtMemberCard(Order order, StoreCardInfo storeCard, RechargeMemberCardForm rechargeForm) {
		if(storeCard.memberCardIsFromChain(order.getMemberCardId())) {
			return ;
		}
		
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().getSimple(order.getStoreId(), order.getLeaguerId());
		LeaguerDetailBeanHelper.getInstance().rechargeMemberCard(leaguer.getLeaguerMemberCard(), rechargeForm);
		LeaguerDetailMgr.getInstance().updateSimple(leaguer);
	}
	
	//退回连锁店会员卡余额
	private void rechargeChainMemberCard(LeaguerDetail leaguer, Order order, StoreCardInfo storeCard, RechargeMemberCardForm rechargeForm) {
		if(!storeCard.memberCardIsFromChain(order.getMemberCardId())) {
			return ;
		}
		
		CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(leaguer.getCuserId());
		Store store = StoreMgr.getInstance().getSimple(order.getStoreId());
		
		Long chainId = store.takeChainId();
		if(chainId == null) {
			return ;
		}
		
		LeaguerMemberCard chainMemberCard = cuserChainData.takeLeaguerMemberCard(chainId);
		if(chainMemberCard == null) {
			return ;
		}
		
		LeaguerDetailBeanHelper.getInstance().rechargeMemberCard(chainMemberCard, rechargeForm);
		CuserChainDataMgr.getInstance().update(cuserChainData);
	}
	
}
