package com.hq.storeMS.service.cuserChainData.bs;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.service.cuserChainData.data.ChainData;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetailBeanHelper;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeLeaguerInfo.apiData.UpdateMemberCardForm;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerCardEnum;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerMemberCard;
import com.hq.storeMS.service.storeLeaguerInfo.data.LeaguerProductCard;
import com.zenmind.common.hotSwap.HotSwap;

public class CuserChainDataMgr {

	public static CuserChainDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(CuserChainDataMgr.class);
	}
	
	public ChainData getModifyChainDataByStoreId(long cuserId, long storeId) {
		CuserChainData cuserChaindata = get(cuserId);
		Store store = StoreMgr.getInstance().getSimple(storeId);
		if(store!=null && store.takeChainId()!=null) {
			return cuserChaindata.takeChainData(store.takeChainId());
		}
		return null;
	}
	
	public List<CuserChainData> getListByIds(Collection<Long> ids){
		return CuserChainDataDataHolder.getInstance().getListByIds(ids);
	}
	
	//设置连锁店会员卡
	public boolean updateChainMemberCard(LeaguerDetail detail, UpdateMemberCardForm updateForm) {
		CuserChainData cuserChaindata = get(detail.getCuserId());
		Store store = StoreMgr.getInstance().getSimple(detail.getStoreId());
		if(store==null || store.takeChainId()==null) {
			return false;
		}
		
		ChainData chainData = cuserChaindata.takeChainData(store.takeChainId());
		if(chainData == null) {
			chainData = ChainData.newInstance();
			chainData.setChainId(store.takeChainId());
			cuserChaindata.getChainDataMap().put(chainData.getChainId(), chainData);
		}
		LeaguerMemberCard chainMemberCard = chainData.getChainMemberCard();
		LeaguerDetailBeanHelper.getInstance().updateMemberCard(chainMemberCard, updateForm);
		update(cuserChaindata);
		return true;
	}
	
	public void update(CuserChainData target) {
		CuserChainDataDataHolder.getInstance().updpate(target);
	}
	
	public CuserChainData getSimple(long cuserId) {
		CuserChainData detail = CuserChainDataDataHolder.getInstance().get(cuserId);
		if(detail == null){//不存在，则新增
			detail = CuserChainData.newInstance(cuserId);
			CuserChainDataDataHolder.getInstance().addWithId(detail);
		}
		return detail;
	}

	public CuserChainData get(long cuserId) {
		CuserChainData detail = getSimple(cuserId);
		checkCardValid(detail);
		return detail;
	}
	
	private void checkCardValid(CuserChainData detail){
		boolean flag = false;
		Collection<ChainData> values = detail.getChainDataMap().values();
		long nowTime = System.currentTimeMillis();
		for (ChainData data : values) {
			//客户会员卡
			LeaguerMemberCard leaguerMemberCard = data.getChainMemberCard();
			if(isInvalid(nowTime, leaguerMemberCard.getEndTime(), leaguerMemberCard.getState())){
				leaguerMemberCard.setState(LeaguerCardEnum.INVALID.ordinal());
				flag = true;
			}
			
			//遍历客户次卡
			Collection<LeaguerProductCard> prdCards = data.getChainPrdCardMap().values();
			for (LeaguerProductCard leaguerProductCard : prdCards) {
				if(isInvalid(nowTime, leaguerProductCard.getEndTime(), leaguerProductCard.getState())){
					//设置过期失效
					leaguerProductCard.setState(LeaguerCardEnum.INVALID.ordinal());
					flag = true;
				}
			}
		}
		
		if(flag) {
			update(detail);
		}
	}
	
	private boolean isInvalid(long nowTime, long endTime, int state) {
		return state == LeaguerCardEnum.VALID.ordinal() && endTime > 0 && nowTime > endTime;
	}
}
