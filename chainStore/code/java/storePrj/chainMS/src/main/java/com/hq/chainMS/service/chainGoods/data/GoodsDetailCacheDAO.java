package com.hq.chainMS.service.chainGoods.data;

import java.util.List;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailCacheDAO {

	public static GoodsDetailCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailCacheDAO.class);
	}

	final private String suffix = "chainGoodsDetail";

	public void saveList(GoodsDetailQueryForm queryForm, List<GoodsDetail> list) {
		GoodsDetailRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getChainId()), queryForm.getListId());
	}

	public List<GoodsDetail> getList(GoodsDetailQueryForm queryForm) {
		return GoodsDetailRedisDAO.getInstance().getList(getGroupName(queryForm.getChainId()), queryForm.getListId());
	}
	
	public void save(GoodsDetail target) {
		GoodsDetailRedisDAO.getInstance().saveOne(getGroupName(target.getChainId()), target.getId(), target);
	}
	
	public GoodsDetail get(long chainId, String id) {
		return GoodsDetailRedisDAO.getInstance().findByOne(getGroupName(chainId), id);
	}

	public void delete(GoodsDetail target) {
		GoodsDetailRedisDAO.getInstance().delete(target.getId());
		GoodsDetailRedisDAO.getInstance().deleteList(getGroupName(target.getChainId()));
	}

	private String getGroupName(long chainId) {
		return AppUtils.joinByUnderline(suffix, chainId);
	}
}
