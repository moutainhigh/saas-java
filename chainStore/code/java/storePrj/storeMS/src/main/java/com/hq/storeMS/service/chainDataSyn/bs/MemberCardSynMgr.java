package com.hq.storeMS.service.chainDataSyn.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCard;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.chainCard.bs.ChainCardDataHolder;
import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.data.ChainDataStatusEnum;
import com.hq.storeMS.service.chainDataSyn.data.MemberCardSyn;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoDataHolder;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class MemberCardSynMgr {

	public static MemberCardSynMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MemberCardSynMgr.class);
	}

	public PageResp<MemberCardSyn> findChainMemberCard(ChainDataQueryForm queryForm) {
		List<MemberCardSyn> list = findList(queryForm);
		List<MemberCardSyn> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<MemberCardSyn> findList(ChainDataQueryForm queryForm) {
		long chainId = queryForm.getChainId();
		long storeId = queryForm.getStoreId();
		List<MemberCardSyn> result = new ArrayList<MemberCardSyn>();
		ChainCard chainData = ChainCardDataHolder.getInstance().get(chainId);
		StoreCardInfo storeData = StoreCardInfoDataHolder.getInstance().get(storeId);

		Collection<MembershipCard> array = chainData.getMembershipCardMap().values();
		for (MembershipCard tmp : array) {
			// 过滤掉 已删除 或者 已下架的
//			if (tmp.getEntityState() == EntityState.Deleted.ordinal()
//					|| tmp.getStatus() == CardStatusEnum.CLOSE.ordinal()) {
//				continue;
//			}
			// 分配到店
			if (!tmp.getApplyStoreIds().contains(storeId)) {
				continue;
			}
			MemberCardSyn data = MemberCardSyn.newInstanceByMemberCard(tmp, storeId, chainId);
			if (storeData.takeMembershipCardById(tmp.getId()) == null) {
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else if (storeData.takeMembershipCardById(tmp.getId()).getEntityState() == EntityState.Deleted.ordinal()){
				data.setSynStatus(ChainDataStatusEnum.NOT_HAVE.ordinal());
			} else {
				data.setSynStatus(ChainDataStatusEnum.HAVE.ordinal());
			}
			result.add(data);
		}
		return result;
	}

	private List<MemberCardSyn> filterRecord(ChainDataQueryForm queryForm, List<MemberCardSyn> list) {
		List<MemberCardSyn> result = new ArrayList<MemberCardSyn>();
		for (MemberCardSyn record : list) {
			if (isRightRecord(queryForm, record)) {
				result.add(record);
			}
		}
		Collections.sort(result, new Comparator<MemberCardSyn>() {
			@Override
			public int compare(MemberCardSyn o1, MemberCardSyn o2) {
				return o1.getNumber().compareTo(o2.getNumber());
			}
		});
		return result;
	}

	private boolean isRightRecord(ChainDataQueryForm queryForm, MemberCardSyn record) {
		if (!checkNumberOrName(queryForm.getNumberOrName(), record)) {
			return false;
		}
		
		if (!checkSynStatus(queryForm.getSynStatus(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkNumberOrName(String numberOrName, MemberCardSyn record) {
		if (StringUtils.isBlank(numberOrName)) {
			return true;
		}
		if (record.getName() != null && record.getName().contains(numberOrName)) {
			return true;
		}
		if (record.getNumber() != null && record.getNumber().contains(numberOrName)) {
			return true;
		}
		return false;
	}
	
	private boolean checkSynStatus(int synStatus, MemberCardSyn record) {
		if (synStatus == -1) {
			return true;
		}
		if (synStatus == record.getSynStatus()) {
			return true;
		}
		return false;
	}

}
