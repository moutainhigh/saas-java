package com.hq.chainMS.service.chainProduct.bs.updateHandle;

import java.util.HashMap;
import java.util.Map;

import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateForm;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateType;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductHandleHelper {

	public static ProductHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ProductHandleHelper.class);
	}

	private Map<ChainProductUpdateType, IProductHandle> handleMapper = new HashMap<ChainProductUpdateType, IProductHandle>();

	public ProductHandleHelper() {
		handleMapper.put(ChainProductUpdateType.AddProductInfo, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductHandle.getInstance().addProductInfo(chainId, formInfo.getAddProductInfoData());
			}
		});
		
		handleMapper.put(ChainProductUpdateType.UpdateProductInfo, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductHandle.getInstance().updateProductInfo(chainId, formInfo.getUpdateProductInfoData());
			}
		});
		handleMapper.put(ChainProductUpdateType.RemoveProductInfo, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductHandle.getInstance().removeProductInfo(chainId, formInfo.getRemoveProductInfoData());
			}
		});
		handleMapper.put(ChainProductUpdateType.UpdateProductState, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductHandle.getInstance().updateProductState(chainId, formInfo.getUpdateProductStateData());
			}
		});
		handleMapper.put(ChainProductUpdateType.BatchUpdateProductState, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return BatchProductHandle.getInstance().batchUpdateProductState(chainId, formInfo.getBatchUpdateProductStateData());
			}
		});
		
		handleMapper.put(ChainProductUpdateType.AddProductType, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductTypeHandle.getInstance().addProductType(chainId, formInfo.getAddProductTypeData());
			}
		});
		handleMapper.put(ChainProductUpdateType.UpdateProductType, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductTypeHandle.getInstance().updateProductType(chainId, formInfo.getUpdateProductTypeData());
			}
		});
		handleMapper.put(ChainProductUpdateType.RemoveProductType, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductTypeHandle.getInstance().removeProductType(chainId, formInfo.getRemoveProductTypeData());
			}
		});
		
		handleMapper.put(ChainProductUpdateType.AllotStore, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return ProductHandle.getInstance().allotStore(chainId, formInfo.getProductAllotForm());
			}
		});
		handleMapper.put(ChainProductUpdateType.BatchAllotStore, new IProductHandle(){
			@Override
			public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
				return BatchProductHandle.getInstance().batchAllotStore(chainId, formInfo.getProductBatchAllotForm());
			}
		});
	}

	public OperateTips update(long chainId, ChainProductUpdateForm formInfo) {
		ChainProductUpdateType updateType = formInfo.getChainProductUpdateType();
		IProductHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(chainId, formInfo);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
