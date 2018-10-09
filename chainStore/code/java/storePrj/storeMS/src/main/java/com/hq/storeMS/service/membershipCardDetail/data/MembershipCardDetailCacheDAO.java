package com.hq.storeMS.service.membershipCardDetail.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailCacheDAO {

	public static MembershipCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailCacheDAO.class);
	}

	final private String suffix = "storeMembershipCardDetail";

	public void saveList(MembershipCardDetailQueryForm queryForm, List<MembershipCardDetail> list) {
		MembershipCardDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<MembershipCardDetail> getList(MembershipCardDetailQueryForm queryForm) {
		return MembershipCardDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(MembershipCardDetail target) {
		MembershipCardDetailRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), target.getId(), target);
	}
	
	public MembershipCardDetail get(long storeId, String id) {
		return MembershipCardDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	public void delete(MembershipCardDetail target) {
		MembershipCardDetailRedisDAO.getInstance().delete(target.getId());
		MembershipCardDetailRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
