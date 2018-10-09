package com.hq.storeMS.service.storeProductInfo.bs.update;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.productDetail.bs.ProductDetailMgr;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoDataHolder;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfoBeanHelper;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class StProductMgr {
	public static StProductMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StProductMgr.class);
	}

	// 添加项目
	public OperateTips addProductInfo(AddProductInfoData inputData) {
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.AddProductInfo.getDescript()+"失败");
		
		if(StoreVipMgr.getInstance().isProductLimited(storeId)){
			tips.setTips("当前店铺项目数量已达上限");
			return tips;
		}
		
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		if (checkNumberExists4Add(inputData.getNumber(), storeProductInfo.getProductInfoMap().values())) {
			tips.setTips("项目编号已存在");
			return tips;
		}

		if (StoreProductInfoBeanHelper.getInstance().addProductInfo(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
			ProductDetail detail = inputData.toProductDetail(storeId);
			ProductDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreProductInfoUpdateType.AddProductInfo.getDescript()));
		}
		return tips;
	}
	
	// 删除项目信息
	public OperateTips removeProductInfo(RemoveProductInfoData inputData) {
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.RemoveProductInfo.getDescript() + "失败");
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		if (StoreProductInfoBeanHelper.getInstance().removeProductInfo(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
			ProductDetail detail = ProductDetailMgr.getInstance().get(storeId, inputData.getId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			ProductDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreProductInfoUpdateType.RemoveProductInfo.getDescript()));
		}
		return tips;
	}

	// 修改项目信息
	public OperateTips updateProductInfo(UpdateProductInfoData inputData) {
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.UpdateProductInfo.getDescript() + "失败");
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);

		if (checkNumberExists4Update(inputData.getNumber(), storeProductInfo.getProductInfoMap().values(), inputData.getId())) {
			tips.setTips("项目编号已存在");
			return tips;
		}

		if (StoreProductInfoBeanHelper.getInstance().updateProductInfo(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
		}
		//将详情信息也更新
		ProductDetail detail = ProductDetailMgr.getInstance().get(storeId, inputData.getId());
		inputData.updateProductDetail(detail);
		ProductDetailMgr.getInstance().update(detail);
		tips.setSuccess(true);
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreProductInfoUpdateType.UpdateProductInfo.getDescript()));
		return tips;
	}

	// 修改项目状态
	public OperateTips updateProductState(UpdateProductStateData inputData) {
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.UpdateProductState.getDescript() + "失败");

		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		if (StoreProductInfoBeanHelper.getInstance().updateProductState(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
			//将详情信息也更新
			ProductDetail detail = ProductDetailMgr.getInstance().get(storeId, inputData.getId());
			detail.setState(inputData.getState());
			ProductDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreProductInfoUpdateType.UpdateProductState.getDescript()));
		}
		return tips;
	}
	
	private boolean checkNumberExists4Add(String number, Collection<ProductInfo> productInfos) {
		return checkNumberExists(number, productInfos, ServerConstants.ZERO);
	}
	
	private boolean checkNumberExists4Update(String number, Collection<ProductInfo> productInfos, String id) {
		return checkNumberExists(number, productInfos, id);
	}
	
	private boolean checkNumberExists(String number, Collection<ProductInfo> productInfos, String id) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		if (CollectionUtils.isNotEmpty(productInfos)) {
			for (ProductInfo productInfo : productInfos) {
				if (number.equals(productInfo.getNumber()) && productInfo.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(productInfo.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
