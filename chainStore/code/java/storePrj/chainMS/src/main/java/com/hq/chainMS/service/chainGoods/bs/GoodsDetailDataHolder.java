package com.hq.chainMS.service.chainGoods.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.validate.ValidateInfoThreadLocal;
import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.hq.chainMS.service.chainGoods.data.GoodsDetail;
import com.hq.chainMS.service.chainGoods.data.GoodsDetailCacheDAO;
import com.hq.chainMS.service.chainGoods.data.GoodsDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailDataHolder {
	
	public static GoodsDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(GoodsDetailDataHolder.class);
	}
	
	public void addWithId(GoodsDetail target) {
		GoodsDetailDAO.getInstance().addWithId(getBossId(target.getChainId()), target);
		GoodsDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(GoodsDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		GoodsDetailDAO.getInstance().updpate(getBossId(target.getChainId()), target);
		GoodsDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(GoodsDetail target) {
		GoodsDetailDAO.getInstance().delete(getBossId(target.getChainId()), target.getId());
		GoodsDetailCacheDAO.getInstance().delete(target);
	}
	
	public GoodsDetail get(long chainId, String id) {
		GoodsDetail target = GoodsDetailCacheDAO.getInstance().get(chainId, id);
		if(target == null){
			target = GoodsDetailDAO.getInstance().get(getBossId(chainId), id);
			if(target != null){
				GoodsDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<GoodsDetail> findGoodsDetailList(GoodsDetailQueryForm queryForm) {
		List<GoodsDetail> list = GoodsDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = GoodsDetailDAO.getInstance().findGoodsDetailList(getBossId(queryForm.getChainId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				GoodsDetailCacheDAO.getInstance().saveList(queryForm, list);
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
