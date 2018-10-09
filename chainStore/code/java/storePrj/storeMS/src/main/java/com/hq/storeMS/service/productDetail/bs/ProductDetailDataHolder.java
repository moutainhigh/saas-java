package com.hq.storeMS.service.productDetail.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeMS.service.productDetail.data.ProductDetail;
import com.hq.storeMS.service.productDetail.data.ProductDetailCacheDAO;
import com.hq.storeMS.service.productDetail.data.ProductDetailDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductDetailDataHolder {
	
	public static ProductDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ProductDetailDataHolder.class);
	}
	
	public void addWithId(ProductDetail target) {
		ProductDetailDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
		ProductDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(ProductDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ProductDetailDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		ProductDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(ProductDetail target) {
		ProductDetailDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		ProductDetailCacheDAO.getInstance().delete(target);
	}
	
	public ProductDetail get(long storeId, String id) {
		ProductDetail target = ProductDetailCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = ProductDetailDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				ProductDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<ProductDetail> findProductDetailList(ProductDetailQueryForm queryForm) {
		List<ProductDetail> list = ProductDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ProductDetailDAO.getInstance().findProductDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ProductDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}
