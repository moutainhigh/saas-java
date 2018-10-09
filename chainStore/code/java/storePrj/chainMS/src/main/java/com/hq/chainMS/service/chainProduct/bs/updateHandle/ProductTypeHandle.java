package com.hq.chainMS.service.chainProduct.bs.updateHandle;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainProduct.apiData.AddProductTypeData;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateType;
import com.hq.chainMS.service.chainProduct.apiData.RemoveProductTypeData;
import com.hq.chainMS.service.chainProduct.apiData.UpdateProductTypeData;
import com.hq.chainMS.service.chainProduct.bs.ChainProductDataHolder;
import com.hq.chainMS.service.chainProduct.bs.ChainProductMgr;
import com.hq.chainMS.service.chainProduct.data.ChainProduct;
import com.hq.chainMS.service.chainProduct.data.ChainProductBeanHelper;
import com.hq.chainMS.service.chainProduct.data.ProductType;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductTypeHandle {
	public static ProductTypeHandle getInstance() {
		return HotSwap.getInstance().getSingleton(ProductTypeHandle.class);
	}
	
	//添加项目分类
	public OperateTips addProductType(long chainId, AddProductTypeData inputData){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, "添加项目分类失败");
		
		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if(checkNameExists4Add(inputData.getName(), chainProduct.getProductTypeMap().values())){
			tips.setTips("项目分类已存在");
			return tips;
		}
		if (ChainProductBeanHelper.getInstance().addProductType(chainProduct, inputData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//删除项目分类
	public OperateTips removeProductType(long chainId, RemoveProductTypeData inputData){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.RemoveProductType.getDescript()+"失败");
		
		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if (ChainProductBeanHelper.getInstance().removeProductType(chainProduct, inputData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	//更新项目分类
	public OperateTips updateProductType(long chainId, UpdateProductTypeData inputData){
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainProductUpdateType.UpdateProductType.getDescript()+"失败");
		
		ChainProduct chainProduct = ChainProductMgr.getInstance().get(chainId);
		if(checkNameExists4Update(inputData.getName(), chainProduct.getProductTypeMap().values(), inputData.getId())){
			tips.setTips("项目分类已存在");
			return tips;
		}
		
		if (ChainProductBeanHelper.getInstance().updateProductType(chainProduct, inputData)) {
			ChainProductDataHolder.getInstance().update(chainProduct);
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean checkNameExists4Add(String name, Collection<ProductType> types) {
		return checkNameExists(name, types, "");
	}

	private boolean checkNameExists4Update(String name, Collection<ProductType> types, String id) {
		return checkNameExists(name, types, id);
	}

	private boolean checkNameExists(String name, Collection<ProductType> types, String id) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		for (ProductType type : types) {
			if (name.equals(type.getName()) && type.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(type.getId())) {
				return true;
			}
		}
		return false;
	}
}
