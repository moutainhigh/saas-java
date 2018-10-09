package com.hq.storeMS.service.arrearage.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.arrearage.apiData.ArrearageAddPaymentHistoryApiForm;
import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.storeMS.service.arrearage.data.Arrearage;
import com.hq.storeMS.service.arrearage.data.ArrearageBeanHelper;
import com.hq.storeMS.service.arrearage.data.ArrearageGroup;
import com.hq.storeMS.service.arrearage.data.PaymentHistory;
import com.hq.storeMS.service.arrearage.data.PaymentTypeEnum;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataMgr;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageMgr {

	public static ArrearageMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageMgr.class);
	}
	
	private final DataVersionEnum dataVersionEnum = DataVersionEnum.Arrearage;
	
	public OperateTips addPaymentHistory(long storeId, long arrearageId, ArrearageAddPaymentHistoryApiForm addForm){
		OperateTips operateTips = OperateTips.newInstance(false, "新增还款记录失败");
		Arrearage arrearage = get(storeId, arrearageId);
		LeaguerDetail leaguer = LeaguerDetailMgr.getInstance().get(arrearage.getStoreId(), arrearage.getLeaguerId());
		float memshipCardCost = LeaguerDetailBeanHelper.getInstance().getMemshipCardCost(addForm.getPayItems());
		if(LeaguerDetailBeanHelper.getInstance().isBalanceGteCost(leaguer.getLeaguerMemberCard(), memshipCardCost)){
			BUser sessionBUser = BUserAuthUtils.getInstance().getSessionBUser();
			addForm.setCreatorId(sessionBUser.getId());
			addForm.setCreatorName(sessionBUser.getName());
			if(ArrearageBeanHelper.getInstance().addPaymentHistory(arrearage, addForm)){
				ArrearageMgr.getInstance().update(arrearage);
				reduceLeaguerBalance(leaguer, memshipCardCost);
				operateTips.setSuccess(true);
			}else{
				operateTips.setTips("支付金额大于欠款金额");
			}
		}else{
			operateTips.setTips("客户会员卡余额不足");
		}
		return operateTips;
	}
	
	private void reduceLeaguerBalance(LeaguerDetail leaguer, float memshipCardCost){
		if(memshipCardCost <= 0){
			return ;
		}
		long storeId = leaguer.getStoreId();
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		
		LeaguerMemberCard leaguerMemberCard = leaguer.getLeaguerMemberCard();
		
		boolean chainFlag = false;
		
		if(storeCard.memberCardIsFromChain(leaguerMemberCard.getCardId())) {//连锁店的卡
			CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(leaguer.getCuserId());
			Store store = StoreMgr.getInstance().getSimple(storeId);
			Long chainId = store.takeChainId();
			if(chainId != null) {
				LeaguerMemberCard memberCard = cuserChainData.takeLeaguerMemberCard(chainId);
				if(memberCard != null) {
					chainFlag = true;
					if(LeaguerDetailBeanHelper.getInstance().reduceBalance(memberCard, memshipCardCost)) {
						CuserChainDataMgr.getInstance().update(cuserChainData);
					}
				}
			}
		}
		if(!chainFlag) {//连锁卡扣失败  则使用本地会员卡余额抵扣
			LeaguerDetail tmp = LeaguerDetailMgr.getInstance().getSimple(storeId, leaguer.getId());
			LeaguerDetailBeanHelper.getInstance().reduceBalance(tmp.getLeaguerMemberCard(), memshipCardCost);
			LeaguerDetailMgr.getInstance().updateSimple(tmp);
		}
	}
	
	// 获取店铺时间段内的还款记录列表
	public List<PaymentHistory> findPaymentHistorys(long storeId, long minTime, long maxTime) {
		ArrearageQueryForm queryForm = ArrearageQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		List<Arrearage> arrearages = ArrearageDataHolder.getInstance().findArrearageList(queryForm);
		List<PaymentHistory> list = new ArrayList<PaymentHistory>();
		for (Arrearage data : arrearages) {
			list.addAll(data.getPaymentHistories());
		}
		return filterRecord(list, minTime, maxTime);
	}

	private List<PaymentHistory> filterRecord(List<PaymentHistory> list, long minTime, long maxTime) {
		if(maxTime == 0L) {
			maxTime = Long.MAX_VALUE;
		}
		List<PaymentHistory> result = new ArrayList<PaymentHistory>();
		for (PaymentHistory record : list) {
			if (record.getPaymentType() == PaymentTypeEnum.Payment.ordinal() && record.getCreatedTime() >= minTime && record.getCreatedTime() <= maxTime) {
				result.add(record);
			}
		}
		return result;
	}

	public void addAndReturnId(Arrearage target) {
		ArrearageDataHolder.getInstance().addAndReturnId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}

	public void update(Arrearage target) {
		ArrearageDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public void delete(Arrearage target) {
		ArrearageDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	public Arrearage get(long storeId, long id) {
		return ArrearageDataHolder.getInstance().get(storeId, id);
	}
	
	public Arrearage findArrearageByOrderId(long storeId, long orderId) {
		return ArrearageDataHolder.getInstance().findArrearageByOrderId(storeId, orderId);
	}

	public PageResp<Arrearage> getArrearagePageInfo(ArrearageQueryForm queryForm) {
		List<Arrearage> list = ArrearageDataHolder.getInstance().findArrearageList(queryForm);
		List<Arrearage> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public List<Arrearage> findArrearageList(ArrearageQueryForm queryForm) {
		List<Arrearage> list = ArrearageDataHolder.getInstance().findArrearageList(queryForm);
		List<Arrearage> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().getPageItemList(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public PageResp<ArrearageGroup> getArrearageGroupPageInfo(ArrearageQueryForm queryForm) {
		List<Arrearage> list = ArrearageDataHolder.getInstance().findArrearageList(queryForm);
		List<ArrearageGroup> resultList = packArrearageGroupList(filterRecord(queryForm, list));
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public List<ArrearageGroup> findArrearageGroupList(ArrearageQueryForm queryForm) {
		List<Arrearage> list = ArrearageDataHolder.getInstance().findArrearageList(queryForm);
		List<ArrearageGroup> resultList = packArrearageGroupList(filterRecord(queryForm, list));
		return PageUtil.getInstance().getPageItemList(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<ArrearageGroup> packArrearageGroupList(List<Arrearage> list){
		Map<String, ArrearageGroup> groupMap = new HashMap<String, ArrearageGroup>();
		for (Arrearage arrearage : list) {
			ArrearageGroup arrearageGroup = groupMap.get(arrearage.getLeaguerId());
			if(arrearageGroup == null){
				arrearageGroup = ArrearageGroup.newInstance();
				arrearageGroup.setLeaguerId(arrearage.getLeaguerId());
				arrearageGroup.setLeaguerName(arrearage.getLeaguerName());
				arrearageGroup.setLeaguerPhone(arrearage.getLeaguerPhone());
				groupMap.put(arrearage.getLeaguerId(), arrearageGroup);
			}
			arrearageGroup.setTotalAmount(arrearageGroup.getTotalAmount() + arrearage.getBalanceDue());
		}
		List<ArrearageGroup> result = new ArrayList<ArrearageGroup>(groupMap.values());
		Collections.sort(result, new Comparator<ArrearageGroup>(){
			@Override
			public int compare(ArrearageGroup o1, ArrearageGroup o2) {
	            return Float.compare(o2.getTotalAmount(), o1.getTotalAmount());
			}
		});
		return result;
	}
	
	private List<Arrearage> filterRecord(ArrearageQueryForm queryForm, List<Arrearage> list){
		List<Arrearage> result = new ArrayList<Arrearage>();
		if(CollectionUtils.isNotEmpty(list)){
			for (Arrearage arrearage : list) {
				if(isRightRecord(queryForm, arrearage)){
					result.add(arrearage);
				}
			}
		}
		return result;
	}
	
	private boolean isRightRecord(ArrearageQueryForm queryForm, Arrearage arrearage){
		//客户名称或者号码  模糊匹配
		String leaguerNameOrPhone = queryForm.getLeaguerNameOrPhone();
		if(StringUtils.isNoneBlank(leaguerNameOrPhone)){
			String leaguerPhone = String.valueOf(arrearage.getLeaguerPhone());
			String leaguerName = arrearage.getLeaguerName();
			if((leaguerName.indexOf(leaguerNameOrPhone) == -1) && (leaguerPhone.indexOf(leaguerNameOrPhone) == -1)){
				return false;
			}
		}
		
		//状态匹配
		if(CollectionUtils.isNotEmpty(queryForm.getStatusSet()) && !queryForm.getStatusSet().contains(arrearage.getStatus())){
			return false;
		}
		
		//客户ID
		if(StringUtils.isNoneBlank(queryForm.getLeaguerId()) && !queryForm.getLeaguerId().equals(arrearage.getLeaguerId())){
			return false;
		}
		
		return true;
	}
}
