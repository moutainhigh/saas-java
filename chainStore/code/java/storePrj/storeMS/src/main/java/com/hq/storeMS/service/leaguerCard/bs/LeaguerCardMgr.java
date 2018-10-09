package com.hq.storeMS.service.leaguerCard.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.common.LoadDataEnum;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.SortEnum;
import com.hq.storeMS.service.leaguerCard.apiData.LeaguerCardQueryForm;
import com.hq.storeMS.service.leaguerCard.data.LeaguerCard;
import com.hq.storeMS.service.leaguerCard.data.LeaguerCardRedisDao;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailDataHolder;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeMS.service.storeConfig.bs.StoreConfigMgr;
import com.hq.storeMS.service.storeConfig.data.StoreConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.LeaguerAnalysisConfig;
import com.hq.storeMS.service.storeConfig.data.leaguer.SysInitLeaguerAnalysisEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerCardMgr {

	final private String suffix = "leaguerCard";
	
	public static LeaguerCardMgr getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerCardMgr.class);
	}
	
	public PageResp<LeaguerCard> getExpiredCardPageInfo(LeaguerCardQueryForm queryForm){
		List<LeaguerCard> listTmp = null;
		if(queryForm.getLoadType() == LoadDataEnum.DB.ordinal()){
			listTmp = buildLeaguerCardList(queryForm);
			if(null != listTmp){
				LeaguerCardRedisDao.getInstance().saveList(getGroupName(queryForm.getStoreId()), listTmp);
			}
		}else{
			listTmp = LeaguerCardRedisDao.getInstance().getList(getGroupName(queryForm.getStoreId()));
			if(null == listTmp){
				listTmp = buildLeaguerCardList(queryForm);//客户次卡列表 组装添加缓存
				if(null != listTmp){
					LeaguerCardRedisDao.getInstance().saveList(getGroupName(queryForm.getStoreId()), listTmp);
				}
			}
		}
		List<LeaguerCard> resultList = filterRecord(listTmp,queryForm);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<LeaguerCard> buildLeaguerCardList(LeaguerCardQueryForm queryForm) {
		List<LeaguerCard> list = new ArrayList<LeaguerCard>();
		LeaguerDetailQueryForm leaguerDetailQueryForm = LeaguerDetailQueryForm.newInstance();
		leaguerDetailQueryForm.setStoreId(queryForm.getStoreId()).setMinTime(0l);
		List<LeaguerDetail> leaguerDetailList = LeaguerDetailDataHolder.getInstance().findLeaguerDetailList(leaguerDetailQueryForm);
		if(CollectionUtils.isNotEmpty(leaguerDetailList)){
			StoreCardInfo storeCardInfo = StoreCardInfoMgr.getInstance().getByStoreId(queryForm.getStoreId());
			Map<String, ProductCard> productCardMap = storeCardInfo.getProductCardMap();
			StoreConfig storeConfig = StoreConfigMgr.getInstance().getByStoreId(queryForm.getStoreId());
			for (LeaguerDetail leaguerDetail : leaguerDetailList) {
				Map<String, LeaguerProductCard> leaguerProductCardMap = leaguerDetail.getLeaguerProductCardMap();
				for (String key : leaguerProductCardMap.keySet()) {
					LeaguerProductCard leaguerProductCard = leaguerProductCardMap.get(key);
					if(checkCardEndTime(storeConfig, leaguerProductCard.getEndTime())){
						LeaguerCard leaguerCard = LeaguerCard.newInstance();
						leaguerCard.setLeaguerId(leaguerDetail.getId());
						leaguerCard.setLeaguerName(leaguerDetail.getName());
						leaguerCard.setLeaguerPhone(leaguerDetail.getPhone());
						leaguerCard.setLastConsumeTime(leaguerDetail.getLastConsumeTime());
						leaguerCard.setCardId(leaguerProductCard.getCardId());
						leaguerCard.setCardName(productCardMap.get(leaguerProductCard.getCardId()).getName());
						leaguerCard.setCardTypeId(productCardMap.get(leaguerProductCard.getCardId()).getTypeId());
						leaguerCard.setCardEndTime(leaguerProductCard.getEndTime());
						list.add(leaguerCard);
					}
				}
			}
		}
		return list;
	}
	
	private boolean checkCardEndTime(StoreConfig storeConfig,long endTime){
		Map<Integer, LeaguerAnalysisConfig> leaguerAnalysisConfigMap = storeConfig.getLeaguerConfig().getLeaguerAnalysisConfigMap();
		SysInitLeaguerAnalysisEnum productcardExpiredThreshold = SysInitLeaguerAnalysisEnum.PRODUCTCARD_EXPIRED_THRESHOLD;
		long expiredThreshold = productcardExpiredThreshold.getThreshold();
		if(leaguerAnalysisConfigMap.containsKey(productcardExpiredThreshold.ordinal()+1)){
			expiredThreshold = leaguerAnalysisConfigMap.get(productcardExpiredThreshold.ordinal()+1).getThreshold();
		}
		long curTime = System.currentTimeMillis();
		if(endTime > curTime && (endTime - curTime) <= expiredThreshold * ServerConstants.ONE_DAY){//未过期 并在即将过期配置阈值范围内
			return true;
		}
		return false;
	}

	private List<LeaguerCard> filterRecord(List<LeaguerCard> list,LeaguerCardQueryForm queryForm) {
		List<LeaguerCard> result = new ArrayList<LeaguerCard>();
		if(CollectionUtils.isNotEmpty(list)){
			for (LeaguerCard record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<LeaguerCard>() {
			@Override
			public int compare(LeaguerCard o1, LeaguerCard o2) {
				if(queryForm.getSort() == SortEnum.DESC.ordinal()){
					return Long.compare(o2.getCardEndTime() , o1.getCardEndTime());
				}else{
					return Long.compare(o1.getCardEndTime() , o2.getCardEndTime());
				}
			}
		});
		return result;
	}
	
	private boolean isRightRecord(LeaguerCardQueryForm queryForm, LeaguerCard record){
		if(!checkLeaguerNameOrPhone(queryForm.getLeaguerNameOrPhone(), record)) {
			return false;
		}
		if(!checkCardTypeId(queryForm.getCardTypeId(), record)) {
			return false;
		}
		return true;
	}
	
	private boolean checkLeaguerNameOrPhone(String leaguerNameOrPhone, LeaguerCard record){
		if(StringUtils.isBlank(leaguerNameOrPhone)) {
			return true;
		}
		String leaguerName = record.getLeaguerName();
		if(leaguerName != null && leaguerName.contains(leaguerNameOrPhone)) {
			return true;
		}
		String phone = String.valueOf(record.getLeaguerPhone());
		if(phone.contains(leaguerNameOrPhone)) {
			return true;
		}
		return false;
	}
	
	private boolean checkCardTypeId(String cardTypeIdP, LeaguerCard record){
		if(StringUtils.isBlank(cardTypeIdP)) {
			return true;
		}
		String cardTypeId = record.getCardTypeId();
		if(StringUtils.equals(cardTypeIdP, cardTypeId)) {
			return true;
		}
		return false;
	}
	
	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
	
}
