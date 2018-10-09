package com.hq.storeMS.service.productCardDetail.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetailCacheDAO;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetailDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailDataHolder {
	
	public static ProductCardDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ProductCardDetailDataHolder.class);
	}
	
	public void addWithId(ProductCardDetail target) {
		ProductCardDetailDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
		ProductCardDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(ProductCardDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ProductCardDetailDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		ProductCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(ProductCardDetail target) {
		ProductCardDetailDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		ProductCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public ProductCardDetail get(long storeId, String id) {
		ProductCardDetail target = ProductCardDetailCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = ProductCardDetailDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				ProductCardDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<ProductCardDetail> findProductCardDetailList(ProductCardDetailQueryForm queryForm) {
		List<ProductCardDetail> list = ProductCardDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ProductCardDetailDAO.getInstance().findProductCardDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ProductCardDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
}
