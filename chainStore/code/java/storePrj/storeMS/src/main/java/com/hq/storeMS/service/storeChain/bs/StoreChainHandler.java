package com.hq.storeMS.service.storeChain.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeMS.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeMS.service.storeCardInfo.bs.update.MembershipCardMgr;
import com.hq.storeMS.service.storeCardInfo.bs.update.ProductCardMgr;
import com.hq.storeMS.service.storeChain.apiData.StoreChainUpdateApiForm;
import com.hq.storeMS.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeMS.service.storeChain.apiData.StoreChainUpdateType;
import com.hq.storeMS.service.storeChain.data.StoreChain;
import com.hq.storeMS.service.storeChain.data.StoreChainItemType;
import com.hq.storeMS.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeMS.service.storeGoods.bs.update.StGoodsMgr;
import com.hq.storeMS.service.storePackageProject.apiData.PackageProjectUpdateStateForm;
import com.hq.storeMS.service.storePackageProject.bs.update.StPackageProjectMgr;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.bs.update.StProductMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreChainHandler {

	public static StoreChainHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreChainHandler.class);
	}

	private final LogModule logModule = LogModule.StoreChain;

	public ReqResult<StoreChain> updateStoreChain(StoreChainUpdateApiForm updateForm) {
		ReqResult<StoreChain> result = ReqResult.newInstance(false, StoreChain.class);
		try {
			StoreChainUpdateType updateType = updateForm.getStoreChainUpdateType();
			switch (updateType) {
			case BatchUpdateState:
				batchUpdateState(updateForm.getStoreChainUpdateStatusForms());
				break;
			default:
				break;
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "StoreChainHandler[updateStoreChain]";
			final String reason = LogHelper.getInstance().exceptionReason(updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	private void batchUpdateState(List<StoreChainUpdateStatusForm> list) {
		for (StoreChainUpdateStatusForm form : list) {
			updateStatus(form);
		}
	}

	private void updateStatus(StoreChainUpdateStatusForm form) {
		String id = form.getId();
		int status = form.getStatus();
		long storeId = form.getStoreId();
		StoreChainItemType type = form.getStoreChainItemType();
		switch (type) {
		case Goods:
			StGoodsMgr.getInstance().updateGoodsState(storeId, GoodsUpdateStateForm.newInstance(id, status));
			break;
		case MemberCard:
			MembershipCardMgr.getInstance().updMemberCardState(storeId, UpdMemberCardStateData.newInstance(id, storeId, status));
			break;
		case PackagePrj:
			StPackageProjectMgr.getInstance().updPackageProjectState(storeId, PackageProjectUpdateStateForm.newInstance(id, status));
			break;
		case Product:
			StProductMgr.getInstance().updateProductState(UpdateProductStateData.newInstance(id, storeId, status));
			break;
		case ProductCard:
			ProductCardMgr.getInstance().updProductCardState(storeId, UpdProductCardStateData.newInstance(id, storeId, status));
			break;
		default:
			break;
		}
	}
}
