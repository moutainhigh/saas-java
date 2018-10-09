package com.hq.chainStore.service.storeGoods.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreGoodsSynDataHolder extends AbsDataSynDataHolder<StoreGoods> {

	public static StoreGoodsSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreGoods;

	protected Class<StoreGoods> getClazz() {
		return StoreGoods.class;
	}

	protected RestDao<StoreGoods> getDao() {
		return StoreGoodsDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	public StoreGoods getStoreGoods(Long ownerId, Long storeId){
		return super.getData(String.valueOf(ownerId), String.valueOf(storeId));
	}
	
	public List<Goods> getGoodsList(String ownerId, Long storeId){
		StoreGoods storeGoods = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<Goods>(storeGoods.getGoodsMap().values());
	}
	
	public Goods getGoods(String ownerId, Long storeId, String goodsId){
		StoreGoods storeGoods = super.getData(ownerId, String.valueOf(storeId));
		return storeGoods.getGoodsMap().get(goodsId);
	}
	
	public List<GoodsType> getGoodsTypeList(String ownerId, Long storeId){
		StoreGoods storeGoods = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<GoodsType>(storeGoods.getGoodsTypeMap().values());
	}
	
	public GoodsType getGoodsType(String ownerId, Long storeId, String goodsTypeId){
		StoreGoods storeGoods = super.getData(ownerId, String.valueOf(storeId));
		return storeGoods.getGoodsTypeMap().get(goodsTypeId);
	}
	
	/**
	 * 获取店铺商品的有序列表[置顶]
	 * @param ownerId 当前登录者ID
	 * @param storeId 当前店铺ID
	 * @return
	 */
	public List<Goods> getGoodsBySort(String ownerId, Long storeId){
		List<Goods> list = new ArrayList<Goods>();
		StoreGoods storeGoods = super.getData(ownerId, String.valueOf(storeId));
		Map<String, Goods> goodsMap = storeGoods.getGoodsMap();
		List<String> goodsIds = storeGoods.getTopGoodsIdList();
		
		//置顶的商品
		for (String id : goodsIds) {
			list.add(goodsMap.get(id));
		}
		
		List<Goods> goodsList = new ArrayList<Goods>(goodsMap.values());
		for (Goods info : goodsList) {
			//不是置顶的项目
			if(!goodsIds.contains(info.getId())){
				list.add(info);
			}
		}
		return list;
	}
}
