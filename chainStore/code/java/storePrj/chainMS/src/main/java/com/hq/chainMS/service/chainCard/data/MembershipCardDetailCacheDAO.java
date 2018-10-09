package com.hq.chainMS.service.chainCard.data;

import java.util.List;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailCacheDAO {

	public static MembershipCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailCacheDAO.class);
	}

	final private String suffix = "chainMembershipCardDetail";

	public void saveList(MembershipCardDetailQueryForm queryForm, List<MembershipCardDetail> list) {
		MembershipCardDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getChainId()), queryForm.getListId());
	}

	public List<MembershipCardDetail> getList(MembershipCardDetailQueryForm queryForm) {
		return MembershipCardDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getChainId()), queryForm.getListId());
	}
	
	public void save(MembershipCardDetail target) {
		MembershipCardDetailRedisDAO.getInstance().saveOne(getGroupName(target.getChainId()), target.getId(), target);
	}
	
	public MembershipCardDetail get(long chainId, String id) {
		return MembershipCardDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	public void delete(MembershipCardDetail target) {
		MembershipCardDetailRedisDAO.getInstance().delete(target.getId());
		MembershipCardDetailRedisDAO.getInstance().deleteList(getGroupName(target.getChainId()));
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}
