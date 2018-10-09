package com.hq.chainMS.service.chainProduct.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.validate.ValidateInfoThreadLocal;
import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainMS.service.chainProduct.data.ProductDetail;
import com.hq.chainMS.service.chainProduct.data.ProductDetailCacheDAO;
import com.hq.chainMS.service.chainProduct.data.ProductDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailDataHolder {
	
	public static ProductDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ProductDetailDataHolder.class);
	}
	
	public void addWithId(ProductDetail target) {
		ProductDetailDAO.getInstance().addWithId(getBossId(target.getChainId()), target);
		ProductDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(ProductDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ProductDetailDAO.getInstance().updpate(getBossId(target.getChainId()), target);
		ProductDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(ProductDetail target) {
		ProductDetailDAO.getInstance().delete(getBossId(target.getChainId()), target.getId());
		ProductDetailCacheDAO.getInstance().delete(target);
	}
	
	public ProductDetail get(long chainId, String id) {
		ProductDetail target = ProductDetailCacheDAO.getInstance().get(chainId, id);
		if(target == null){
			target = ProductDetailDAO.getInstance().get(getBossId(chainId), id);
			if(target != null){
				ProductDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<ProductDetail> findProductDetailList(ProductDetailQueryForm queryForm) {
		List<ProductDetail> list = ProductDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ProductDetailDAO.getInstance().findProductDetailList(getBossId(queryForm.getChainId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ProductDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long chainId) {
		long bossId = ValidateInfoThreadLocal.getInstance().getBossId();
		if(bossId == 0) {
			Chain chain = ChainDataHolder.getInstance().get(chainId);
			if(chain!=null) {
				bossId = chain.getBossId();
			}
		}
		return bossId;
	}
}
