package com.hq.chainMS.service.chainProduct.bs.updateHandle;

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
import com.hq.chainMS.service.chainProduct.apiData.AddProductInfoData;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateType;
import com.hq.chainMS.service.chainProduct.apiData.ProductAllotForm;
import com.hq.chainMS.service.chainProduct.apiData.RemoveProductInfoData;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductInfoData;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductStateData;
import com.hq.chainMS.service.chainProduct.bs.ChainProductDataHolder;
import com.hq.chainMS.service.chainProduct.bs.ChainProductMgr;
import com.hq.chainMS.service.chainProduct.bs.ProductDetailMgr;
import com.hq.chainMS.service.chainProduct.data.ChainProduct;
import com.hq.chainMS.service.chainProduct.data.ChainProductBeanHelper;
import com.hq.chainMS.service.chainProduct.data.Product;
import com.hq.chainMS.service.chainProduct.data.ProductDetail;
import com.hq.chainMS.service.chainProduct.data.ProductStateEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.storeChain.bs.StoreChainMgr;
import com.hq.storeClient.service.storeChain.apiData.StoreChainUpdateStatusForm;
import com.hq.storeClient.service.storeChain.data.StoreChainItemType;
import com.hq.storeClient.service.storeChain.data.StoreChainStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductHandle {
	public static ProductHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ProductHandle.class);
	}

	// 添加项目
	public OperateTips addProductInfo(long chainId, AddProductInfoData addProductInfoData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.AddProductInfo.getDescript()+"失败");
		
		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if (checkNumberExists4Add(addProductInfoData.getNumber(), chainProduct.getProductInfoMap().values())) {
			tips.setTips("项目编号已存在");
			return tips;
		}

		if (ChainProductBeanHelper.getInstance().addProductInfo(chainProduct, addProductInfoData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			
			ProductDetail detail = addProductInfoData.toProductDetail(chainId);
			ProductDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	// 删除项目信息
	public OperateTips removeProductInfo(long chainId, RemoveProductInfoData removeProductInfoData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.RemoveProductInfo.getDescript() + "失败");

		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if (ChainProductBeanHelper.getInstance().removeProductInfo(chainProduct, removeProductInfoData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			
			ProductDetail detail = ProductDetailMgr.getInstance().get(chainId, removeProductInfoData.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			ProductDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 修改项目信息
	public OperateTips updateProductInfo(long chainId, UpdateProductInfoData inputData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.UpdateProductInfo.getDescript() + "失败");

		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if (checkNumberExists4Update(inputData.getNumber(), chainProduct.getProductInfoMap().values(), inputData.getId())) {
			tips.setTips("项目编号已存在");
			return tips;
		}

		if (ChainProductBeanHelper.getInstance().updateProductInfo(chainProduct, inputData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
		}
		//将详情信息也更新
		ProductDetail detail = ProductDetailMgr.getInstance().get(chainId, inputData.getId());
		inputData.updateProductDetail(detail);
		ProductDetailMgr.getInstance().update(detail);
		
		ProductMessageForm form = ProductMessageForm.newInstance(detail.getId(), detail.getName(), detail.getApplyStoreIds());
		BUserMessageMgr.getInstance().updateChainProduct(chainId, form);
		tips.setSuccess(true);
		return tips;
	}

	// 修改项目状态
	public OperateTips updateProductState(long chainId, UpdateProductStateData inputData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.UpdateProductState.getDescript() + "失败");

		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if (ChainProductBeanHelper.getInstance().updateProductState(chainProduct, inputData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			//将详情信息也更新
			ProductDetail detail = ProductDetailMgr.getInstance().get(chainId, inputData.getId());
			detail.setState(inputData.getState());
			ProductDetailMgr.getInstance().update(detail);
			
			if(inputData.getState() == ProductStateEnum.Close.ordinal()) {//下架操作
				updateStoreDataClose(chainId, detail.getApplyStoreIds(), inputData.getId());
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
				form.setItemType(StoreChainItemType.Product.ordinal());
				form.setStoreId(storeId);
				updateStatusForms.add(form);
			}
			StoreChainMgr.getInstance().batchUpdateState(String.valueOf(chainId), updateStatusForms);
		} catch (Exception e) {
			MainLog.error(LogModule.ChainProduct, "ProductHandle[updateStoreDataClose]", "", e);
		}
	}
	
	// 分配
	public OperateTips allotStore(long chainId, ProductAllotForm inputData) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.AllotStore.getDescript() + "失败");
		
		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		inputData.getApplyStoreIds().remove(null);
		if (ChainProductBeanHelper.getInstance().allotStore(chainProduct, inputData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			//将详情信息也更新
			ProductDetail detail = ProductDetailMgr.getInstance().get(chainId, inputData.getId());
			detail.setApplyStoreIds(inputData.getApplyStoreIds());
			ProductDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean checkNumberExists4Add(String number, Collection<Product> productInfos) {
		return checkNumberExists(number, productInfos, "");
	}
	
	private boolean checkNumberExists4Update(String number, Collection<Product> productInfos, String id) {
		return checkNumberExists(number, productInfos, id);
	}
	
	private boolean checkNumberExists(String number, Collection<Product> productInfos, String id) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		if (CollectionUtils.isNotEmpty(productInfos)) {
			for (Product productInfo : productInfos) {
				if (number.equals(productInfo.getNumber()) && productInfo.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(productInfo.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
