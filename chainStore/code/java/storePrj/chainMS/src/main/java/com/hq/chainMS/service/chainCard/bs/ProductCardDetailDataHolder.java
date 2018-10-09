package com.hq.chainMS.service.chainCard.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.validate.ValidateInfoThreadLocal;
import com.hq.chainMS.service.chain.bs.ChainDataHolder;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.data.ProductCardDetail;
import com.hq.chainMS.service.chainCard.data.ProductCardDetailCacheDAO;
import com.hq.chainMS.service.chainCard.data.ProductCardDetailDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ProductCardDetailDataHolder {
	
	public static ProductCardDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ProductCardDetailDataHolder.class);
	}
	
	public void addWithId(ProductCardDetail target) {
		ProductCardDetailDAO.getInstance().addWithId(getBossId(target.getChainId()), target);
		ProductCardDetailCacheDAO.getInstance().delete(target);
	}

	public void updpate(ProductCardDetail target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		ProductCardDetailDAO.getInstance().updpate(getBossId(target.getChainId()), target);
		ProductCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public void delete(ProductCardDetail target) {
		ProductCardDetailDAO.getInstance().delete(getBossId(target.getChainId()), target.getId());
		ProductCardDetailCacheDAO.getInstance().delete(target);
	}
	
	public ProductCardDetail get(long chainId, String id) {
		ProductCardDetail target = ProductCardDetailCacheDAO.getInstance().get(chainId, id);
		if(target == null){
			target = ProductCardDetailDAO.getInstance().get(getBossId(chainId), id);
			if(target != null){
				ProductCardDetailCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<ProductCardDetail> findProductCardDetailList(ProductCardDetailQueryForm queryForm) {
		List<ProductCardDetail> list = ProductCardDetailCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = ProductCardDetailDAO.getInstance().findProductCardDetailList(getBossId(queryForm.getChainId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				ProductCardDetailCacheDAO.getInstance().saveList(queryForm, list);
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
