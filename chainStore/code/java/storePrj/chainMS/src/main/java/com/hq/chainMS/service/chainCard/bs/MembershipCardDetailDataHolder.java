package com.hq.chainMS.service.chainCard.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.validate.ValidateInfoThreadLocal;
import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetail;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetailCacheDAO;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class MembershipCardDetailDataHolder {
	
	public static MembershipCardDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(MembershipCardDetailDataHolder.class);
	}
	
	public void addWithId(MembershipCardDetail target) {
		MembershipCardDetailDAO.getInstance().addWithId(getBossId(target.getChainId()), target);
		MembershipCardDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(MembershipCardDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		MembershipCardDetailDAO.getInstance().updpate(getBossId(target.getChainId()), target);
		MembershipCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(MembershipCardDetail target) {
		MembershipCardDetailDAO.getInstance().delete(getBossId(target.getChainId()), target.getId());
		MembershipCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public MembershipCardDetail get(long chainId, String id) {
		MembershipCardDetail target = MembershipCardDetailCacheDAO.getInstance().get(chainId, id);
		if(target == null){
			target = MembershipCardDetailDAO.getInstance().get(getBossId(chainId), id);
			if(target != null){
				MembershipCardDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<MembershipCardDetail> findMembershipCardDetailList(MembershipCardDetailQueryForm queryForm) {
		List<MembershipCardDetail> list = MembershipCardDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = MembershipCardDetailDAO.getInstance().findMembershipCardDetailList(getBossId(queryForm.getChainId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				MembershipCardDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long chainId) {
		long bossId = ValidateInfoThreadLocal.getInstance().getBossId();
		if(bossId == 0) {
			Chain chain = ChainDataHolder.getInstance().get(chainId);
			if(chain!=null) {
				bossId = chain.getBossId();
			}
		}
		return bossId;
	}
}
