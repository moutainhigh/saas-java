package com.hq.chainMS.service.chainProduct.data;

import java.util.List;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainProduct.apiData.ProductDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailCacheDAO {

	public static ProductDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailCacheDAO.class);
	}

	final private String suffix = "chainProductDetail";

	public void saveList(ProductDetailQueryForm queryForm, List<ProductDetail> list) {
		ProductDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getChainId()), queryForm.getListId());
	}

	public List<ProductDetail> getList(ProductDetailQueryForm queryForm) {
		return ProductDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getChainId()), queryForm.getListId());
	}
	
	public void save(ProductDetail target) {
		ProductDetailRedisDAO.getInstance().saveOne(getGroupName(target.getChainId()), target.getId(), target);
	}
	
	public ProductDetail get(long chainId, String id) {
		return ProductDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	public void delete(ProductDetail target) {
		ProductDetailRedisDAO.getInstance().delete(target.getId());
		ProductDetailRedisDAO.getInstance().deleteList(getGroupName(target.getChainId()));
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}
