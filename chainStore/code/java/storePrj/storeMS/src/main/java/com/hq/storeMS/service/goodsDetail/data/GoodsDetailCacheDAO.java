package com.hq.storeMS.service.goodsDetail.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailCacheDAO {

	public static GoodsDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailCacheDAO.class);
	}

	final private String suffix = "storeGoodsDetail";

	public void saveList(GoodsDetailQueryForm queryForm, List<GoodsDetail> list) {
		GoodsDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<GoodsDetail> getList(GoodsDetailQueryForm queryForm) {
		return GoodsDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(GoodsDetail target) {
		GoodsDetailRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), target.getId(), target);
	}
	
	public GoodsDetail get(long storeId, String id) {
		return GoodsDetailRedisDAO.getInstance().findByOne(getGroupName(storeId), id);
	}

	public void delete(GoodsDetail target) {
		GoodsDetailRedisDAO.getInstance().delete(target.getId());
		GoodsDetailRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
