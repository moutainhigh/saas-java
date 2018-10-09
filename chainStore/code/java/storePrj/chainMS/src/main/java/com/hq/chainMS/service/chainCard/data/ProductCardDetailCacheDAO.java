package com.hq.chainMS.service.chainCard.data;

import java.util.List;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailCacheDAO {

	public static ProductCardDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailCacheDAO.class);
	}

	final private String suffix = "chainProductCardDetail";

	public void saveList(ProductCardDetailQueryForm queryForm, List<ProductCardDetail> list) {
		ProductCardDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getChainId()), queryForm.getListId());
	}

	public List<ProductCardDetail> getList(ProductCardDetailQueryForm queryForm) {
		return ProductCardDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getChainId()), queryForm.getListId());
	}
	
	public void save(ProductCardDetail target) {
		ProductCardDetailRedisDAO.getInstance().saveOne(getGroupName(target.getChainId()), target.getId(), target);
	}
	
	public ProductCardDetail get(long chainId, String id) {
		return ProductCardDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	public void delete(ProductCardDetail target) {
		ProductCardDetailRedisDAO.getInstance().delete(target.getId());
		ProductCardDetailRedisDAO.getInstance().deleteList(getGroupName(target.getChainId()));
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}
