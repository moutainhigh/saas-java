package com.hq.chainStore.service.beauticianProduct.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.storeProductInfo.data.ProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfo;
import com.hq.chainStore.service.storeProductInfo.data.StoreProductInfoSynDataHolder;
import com.hq.common.StringUtils4Client;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class BeauticianProductSynDataHolder extends AbsDataSynDataHolder<BeauticianProduct> {

	public static BeauticianProductSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BeauticianProductSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.BeauticianProduct;

	protected Class<BeauticianProduct> getClazz() {
		return BeauticianProduct.class;
	}
	
	protected RestDao<BeauticianProduct> getDao() {
		return BeauticianProductDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	/**
	 * 获取医美师有序的项目列表
	 * @param ownerId 当前登录者ID
	 * @param storeId 当前店铺ID
	 * @param beauticianId 医美师ID
	 * @return
	 */
	public List<ProductInfo> getBeauticianSortProduct(String ownerId, Long storeId, Long beauticianId){
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		String beauticianProductId = StringUtils4Client.format("{}_{}", storeId, beauticianId);
		BeauticianProduct beauticianProduct = super.getData(ownerId, beauticianProductId);
		List<Long> productIds = beauticianProduct.getProductIds();
		
		StoreProductInfo storeProductInfo = StoreProductInfoSynDataHolder.getInstance().getData(ownerId, String.valueOf(storeId));
		Map<String, ProductInfo> productMap = storeProductInfo.getProductInfoMap();
		//置顶的项目
		for (Long id : productIds) {
			list.add(productMap.get(String.valueOf(id)));
		}
		
		List<ProductInfo> productInfos = new ArrayList<ProductInfo>(productMap.values());
		for (ProductInfo info : productInfos) {
			//属于当前医美师  而且不是置顶的项目
//			if(info.getBeauticianSet().contains(beauticianId) && !productIds.contains(info.getId())){
				list.add(info);
			}
//		}
		return list;
	}
}
