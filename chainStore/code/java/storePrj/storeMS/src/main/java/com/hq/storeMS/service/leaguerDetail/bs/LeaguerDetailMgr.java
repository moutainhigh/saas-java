package com.hq.storeMS.service.leaguerDetail.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainClient.service.chain.data.Chain;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.chain.bs.ChainMgr;
import com.hq.storeMS.service.common.ExpiredStateEnum;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.SortEnum;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataMgr;
import com.hq.storeMS.service.cuserChainData.data.ChainData;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerTypeEnum;
import com.hq.storeMS.service.leaguerDetail.data.MemberCardExist;
import com.hq.storeMS.service.leaguerDetail.data.SortTypeEnum;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerAnalysisEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.AttentionTypeEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerCardEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LimitUnitEnum;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailMgr {

	public static LeaguerDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerDetailMgr.class);
	}
	
	private final DataVersionEnum dataVersionEnum = DataVersionEnum.LeaguerDetail;
	
	//新增客户详情，如果有连锁店的会员卡 则默认进行绑定
	public void addWithId(LeaguerDetail target) {
		Chain chain = ChainMgr.getInstance().getChainByStoreId(target.getStoreId());
		if(chain!=null) {//店铺属于连锁店
			CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(target.getCuserId());
			LeaguerMemberCard chainMemberCard = cuserChainData.takeLeaguerMemberCard(chain.getId());
			if(chainMemberCard != null) {
				StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(target.getStoreId());
				if(storeCard.memberCardIsFromChain(chainMemberCard.getCardId())) {//本地有连锁店的会员卡
					LeaguerMemberCard memberCard = LeaguerMemberCard.newInstance();
					FastBeanCopyer.getInstance().copy(chainMemberCard, memberCard);
					memberCard.setBalance(0f);//本地不保存连锁卡的余额
					target.setLeaguerMemberCard(memberCard);
				}
			}
		}
		LeaguerDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}

	//更新客户原始属性的信息[不包括连锁店的会员卡与次卡]
	public void updateSimple(LeaguerDetail target) {
		LeaguerDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	//更新客户的信息[包括连锁店的会员卡与次卡]
	public void updateLeaguerCard(LeaguerDetail target) {
		//先更新cuserChainData的信息
		updateCuserChainData(target);
		
		//回填旧的会员卡信息
		LeaguerMemberCard oldMemberCard = getSimple(target.getStoreId(), target.getId()).getLeaguerMemberCard();
		target.setLeaguerMemberCard(oldMemberCard);
		
		updateSimple(target);
	}
	
	// 只更新次卡的信息  会员卡信息不做处理
	private void updateCuserChainData(LeaguerDetail target) {
		Store store = StoreMgr.getInstance().getSimple(target.getStoreId());
		if(store == null || store.takeChainId() == null) {//店铺尚未加入连锁店
			return ;
		}
		Long chainId = store.takeChainId();
		CuserChainData cuserChainData = CuserChainDataMgr.getInstance().getSimple(target.getCuserId());
		boolean updateFlag = false;
		
		//属于连锁店数据
		ChainData chainData = cuserChainData.takeChainData(chainId);
		if(chainData==null) {
			chainData = ChainData.newInstance();
			chainData.setChainId(chainId);
			cuserChainData.getChainDataMap().put(chainId, chainData);
			updateFlag = true;
		}
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(target.getStoreId());
		
		//次卡
		Collection<LeaguerProductCard> values = target.getLeaguerProductCardMap().values();
		Iterator<LeaguerProductCard> iter = values.iterator();
        while (iter.hasNext()){
        	LeaguerProductCard data = iter.next();
            if(storeCard.prdCardIsFromChain(data.getCardId())) {//连锁店的次卡
            	Map<String, LeaguerProductCard> chainPrdCardMap = chainData.getChainPrdCardMap();
				if(chainPrdCardMap.containsKey(data.getId())) {//已有次卡
            		chainPrdCardMap.put(data.getId(), data);
            	}else {//新购次卡
            		int index = chainData.getChainPrdCardIndex()+1;
            		data.setId(StringFormatUtil.format("{}_{}", ServerConstants.CHAIN_LEAGUERPRDCARD_ID_SUFFFIX, index));
            		chainData.setChainPrdCardIndex(index);
            		chainPrdCardMap.put(data.getId(), data);
            	}
				
            	iter.remove();
            	updateFlag = true;
            }
        }
        if(updateFlag) {
        	CuserChainDataMgr.getInstance().update(cuserChainData);
        }
	}
	
	public void delete(LeaguerDetail target) {
		LeaguerDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	//获取客户详情[不带加强信息，一般用于系统内部的只读操作]
	public LeaguerDetail getSimple(long storeId, String id) {
		return LeaguerDetailDataHolder.getInstance().get(storeId, id);
	}
	
	public LeaguerDetail get(long storeId, String id) {
		LeaguerDetail detail = getSimple(storeId, id);
		if(detail == null) {
			return null;
		}
		checkCardValid(detail);
		backfillCardInfo(detail);
		return detail;
	}
	
	//融合连锁客户与本地客户的卡信息
	private void backfillCardInfo(LeaguerDetail detail) {
		ChainData chainData = CuserChainDataMgr.getInstance().getModifyChainDataByStoreId(detail.getCuserId(), detail.getStoreId());
		if(chainData == null) {
			return ;
		}
		//会员卡信息
		StoreCardInfo storeCard = StoreCardInfoMgr.getInstance().getByStoreId(detail.getStoreId());
		LeaguerMemberCard leaguerMemberCard = detail.getLeaguerMemberCard();
		if(storeCard.memberCardIsFromChain(leaguerMemberCard.getCardId())) {
			LeaguerMemberCard chainMemberCard = chainData.getChainMemberCard();
			leaguerMemberCard.setBalance(chainMemberCard.getBalance());
		}
		
		//次卡信息
		Map<String, LeaguerProductCard> leaguerProductCardMap = detail.getLeaguerProductCardMap();
		Collection<LeaguerProductCard> chainPrdCards = chainData.getChainPrdCardMap().values();
		for (LeaguerProductCard leaguerProductCard : chainPrdCards) {
			leaguerProductCardMap.put(leaguerProductCard.getId(), leaguerProductCard);
		}
	}
	
	public MemberCardExist checkMemberCardExist(String memberCardNumber, long storeId) {
		MemberCardExist existResult = MemberCardExist.newInstance(false);
		if(StringUtils.isBlank(memberCardNumber)) {
			return existResult;
		}
		
		List<LeaguerDetail> list = getLeaguerDetailListByStoreId(storeId);
		if(CollectionUtils.isNotEmpty(list)) {
			for (LeaguerDetail leaguerDetail : list) {
				LeaguerMemberCard leaguerMemberCard = leaguerDetail.getLeaguerMemberCard();
				if(memberCardNumber.equals(leaguerMemberCard.getNumber())) {
					existResult.setExist(true);
					existResult.setTips("会员卡号已经存在");
					break;
				}
			}
		}
		return existResult;
	}
	
	/**
	 * 检查过期时间 小于当前时间则设置卡片过期
	 * @param leaguerProductCardMap
	 */
	private void checkCardValid(LeaguerDetail detail){
		boolean flag = false;
		long nowTime = System.currentTimeMillis();
		
		//会员卡
		LeaguerMemberCard leaguerMemberCard = detail.getLeaguerMemberCard();
		if(isInvalid(nowTime, leaguerMemberCard.getEndTime(), leaguerMemberCard.getState())){
			leaguerMemberCard.setState(LeaguerCardEnum.INVALID.ordinal());
			flag = true;
		}
		
		//次卡
		Collection<LeaguerProductCard> values = detail.getLeaguerProductCardMap().values();
		for (LeaguerProductCard leaguerProductCard : values) {
			if(isInvalid(nowTime, leaguerMemberCard.getEndTime(), leaguerMemberCard.getState())){
				leaguerProductCard.setState(LeaguerCardEnum.INVALID.ordinal());
				flag = true;
			}
		}
		if(flag){
			updateSimple(detail);
		}
	}
	
	private boolean isInvalid(long nowTime, long endTime, int state) {
		return state == LeaguerCardEnum.VALID.ordinal() && endTime > 0 && nowTime > endTime;
	}
	
	public List<LeaguerDetail> getLeaguerDetailListByStoreId(long storeId) {
		LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance().setStoreId(storeId);
		return LeaguerDetailDataHolder.getInstance().findLeaguerDetailList(queryForm);
	}
	
	public PageResp<LeaguerDetail> getLeaguerDetailPageInfo(LeaguerDetailQueryForm queryForm) {
		List<LeaguerDetail> list = LeaguerDetailDataHolder.getInstance().findLeaguerDetailList(queryForm);
		StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(queryForm.getStoreId());
		List<LeaguerDetail> resultList = filterRecord(queryForm, list, storeConfig);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<LeaguerDetail> filterRecord(LeaguerDetailQueryForm queryForm, List<LeaguerDetail> list, StoreConfig storeConfig){
		List<LeaguerDetail> result = new ArrayList<LeaguerDetail>();
		if(CollectionUtils.isNotEmpty(list)){
			//合并连锁店客户卡信息
			mergeLeaguerChainCard(queryForm.getStoreId(), list);
			
			for (LeaguerDetail record : list) {
				if(isRightRecord(queryForm, record, storeConfig)){
					result.add(record);
				}
				checkCardValid(record);
//				backfillCardInfo(record);
			}
		}
		
		SortTypeEnum sortType = SortTypeEnum.valueOf(queryForm.getSortType());
		SortEnum sort = SortEnum.valueOf(queryForm.getSort());
		
		Collections.sort(result, new Comparator<LeaguerDetail>() {
			@Override
			public int compare(LeaguerDetail o1, LeaguerDetail o2) {
				int result = 0;
				switch (sortType) {
					case LastUpdateTime:
						if(sort == SortEnum.DESC) {
							result = Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
						}else {
							result = Long.compare(o1.getLastUpdateTime(), o2.getLastUpdateTime());
						}
						break;
					case AvgPrice:
						if(sort == SortEnum.DESC) {
							result = Float.compare(o2.getAvgPrice(), o1.getAvgPrice());
						}else {
							result = Float.compare(o1.getAvgPrice(), o2.getAvgPrice());
						}
						break;
					case ConsumeAmount:
						if(sort == SortEnum.DESC) {
							result = Float.compare(o2.getConsumeAmount(), o1.getConsumeAmount());
						}else {
							result = Float.compare(o1.getConsumeAmount(), o2.getConsumeAmount());
						}
						break;
					case LastConsumeTime:
						if(sort == SortEnum.DESC) {
							result = Long.compare(o2.getLastConsumeTime(), o1.getLastConsumeTime());
						}else {
							result = Long.compare(o1.getLastConsumeTime(), o2.getLastConsumeTime());
						}
						break;
					case MonthRate:
						if(sort == SortEnum.DESC) {
							result = Integer.compare(o2.getMonthRate(), o1.getMonthRate());
						}else {
							result = Integer.compare(o1.getMonthRate(), o2.getMonthRate());
						}
						break;
					case MemberCardEndTime:
						if(sort == SortEnum.DESC) {
							result = Long.compare(o2.getLeaguerMemberCard().getEndTime(), o1.getLeaguerMemberCard().getEndTime());
						}else {
							result = Long.compare(o1.getLeaguerMemberCard().getEndTime(), o2.getLeaguerMemberCard().getEndTime());
						}
						break;
					case MemberCardBalance:
						if(sort == SortEnum.DESC) {
							result = Float.compare(o2.getLeaguerMemberCard().getBalance(), o1.getLeaguerMemberCard().getBalance());
						}else {
							result = Float.compare(o1.getLeaguerMemberCard().getBalance(), o2.getLeaguerMemberCard().getBalance());
						}
						break;
					default:
						break;
				}
				return result;
			}
		});
		return result;
	}

	//组装客户ids 请求客户连锁店卡信息CuserChainData列表 合并连锁店客户卡信息
	private void mergeLeaguerChainCard(long storeId, List<LeaguerDetail> list) {
		Store store = StoreMgr.getInstance().getSimple(storeId);
		if(store!=null && store.takeChainId()!=null) {
			List<CuserChainData> cuserChainDataList = getCuserChainList(storeId, list);
			Map<Long, LeaguerDetail> leaguerMap = toLeaguerMap(list);
			for (CuserChainData cuserChainData : cuserChainDataList) {
				ChainData chainData = cuserChainData.takeChainData(store.takeChainId());
				LeaguerDetail leaguerDetail = leaguerMap.get(cuserChainData.getId());
				//会员卡信息
				LeaguerMemberCard leaguerMemberCard = leaguerDetail.getLeaguerMemberCard();
				LeaguerMemberCard chainMemberCard = chainData.getChainMemberCard();
				leaguerMemberCard.setBalance(chainMemberCard.getBalance());
				
				//次卡信息
				Map<String, LeaguerProductCard> leaguerProductCardMap = leaguerDetail.getLeaguerProductCardMap();
				Collection<LeaguerProductCard> chainPrdCards = chainData.getChainPrdCardMap().values();
				for (LeaguerProductCard leaguerProductCard : chainPrdCards) {
					leaguerProductCardMap.put(leaguerProductCard.getId(), leaguerProductCard);
				}
			}
		}
	}

	private Map<Long, LeaguerDetail> toLeaguerMap(List<LeaguerDetail> list) {
		Map<Long,LeaguerDetail> leaguerMap = new HashMap<Long,LeaguerDetail>();
		for (LeaguerDetail detail : list) {
			leaguerMap.put(detail.getCuserId(), detail);
		}
		return leaguerMap;
	}

	private List<CuserChainData> getCuserChainList(long storeId, List<LeaguerDetail> list) {
		List<CuserChainData> cuserChainDataList = new ArrayList<CuserChainData>();
		Set<Long> cuserIds = new HashSet<Long>();
		StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
		for (LeaguerDetail leaguerDetail : list) {
			LeaguerMemberCard leaguerMemberCard = leaguerDetail.getLeaguerMemberCard();
			if(storeCardInfo.memberCardIsFromChain(leaguerMemberCard.getCardId())) {
				cuserIds.add(leaguerDetail.getCuserId());
			}
		}
		if(!cuserIds.isEmpty()){
			cuserChainDataList = CuserChainDataMgr.getInstance().getListByIds(cuserIds);
		}
		return cuserChainDataList;
	}
	
	private boolean isRightRecord(LeaguerDetailQueryForm queryForm, LeaguerDetail record, StoreConfig storeConfig){
		if(!checkBUserId(queryForm.getBuserId(), record)) {
			return false;
		}
		if(!checkLeaguerType(queryForm.getLeaguerType(), record)) {
			return false;
		}
		if(!checkLeaguerNameOrPhone(queryForm.getLeaguerNameOrPhone(), record)) {
			return false;
		}
		if(queryForm.getSortType() == SortTypeEnum.MemberCardEndTime.ordinal()){
			if(!checkMemberCardEndTime(record,storeConfig,queryForm.getMemberCardExpiredState())){
				return false;
			}
		}
		if(queryForm.getSortType() == SortTypeEnum.MemberCardBalance.ordinal()){
			if(!checkMemberCardBalance(record,storeConfig)){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkBUserId(long buserId, LeaguerDetail record){
		if(buserId == 0) {
			return true;
		}
		
		if(record.getBuserIds().contains(buserId)) {
			return true;
		}
		return false;
	}
	
	private boolean checkLeaguerType(int leaguerType, LeaguerDetail record){
		//全部
		if(leaguerType == 0) {
			return true;
		}
		//标星客
		if(leaguerType == LeaguerTypeEnum.ATTENTION_CUSTOMER.ordinal()) {
			if(record.getAttention() == AttentionTypeEnum.STAR.ordinal()) {
				return true;
			}
		}
		//优质客、风险客、静止客
		if(leaguerType > 0 && leaguerType < 4 ) {
			if(leaguerType == record.getCustomerType()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkLeaguerNameOrPhone(String leaguerNameOrPhone, LeaguerDetail record){
		if(StringUtils.isBlank(leaguerNameOrPhone)) {
			return true;
		}
		String leaguerName = record.getName();
		if(leaguerName != null && leaguerName.contains(leaguerNameOrPhone)) {
			return true;
		}
		String phone = String.valueOf(record.getPhone());
		if(phone.contains(leaguerNameOrPhone)) {
			return true;
		}
		return false;
	}
	
	private boolean checkMemberCardEndTime(LeaguerDetail record, StoreConfig storeConfig, int memberCardExpiredState){
		String cardId = record.getLeaguerMemberCard().getCardId();
		int limitUnit = record.getLeaguerMemberCard().getLimitUnit();
		long endTime = record.getLeaguerMemberCard().getEndTime();
		//判断storeConfig配置时间  筛选符合会员卡即将过期条件的客户 考虑是否需要做单独的统计接口
		Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap = storeConfig.getLeaguerConfig().getLeaguerAnalysisConfigMap();
		SysInitLeaguerAnalysisEnum membercardExpiredThreshold = SysInitLeaguerAnalysisEnum.MEMBERCARD_EXPIRED_THRESHOLD;
		long expiredThreshold = membercardExpiredThreshold.getThreshold();
		if(leaguerAnalysisConfigMap.containsKey(membercardExpiredThreshold.ordinal()+1)){
			expiredThreshold = leaguerAnalysisConfigMap.get(membercardExpiredThreshold.ordinal()+1).getThreshold();
		}
		long currentTimeMillis = System.currentTimeMillis();
		if(StringUtils.isNotBlank(cardId) && (limitUnit != LimitUnitEnum.EMPTY.ordinal()) 
				&& (endTime - currentTimeMillis) < expiredThreshold * ServerConstants.ONE_DAY){//到期时间非永久  小于配置天数
			if(memberCardExpiredState == ExpiredStateEnum.UNEXPIRED.ordinal() && endTime > currentTimeMillis){//未过期(大于当前时间)
				return true;
			}else if(memberCardExpiredState == ExpiredStateEnum.EXPIRED.ordinal() && endTime <= currentTimeMillis){//已过期(小于等于当前时间)
				return true;
			}
			return false;
		}
		return false;
	}
	
	private boolean checkMemberCardBalance(LeaguerDetail record, StoreConfig storeConfig) {
		float balance = record.getLeaguerMemberCard().getBalance();
		//判断storeConfig 配置金额 小于配置金额 筛选符合会员卡余额即将过期条件的客户 考虑是否需要做单独的统计接口
		Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap = storeConfig.getLeaguerConfig().getLeaguerAnalysisConfigMap();
		SysInitLeaguerAnalysisEnum membercardBalanceThreshold = SysInitLeaguerAnalysisEnum.MEMBERCARD_BALANCE_THRESHOLD;
		long balanceThreshold = membercardBalanceThreshold.getThreshold();
		if(leaguerAnalysisConfigMap.containsKey(membercardBalanceThreshold.ordinal()+1)){
			balanceThreshold = leaguerAnalysisConfigMap.get(membercardBalanceThreshold.ordinal()+1).getThreshold();
		}
		if(balance > 0l && balance <= (float)balanceThreshold){
			return true;
		}
		return false;
	}
	
}
