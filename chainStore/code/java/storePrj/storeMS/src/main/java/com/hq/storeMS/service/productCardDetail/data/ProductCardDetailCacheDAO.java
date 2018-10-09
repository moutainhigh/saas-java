package com.hq.storeMS.service.productCardDetail.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailCacheDAO {

	public static ProductCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailCacheDAO.class);
	}

	final private String suffix = "storeProductCardDetail";

	public void saveList(ProductCardDetailQueryForm queryForm, List<ProductCardDetail> list) {
		ProductCardDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<ProductCardDetail> getList(ProductCardDetailQueryForm queryForm) {
		return ProductCardDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(ProductCardDetail target) {
		ProductCardDetailRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), target.getId(), target);
	}
	
	public ProductCardDetail get(long storeId, String id) {
		return ProductCardDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	public void delete(ProductCardDetail target) {
		ProductCardDetailRedisDAO.getInstance().delete(target.getId());
		ProductCardDetailRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
