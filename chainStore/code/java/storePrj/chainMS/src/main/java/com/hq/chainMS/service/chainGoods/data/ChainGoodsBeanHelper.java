package com.hq.chainMS.service.chainGoods.data;

import java.util.Map;

import com.hq.chainMS.common.util.AppUtils;
import com.hq.chainMS.service.chainGoods.apiData.GoodsAddForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsAllotForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsRemoveForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsTypeAddForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsUpdateForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsUpdateStateForm;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainGoodsBeanHelper {

	public static ChainGoodsBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsBeanHelper.class);
	}

	/*************************** 商品基本操作 ***************************/
	public boolean addGoods(ChainGoods chainGoods, GoodsAddForm addForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Goods data = addForm.toGoods(chainGoods.getChainId());
		long index = addForm.getIndex();
		Map<String, Goods> goodsMap = chainGoods.getGoodsMap();
		if (!goodsMap.containsKey(data.getId()) && chainGoods.getGoodsIdIndex() + 1 == index) {
			goodsMap.put(data.getId(), data);
			chainGoods.setGoodsIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeGoods(ChainGoods chainGoods, GoodsRemoveForm removeForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = chainGoods.getGoodsMap();
		if (goodsMap.containsKey(removeForm.getId())) {
			Goods goods = goodsMap.get(removeForm.getId());
			goods.setEntityState(EntityState.Deleted.ordinal());
			success = true;
		}
		return success;
	}
	
	public boolean updateGoods(ChainGoods chainGoods, GoodsUpdateForm updateForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = chainGoods.getGoodsMap();
		if (goodsMap.containsKey(updateForm.getId())) {
			Goods oldTarget = goodsMap.get(updateForm.getId());
			Goods newTarget = AppUtils.copyBeanBySerialize(oldTarget, Goods.class);
			updateForm.updateGoods(newTarget);
			if(!newTarget.equals(oldTarget)) {
				goodsMap.put(newTarget.getId(), newTarget);
				success = true;
			}
		}
		return success;
	}

	public boolean updateGoodsStatus(ChainGoods chainGoods, GoodsUpdateStateForm updateForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = chainGoods.getGoodsMap();
		if (goodsMap.containsKey(updateForm.getId())) {
			Goods data = goodsMap.get(updateForm.getId());
			data.setState(updateForm.getState());
			success = true;
		}
		return success;
	}
	
	public boolean allotStore(ChainGoods chainGoods, GoodsAllotForm updateForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = chainGoods.getGoodsMap();
		if (goodsMap.containsKey(updateForm.getId())) {
			Goods data = goodsMap.get(updateForm.getId());
			data.setApplyStoreIds(updateForm.getApplyStoreIds());
			success = true;
		}
		return success;
	}

	/*************************** 商品分类基本操作 ***************************/
	public boolean addGoodsType(ChainGoods chainGoods, GoodsTypeAddForm addForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		GoodsType data = addForm.toGoodsType(chainGoods.getChainId());
		long index = addForm.getIndex();
		Map<String, GoodsType> goodsTypeMap = chainGoods.getGoodsTypeMap();
		if (!goodsTypeMap.containsKey(data.getId()) && chainGoods.getGoodsTypeIdIndex() + 1 == index) {
			goodsTypeMap.put(data.getId(), data);
			chainGoods.setGoodsTypeIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeGoodsType(ChainGoods chainGoods, GoodsTypeRemoveForm removeForm) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, GoodsType> goodsTypeMap = chainGoods.getGoodsTypeMap();
		if (goodsTypeMap.containsKey(removeForm.getId())) {
			GoodsType goodsType = goodsTypeMap.get(removeForm.getId());
			goodsType.setEntityState(EntityState.Deleted.ordinal());
			goodsType.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

	public boolean updateGoodsType(ChainGoods chainGoods, GoodsTypeUpdateForm data) {
		if (chainGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, GoodsType> goodsTypeMap = chainGoods.getGoodsTypeMap();
		if (goodsTypeMap.containsKey(data.getId())) {
			GoodsType goodsType = goodsTypeMap.get(data.getId());
			data.updateGoodsType(goodsType);
			goodsType.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

}
