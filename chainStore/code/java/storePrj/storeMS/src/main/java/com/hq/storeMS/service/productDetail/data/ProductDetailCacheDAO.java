package com.hq.storeMS.service.productDetail.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.productDetail.apiData.ProductDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailCacheDAO {

	public static ProductDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailCacheDAO.class);
	}

	final private String suffix = "storeProductDetail";

	public void saveList(ProductDetailQueryForm queryForm, List<ProductDetail> list) {
		ProductDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<ProductDetail> getList(ProductDetailQueryForm queryForm) {
		return ProductDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(ProductDetail target) {
		ProductDetailRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), target.getId(), target);
	}
	
	public ProductDetail get(long storeId, String id) {
		return ProductDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	public void delete(ProductDetail target) {
		ProductDetailRedisDAO.getInstance().delete(target.getId());
		ProductDetailRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
