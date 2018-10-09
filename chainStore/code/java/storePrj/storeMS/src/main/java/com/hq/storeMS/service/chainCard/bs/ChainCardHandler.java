package com.hq.storeMS.service.chainCard.bs;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCardHandler {

	public static ChainCardHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCardHandler.class);
	}
	
	private final LogModule logModule = LogModule.ChainCard;

	public ReqResult<ChainCard> getChainCard(long chainId) {
		ReqResult<ChainCard> result = ReqResult.newInstance(false, ChainCard.class);
		try {
			ChainCard chainCard = ChainCardMgr.getInstance().getChainCard(chainId);
			result.setTarget(chainCard);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainCardHandler[getChainCard]", reason, e);
		}
		return result;
	}
	
	public ReqResult<ProductCardDetail> getProductCardDetail(String productCardId, long chainId) {
		ReqResult<ProductCardDetail> result = ReqResult.newInstance(false, ProductCardDetail.class);
		try {
			ProductCardDetail detail = ChainCardMgr.getInstance().getProductCardDetail(productCardId, chainId);
			result.setTarget(detail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(productCardId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainCardHandler[getProductCardDetail]", reason, e);
		}
		return result;
	}
	
	public ReqResult<MembershipCardDetail> getMembershipCardDetail(String memberCardId, long chainId) {
		ReqResult<MembershipCardDetail> result = ReqResult.newInstance(false, MembershipCardDetail.class);
		try {
			MembershipCardDetail detail = ChainCardMgr.getInstance().getMembershipCardDetail(memberCardId, chainId);
			result.setTarget(detail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(memberCardId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainCardHandler[getMembershipCardDetail]", reason, e);
		}
		return result;
	}

}
