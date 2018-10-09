package com.hq.storeMS.service.membershipCardDetail.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetailCacheDAO;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetailDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailDataHolder {
	
	public static MembershipCardDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MembershipCardDetailDataHolder.class);
	}
	
	public void addWithId(MembershipCardDetail target) {
		MembershipCardDetailDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
		MembershipCardDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(MembershipCardDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		MembershipCardDetailDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		MembershipCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(MembershipCardDetail target) {
		MembershipCardDetailDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		MembershipCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public MembershipCardDetail get(long storeId, String id) {
		MembershipCardDetail target = MembershipCardDetailCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = MembershipCardDetailDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				MembershipCardDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<MembershipCardDetail> findMembershipCardDetailList(MembershipCardDetailQueryForm queryForm) {
		List<MembershipCardDetail> list = MembershipCardDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = MembershipCardDetailDAO.getInstance().findMembershipCardDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				MembershipCardDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}
