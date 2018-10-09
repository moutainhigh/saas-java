package com.hq.storeMS.service.storeProductInfo.bs.update;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.RemoveProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductTypeData;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoDataHolder;
import com.hq.storeMS.service.storeProductInfo.bs.StoreProductInfoMgr;
import com.hq.storeMS.service.storeProductInfo.data.ProductType;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfo;
import com.hq.storeMS.service.storeProductInfo.data.StoreProductInfoBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class StProductTypeMgr {
	public static StProductTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StProductTypeMgr.class);
	}
	
	//删除项目分类
	public OperateTips removeProductType(RemoveProductTypeData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.RemoveProductType.getDescript()+"失败");
		
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		if (StoreProductInfoBeanHelper.getInstance().removeProductType(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
			tips.setSuccess(true);
			addLogger(storeId, storeProductInfo.getProductTypeMap().get(inputData.getId()).getName(), StoreProductInfoUpdateType.RemoveProductType);
		}
		return tips;
	}
	
	//更新项目分类
	public OperateTips updateProductType(UpdateProductTypeData inputData){
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, StoreProductInfoUpdateType.UpdateProductType.getDescript()+"失败");
		
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		
		if(checkNameExists(inputData.getName(), storeProductInfo)){
			tips.setTips("项目分类已存在");
			return tips;
		}
		
		if (StoreProductInfoBeanHelper.getInstance().updateProductType(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
			tips.setSuccess(true);
			addLogger(storeId, inputData.getName(), StoreProductInfoUpdateType.UpdateProductType);
		}
		return tips;
	}
	
	public OperateTips addProductType(AddProductTypeData inputData) {
		long storeId = inputData.getStoreId();
		OperateTips tips = OperateTips.newInstance(false, "添加项目分类失败");
		StoreProductInfo storeProductInfo = StoreProductInfoMgr.getInstance().getByStoreId(storeId);
		if(checkNameExists(inputData.getName(), storeProductInfo)){
			tips.setTips("项目分类已存在");
			return tips;
		}
		if (StoreProductInfoBeanHelper.getInstance().addProductType(storeProductInfo, inputData)) {
			StoreProductInfoDataHolder.getInstance().update(storeProductInfo);
			tips.setSuccess(true);
			addLogger(storeId, inputData.getName(), StoreProductInfoUpdateType.AddProductType);
		}
		return tips;
	}
	
	private void addLogger(long storeId, String name, StoreProductInfoUpdateType updateType) {
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, name, OpLogTypeEnum.Product, updateType.getDescript()));
	}
	
	private boolean checkNameExists(String name, StoreProductInfo storeProductInfo){
		boolean flag = false;
		Map<String, ProductType> productTypeMap = storeProductInfo.takeProductTypeMapWithTypeNameKey();
		if(StringUtils.isNoneBlank(name) && MapUtils.isNotEmpty(productTypeMap)){
			ProductType productType = productTypeMap.get(name);
			if(productType != null && productType.getEntityState() != EntityState.Deleted.ordinal()){
				flag = true;
			}
		}
		return flag;
	}
}
