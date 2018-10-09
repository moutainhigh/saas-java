package com.hq.chainMS.service.chainProduct.bs.updateHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainProduct.apiData.BatchUpdateProductStateData;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateType;
import com.hq.chainMS.service.chainProduct.apiData.ProductAllotForm;
import com.hq.chainMS.service.chainProduct.apiData.ProductBatchAllotForm;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductStateData;
import com.hq.chainMS.service.chainProduct.bs.ChainProductMgr;
import com.hq.chainMS.service.chainProduct.data.Product;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class BatchProductHandle {
	public static BatchProductHandle getInstance() {
		return HotSwap.getInstance().getSingleton(BatchProductHandle.class);
	}

	// 批量修改项目状态
	public OperateTips batchUpdateProductState(long chainId, BatchUpdateProductStateData inputData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.BatchUpdateProductState.getDescript() + "失败");

		Map<String, Product> dataMap = ChainProductMgr.getInstance().get(chainId).getProductInfoMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<UpdateProductStateData> list = inputData.toUpdateProductStateDataList();
		for (UpdateProductStateData form : list) {
			OperateTips optips = ProductHandle.getInstance().updateProductState(chainId, form);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(dataMap.get(form.getId()).getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下项目更新失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
	
	// 批量分配
	public OperateTips batchAllotStore(long chainId, ProductBatchAllotForm inputData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.BatchAllotStore.getDescript() + "失败");
		
		Map<String, Product> dataMap = ChainProductMgr.getInstance().get(chainId).getProductInfoMap();
		List<String> tipsInfo = new ArrayList<String>();
		boolean flag = false;
		List<ProductAllotForm> list = inputData.getProductAllotForms();
		for (ProductAllotForm form : list) {
			//批量分配  将现有的和原来的storeIds合并
			Product data = dataMap.get(form.getId());
			if(data==null) {
				continue;
			}
			form.getApplyStoreIds().addAll(data.getApplyStoreIds());
			OperateTips optips = ProductHandle.getInstance().allotStore(chainId, form);
			if(!optips.isSuccess()) {
				flag = true;
				tipsInfo.add(data.getName());
			}
		}
		tips.setSuccess(true);
		if(flag) {
			tips.setTips("以下项目分配失败：[" + StringUtils.join(tipsInfo, ",") + "]");
		}
		return tips;
	}
}
