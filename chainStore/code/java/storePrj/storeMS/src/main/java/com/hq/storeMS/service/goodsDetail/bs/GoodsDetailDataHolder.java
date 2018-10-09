package com.hq.storeMS.service.goodsDetail.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetailCacheDAO;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetailDAO;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailDataHolder {
	
	public static GoodsDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(GoodsDetailDataHolder.class);
	}
	
	public void addWithId(GoodsDetail target) {
		GoodsDetailDAO.getInstance().addWithId(getBossId(target.getStoreId()), target);
		GoodsDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(GoodsDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		GoodsDetailDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		GoodsDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(GoodsDetail target) {
		GoodsDetailDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		GoodsDetailCacheDAO.getInstance().delete(target);
	}
	
	public GoodsDetail get(long storeId, String id) {
		GoodsDetail target = GoodsDetailCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = GoodsDetailDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				GoodsDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<GoodsDetail> findGoodsDetailList(GoodsDetailQueryForm queryForm) {
		List<GoodsDetail> list = GoodsDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = GoodsDetailDAO.getInstance().findGoodsDetailList(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				GoodsDetailCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
    	return BossDataHolder.getInstance().getBossId(storeId);
    }
}
