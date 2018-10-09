package com.hq.chainMS.service.chainCard.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.chainMS.service.detailDataVersion.data.DataVersionEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailMgr {

	public static MembershipCardDetailMgr getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailMgr.class);
	}
	
	private final DataVersionEnum dataVersionEnum = DataVersionEnum.MembershipCardDetail;
	
	public void addWithId(MembershipCardDetail target) {
		MembershipCardDetailDataHolder.getInstance().addWithId(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}

	public void update(MembershipCardDetail target) {
		MembershipCardDetailDataHolder.getInstance().updpate(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public void delete(MembershipCardDetail target) {
		MembershipCardDetailDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByChainId(target.getChainId(), dataVersionEnum);
	}
	
	public MembershipCardDetail get(long chainId, String id) {
		return MembershipCardDetailDataHolder.getInstance().get(chainId, id);
	}
	
	public PageResp<MembershipCardDetail> getMembershipCardDetailPageInfo(MembershipCardDetailQueryForm queryForm) {
		List<MembershipCardDetail> list = MembershipCardDetailDataHolder.getInstance().findMembershipCardDetailList(queryForm);
		List<MembershipCardDetail> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private List<MembershipCardDetail> filterRecord(MembershipCardDetailQueryForm queryForm, List<MembershipCardDetail> list){
		List<MembershipCardDetail> result = new ArrayList<MembershipCardDetail>();
		if(CollectionUtils.isNotEmpty(list)){
			for (MembershipCardDetail record : list) {
				if(isRightRecord(queryForm, record)){
					result.add(record);
				}
			}
		}
		
		Collections.sort(result, new Comparator<MembershipCardDetail>() {
			@Override
			public int compare(MembershipCardDetail o1, MembershipCardDetail o2) {
				 return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}
	
	private boolean isRightRecord(MembershipCardDetailQueryForm queryForm, MembershipCardDetail record){
		if(!checkCardNameOrNumber(queryForm.getCardNameOrNumber(), record)) {
			return false;
		}
		
		if(!checkStatus(queryForm.getStatusSet(), record)) {
			return false;
		}
		return true;
	}
	
	private boolean checkCardNameOrNumber(String cardNameOrNumber, MembershipCardDetail record) {
		if(StringUtils.isBlank(cardNameOrNumber)) {
			return true;
		}
		if(record.getName()!=null && record.getName().contains(cardNameOrNumber)) {
			return true;
		}
		if(record.getNumber()!=null && record.getNumber().contains(cardNameOrNumber)) {
			return true;
		}
		return false;
	}
	
	private boolean checkStatus(Set<Integer> statusSet, MembershipCardDetail record) {
		if(CollectionUtils.isNotEmpty(statusSet) && !statusSet.contains(record.getStatus())) {
			return false;
		}
		return true;
	}
}
