package com.hq.storeMS.service.storeProductInfo.bs.update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.excel.data.ExcelProduct;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductToTopData;
import com.hq.storeMS.service.storeProductInfo.apiData.AddProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.BatchUpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.apiData.CancelProductFromTopData;
import com.hq.storeMS.service.storeProductInfo.apiData.RemoveProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.RemoveProductTypeData;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateForm;
import com.hq.storeMS.service.storeProductInfo.apiData.StoreProductInfoUpdateType;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductInfoData;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductStateData;
import com.hq.storeMS.service.storeProductInfo.apiData.UpdateProductTypeData;
import com.hq.storeMS.service.storeProductInfo.data.ProductInfoTmp;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductHandlerHelper {

	public static ProductHandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ProductHandlerHelper.class);
	}

	private Map<StoreProductInfoUpdateType, IProductHandler> handleMapper = new HashMap<StoreProductInfoUpdateType, IProductHandler>();

	public ProductHandlerHelper() {
		handleMapper.put(StoreProductInfoUpdateType.AddProductInfo, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				AddProductInfoData inputData = formInfo.getAddProductInfoData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductMgr.getInstance().addProductInfo(inputData);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.AddListFromExcel, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo){
				List<ExcelProduct> inputData = formInfo.getAddListFromExcel();
				long storeId = formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchStProductMgr.getInstance().addListFormExcel(inputData, storeId);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.AddListFromStore, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo){
				List<ProductInfoTmp> inputData = formInfo.getAddListFromStore();
				long storeId = formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.STORE_CONFIG_ADMIN);
				return BatchStProductMgr.getInstance().addListFormStore(inputData, storeId);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.UpdateProductInfo, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				UpdateProductInfoData inputData = formInfo.getUpdateProductInfoData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductMgr.getInstance().updateProductInfo(inputData);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.RemoveProductInfo, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				RemoveProductInfoData inputData = formInfo.getRemoveProductInfoData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductMgr.getInstance().removeProductInfo(inputData);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.UpdateProductState, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				UpdateProductStateData inputData = formInfo.getUpdateProductStateData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductMgr.getInstance().updateProductState(inputData);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.BatchUpdateProductState, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				BatchUpdateProductStateData inputData = formInfo.getBatchUpdateProductStateData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return BatchStProductMgr.getInstance().batchUpdateProductState(inputData);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.AddProductType, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				AddProductTypeData inputData = formInfo.getAddProductTypeData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductTypeMgr.getInstance().addProductType(inputData);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.UpdateProductType, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				UpdateProductTypeData inputData = formInfo.getUpdateProductTypeData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductTypeMgr.getInstance().updateProductType(inputData);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.RemoveProductType, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				RemoveProductTypeData inputData = formInfo.getRemoveProductTypeData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductTypeMgr.getInstance().removeProductType(inputData);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.AddProductToTop, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				AddProductToTopData inputData = formInfo.getAddProductToTopData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductTopMgr.getInstance().addToTop(inputData);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.CancelProductFromTop, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				CancelProductFromTopData inputData = formInfo.getCancelProductFromTopData();
				long storeId = formInfo.getStoreId() == 0 ? inputData.getStoreId() : formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.PRODUCT_ADMIN);
				return StProductTopMgr.getInstance().cancelTop(inputData);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.AddPackedProductInfo, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.UpdatePackedProductInfo, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.UpdatePackedProductState, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.RemovePackedProductInfo, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.UpdateProductBeautician, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.UpdateProductMaterial, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				return OperateTips.newInstance(true);
			}
		});
		
		handleMapper.put(StoreProductInfoUpdateType.BatchCancelChainProduct, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				long storeId = formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChProductMgr.getInstance().batchCancelChainProduct(storeId, formInfo.getProductBatchCancelForm());
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.BatchPullProductFromChain, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				long storeId = formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChProductMgr.getInstance().batchAddChainProduct(storeId, formInfo.getProductBatchPullForm());
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.CancelChainProduct, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				long storeId = formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChProductMgr.getInstance().cancelChainProduct(storeId, formInfo.getProductCancelForm());
			}
		});
		handleMapper.put(StoreProductInfoUpdateType.PullProductFromChain, new IProductHandler(){
			@Override
			public OperateTips update(StoreProductInfoUpdateForm formInfo) {
				long storeId = formInfo.getStoreId();
				BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.SYNDATA_ADMIN);
				return ChProductMgr.getInstance().addChainProduct(storeId, formInfo.getProductPullForm());
			}
		});
	}

	public OperateTips update(StoreProductInfoUpdateForm formInfo) {
		StoreProductInfoUpdateType updateType = StoreProductInfoUpdateType.valueOf(formInfo.getUpdateType());
		IProductHandler handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
