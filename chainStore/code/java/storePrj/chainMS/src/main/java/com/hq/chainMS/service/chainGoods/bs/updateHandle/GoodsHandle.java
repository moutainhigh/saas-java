package com.hq.chainMS.service.chainGoods.bs.updateHandle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.buserMessage.apiData.ProductMessageForm;
import com.hq.chainMS.service.buserMessage.bs.BUserMessageMgr;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateType;
import com.hq.chainMS.service.chainGoods.apiData.GoodsAddForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsAllotForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsRemoveForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsUpdateForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainMS.service.chainGoods.bs.ChainGoodsMgr;
import com.hq.chainMS.service.chainGoods.bs.GoodsDetailMgr;
import com.hq.chainMS.service.chainGoods.data.ChainGoods;
import com.hq.chainMS.service.chainGoods.data.ChainGoodsBeanHelper;
import com.hq.chainMS.service.chainGoods.data.Goods;
import com.hq.chainMS.service.chainGoods.data.GoodsDetail;
import com.hq.chainMS.service.chainGoods.data.GoodsStateEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.storeChain.bs.StoreChainMgr;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.data.StoreChainItemType;
import com.hq.storeClient.service.storeChain.data.StoreChainStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsHandle {
	public static GoodsHandle getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsHandle.class);
	}

	// 添加商品
	public OperateTips addGoods(long chainId, GoodsAddForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.AddGoods.getDescript() + "失败");
		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);

		if (checkNumberExists4Add(inputForm.getNumber(), chainGoods.getGoodsMap().values())) {
			tips.setTips("商品编号已存在");
			return tips;
		}
		
		if (ChainGoodsBeanHelper.getInstance().addGoods(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);

			GoodsDetail detail = inputForm.toGoodsDetail(chainId);
			GoodsDetailMgr.getInstance().addWithId(detail);
			
			tips.setSuccess(true);
		}
		return tips;
	}

	// 删除商品
	public OperateTips removeGoods(long chainId, GoodsRemoveForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.RemoveGoods.getDescript() + "失败");

		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
		if (ChainGoodsBeanHelper.getInstance().removeGoods(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);

			GoodsDetail detail = GoodsDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			GoodsDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 更新商品基本信息
	public OperateTips updateGoods(long chainId, GoodsUpdateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.UpdateGoods.getDescript() + "失败");
		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);

		if (checkNumberExists4Update(inputForm.getNumber(), chainGoods.getGoodsMap().values(), inputForm.getId())) {
			tips.setTips("商品编号已存在");
			return tips;
		}

		if (ChainGoodsBeanHelper.getInstance().updateGoods(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);
		}
		// 将详情信息也更新
		GoodsDetail detail = GoodsDetailMgr.getInstance().get(chainId, inputForm.getId());
		inputForm.updateGoodsDetail(detail);
		GoodsDetailMgr.getInstance().update(detail);
		
		ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
		BUserMessageMgr.getInstance().updateChainProduct(chainId, form);
		
		tips.setSuccess(true);
		return tips;
	}

	// 更新商品状态
	public OperateTips updateGoodsState(long chainId, GoodsUpdateStateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.UpdateGoodsState.getDescript() + "失败");

		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
		if (ChainGoodsBeanHelper.getInstance().updateGoodsStatus(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);
			// 将详情信息也更新
			GoodsDetail detail = GoodsDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setState(inputForm.getState());
			GoodsDetailMgr.getInstance().update(detail);
			
			if(inputForm.getState() == GoodsStateEnum.Close.ordinal()) {//下架操作
				updateStoreDataClose(chainId, detail.getApplyStoreIds(), inputForm.getId());
			}else {
				ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
				BUserMessageMgr.getInstance().openChainProduct(chainId, form);
			}
			
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private void updateStoreDataClose(long chainId, Set<Long> applyStoreIds, String id) {
		try {
			List<StoreChainUpdateStatusForm> updateStatusForms = new ArrayList<StoreChainUpdateStatusForm>();
			for (Long storeId : applyStoreIds) {
				StoreChainUpdateStatusForm form = StoreChainUpdateStatusForm.newInstance();
				form.setStatus(StoreChainStatus.Close.ordinal());
				form.setId(id);
				form.setItemType(StoreChainItemType.Goods.ordinal());
				form.setStoreId(storeId);
				updateStatusForms.add(form);
			}
			StoreChainMgr.getInstance().batchUpdateState(String.valueOf(chainId), updateStatusForms);
		} catch (Exception e) {
			MainLog.error(LogModule.ChainGoods, "GoodsHandle[updateStoreDataClose]", "", e);
		}
	}
	
	// 分配商品的适用店铺
	public OperateTips allotStore(long chainId, GoodsAllotForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.AllotStore.getDescript() + "失败");
		
		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
		inputForm.getApplyStoreIds().remove(null);
		if (ChainGoodsBeanHelper.getInstance().allotStore(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);
			// 将详情信息也更新
			GoodsDetail detail = GoodsDetailMgr.getInstance().get(chainId, inputForm.getId());
			detail.setApplyStoreIds(inputForm.getApplyStoreIds());
			GoodsDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean checkNumberExists4Add(String number, Collection<Goods> goods) {
		return checkNumberExists(number, goods, "");
	}

	private boolean checkNumberExists4Update(String number, Collection<Goods> goods, String id) {
		return checkNumberExists(number, goods, id);
	}

	private boolean checkNumberExists(String number, Collection<Goods> goods, String id) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		if (CollectionUtils.isNotEmpty(goods)) {
			for (Goods info : goods) {
				if (number.equals(info.getNumber()) && info.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(info.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
