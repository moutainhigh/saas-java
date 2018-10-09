package com.hq.storeMS.service.chainCard.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainClient.service.chainCard.bs.ChainCardClientMgr;
import com.hq.chainClient.service.chainCard.bs.MembershipCardDetailClientMgr;
import com.hq.chainClient.service.chainCard.bs.ProductCardDetailClientMgr;
import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.chainCard.data.ChainCardCacheDAO;
import com.hq.storeMS.service.chainCard.data.MembershipCardDetailCacheDAO;
import com.hq.storeMS.service.chainCard.data.ProductCardDetailCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainCardDataHolder implements IntfDataHolder {

	public static ChainCardDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardDataHolder.class);
	}

	final private DataSynType synType = DataSynType.ChainCard;

	public ChainCard get(long id) {
		ChainCard data = ChainCardCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChainCardClientMgr.getInstance().get(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public ProductCardDetail getProductCardDetail(String productCardId, long chainId) {
		ProductCardDetail data = ProductCardDetailCacheDAO.getInstance().get(chainId, productCardId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ProductCardDetailClientMgr.getInstance().getProductCardDetail(chainId, productCardId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public MembershipCardDetail getMembershipCardDetail(String memberCardId, long chainId) {
		MembershipCardDetail data = MembershipCardDetailCacheDAO.getInstance().get(chainId, memberCardId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = MembershipCardDetailClientMgr.getInstance().getMembershipCardDetail(chainId, memberCardId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer) {
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if (NumberUtils.isNumber(id)) {
			ChainCard target = this.get(Long.valueOf(id));
			if (target != null) {
				long newVer = target.getVer();
				if (clientSynVer.getVer() < newVer) {
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data);
				}
			} else {
				MainLog.info(LogModule.ChainCard, "ChainCardDataHolder[getSynItem]", "获取详情对象为空[id=" + id + "]");
			}
		} else {
			MainLog.info(LogModule.ChainCard, "ChainCardDataHolder[getSynItem]", "数据同步失败[id=" + id + "]");
		}
		return item;
	}
}
