package com.hq.chainMS.service.chainGoods.bs.updateHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateType;
import com.hq.chainMS.service.chainGoods.apiData.GoodsAllotForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsBatchAllotForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsBatchUpdateStateForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainMS.service.chainGoods.bs.ChainGoodsMgr;
import com.hq.chainMS.service.chainGoods.data.Goods;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchGoodsHandle {
	public static BatchGoodsHandle getInstance() {
		return HotSwap.getInstance().getSingleton(BatchGoodsHandle.class);
	}

	// 批量更新商品状态
	public OperateTips batchUpdateGoodsState(long chainId, GoodsBatchUpdateStateForm batchUpdateStateForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.BatchUpdateGoodsState.getDescript() + "失败");

		Map<String, Goods> goodsMap = ChainGoodsMgr.getInstance().get(chainId).getGoodsMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<GoodsUpdateStateForm> goodsUpdateStateFormList = batchUpdateStateForm.toGoodsUpdateStateFormList();
		for (GoodsUpdateStateForm goodsUpdateStateForm : goodsUpdateStateFormList) {
			OperateTips optips = GoodsHandle.getInstance().updateGoodsState(chainId, goodsUpdateStateForm);
			if (!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(goodsMap.get(goodsUpdateStateForm.getId()).getName());
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下商品更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	// 批量分配商品到店铺
	public OperateTips batchAllotStore(long chainId, GoodsBatchAllotForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.BatchAllotStore.getDescript() + "失败");
		
		Map<String, Goods> goodsMap = ChainGoodsMgr.getInstance().get(chainId).getGoodsMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<GoodsAllotForm> list = inputForm.getGoodsAllotForms();
		for (GoodsAllotForm form : list) {
			//批量分配  将现有的和原来的storeIds合并
			Goods data = goodsMap.get(form.getId());
			if(data==null) {
				continue;
			}
			form.getApplyStoreIds().addAll(data.getApplyStoreIds());
			OperateTips optips = GoodsHandle.getInstance().allotStore(chainId, form);
			if (!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(data.getName());
			}
		}
		tips.setSuccess(true);
		if (flag) {
			tips.setTips("以下商品分配到店失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
}
