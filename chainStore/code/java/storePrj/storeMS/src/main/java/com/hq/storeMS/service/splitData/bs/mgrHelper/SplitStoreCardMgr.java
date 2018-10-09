package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.SplitMarkEnum;
import com.hq.storeMS.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.storeMS.service.membershipCardDetail.bs.MembershipCardDetailMgr;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;
import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailMgr;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoMgr;
import com.hq.storeMS.service.storeCardInfo.data.MembershipCard;
import com.hq.storeMS.service.storeCardInfo.data.ProductCard;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class SplitStoreCardMgr {
	
	public static SplitStoreCardMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SplitStoreCardMgr.class);
	}
	
	public void splitData(long storeId) {
		try {
			StoreCardInfo storeData = StoreCardInfoMgr.getInstance().getByStoreId(storeId);
			splitMark4StoreCardInfo(storeData);
		} catch (Exception e) {
			MainLog.error(LogModule.Tmp, "SplitStoreCardMgr[splitData]", "拆分卡包数据失败"+storeId, e);
		}
	}

	//数据拆分
	private void splitMark4StoreCardInfo(StoreCardInfo storeData) {
		long storeId = storeData.getStoreId();
		if(storeData.getSplitMark() == SplitMarkEnum.AWAIT.ordinal()) {
			removeMembershipCardDetailData(storeId);
			removeProductCardDetailData(storeId);
			
			Collection<MembershipCard> memberCards = storeData.getMembershipCardMap().values();
			for (MembershipCard card : memberCards) {
				MembershipCardDetailMgr.getInstance().addWithId(MembershipCardDetail.newInstanceByMembershipCard(card, storeId));
			}
			Collection<ProductCard> productCards = storeData.getProductCardMap().values();
			for (ProductCard card : productCards) {
				ProductCardDetailMgr.getInstance().addWithId(ProductCardDetail.newInstanceByProductCard(card, storeId));
			}
			storeData.setSplitMark(SplitMarkEnum.FINISH.ordinal());
			StoreCardInfoMgr.getInstance().updateStoreCardInfo(storeData);
		}
	}
	
	private void removeMembershipCardDetailData(long storeId) {
		MembershipCardDetailQueryForm queryForm = MembershipCardDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<MembershipCardDetail> page = MembershipCardDetailMgr.getInstance().getMembershipCardDetailPageInfo(queryForm);
		List<MembershipCardDetail> list = page.getList();
		for (MembershipCardDetail MembershipCardDetail : list) {
			MembershipCardDetailMgr.getInstance().delete((MembershipCardDetail)MembershipCardDetail);
		}
	}
	
	private void removeProductCardDetailData(long storeId) {
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setStoreId(storeId);
		PageResp<ProductCardDetail> page = ProductCardDetailMgr.getInstance().getProductCardDetailPageInfo(queryForm);
		List<ProductCardDetail> list = page.getList();
		for (ProductCardDetail ProductCardDetail : list) {
			ProductCardDetailMgr.getInstance().delete((ProductCardDetail)ProductCardDetail);
		}
	}
}
