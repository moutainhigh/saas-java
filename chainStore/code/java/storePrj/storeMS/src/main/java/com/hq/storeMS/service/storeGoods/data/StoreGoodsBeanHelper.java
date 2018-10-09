package com.hq.storeMS.service.storeGoods.data;

import java.util.Map;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storeGoods.apiData.GoodsAddForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsRemoveForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeRemoveForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeUpdateForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsUpdateForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreGoodsBeanHelper {

	public static StoreGoodsBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(StoreGoodsBeanHelper.class);
	}

	/*************************** 商品基本操作 ***************************/
	public boolean addGoods(StoreGoods storeGoods, GoodsAddForm addForm) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		Goods data = addForm.toGoods();
		long index = addForm.getIndex();
		Map<String, Goods> goodsMap = storeGoods.getGoodsMap();
		if (!goodsMap.containsKey(data.getId()) && storeGoods.getGoodsIdIndex() + 1 == index) {
			goodsMap.put(data.getId(), data);
			storeGoods.setGoodsIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeGoods(StoreGoods storeGoods, GoodsRemoveForm removeForm) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = storeGoods.getGoodsMap();
		if (goodsMap.containsKey(removeForm.getGoodsId())) {
			Goods goods = goodsMap.get(removeForm.getGoodsId());
			goods.setEntityState(EntityState.Deleted.ordinal());
			success = true;
		}
		return success;
	}
	
	public boolean updateGoods(StoreGoods storeGoods, GoodsUpdateForm updateForm) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = storeGoods.getGoodsMap();
		if (goodsMap.containsKey(updateForm.getId())) {
			Goods oldData = goodsMap.get(updateForm.getId());
			Goods newData = AppUtils.copyBeanBySerialize(oldData, Goods.class);
			updateForm.updateGoods(newData);
			if(!oldData.equals(newData)) {
				goodsMap.put(newData.getId(), newData);
				success = true;
			}
		}
		return success;
	}

	public boolean updateGoodsStatus(StoreGoods storeGoods, GoodsUpdateStateForm updateForm) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, Goods> goodsMap = storeGoods.getGoodsMap();
		if (goodsMap.containsKey(updateForm.getGoodsId())) {
			Goods data = goodsMap.get(updateForm.getGoodsId());
			data.setState(updateForm.getState());
			goodsMap.put(data.getId(), data);
			success = true;
		}
		return success;
	}

	/*************************** 商品分类基本操作 ***************************/
	public boolean addGoodsType(StoreGoods storeGoods, GoodsTypeAddForm addForm) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		GoodsType data = addForm.toGoodsType();
		long index = Long.valueOf(data.getId());
		Map<String, GoodsType> goodsTypeMap = storeGoods.getGoodsTypeMap();
		if (!goodsTypeMap.containsKey(data.getId()) && storeGoods.getGoodsTypeIdIndex() + 1 == index) {
			goodsTypeMap.put(data.getId(), data);
			storeGoods.setGoodsTypeIdIndex(index);
			success = true;
		}
		return success;
	}

	public boolean removeGoodsType(StoreGoods storeGoods, GoodsTypeRemoveForm removeForm) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, GoodsType> goodsTypeMap = storeGoods.getGoodsTypeMap();
		if (goodsTypeMap.containsKey(removeForm.getGoodsTypeId())) {
			GoodsType goodsType = goodsTypeMap.get(removeForm.getGoodsTypeId());
			goodsType.setEntityState(EntityState.Deleted.ordinal());
			goodsType.setLastUpdateTime(System.currentTimeMillis());
			success = true;
		}
		return success;
	}

	public boolean updateGoodsType(StoreGoods storeGoods, GoodsTypeUpdateForm data) {
		if (storeGoods == null) {
			return false;
		}
		boolean success = false;
		Map<String, GoodsType> goodsTypeMap = storeGoods.getGoodsTypeMap();
		if (goodsTypeMap.containsKey(data.getId())) {
			GoodsType goodsType = goodsTypeMap.get(data.getId());
			data.updateGoodsType(goodsType);
			goodsType.setLastUpdateTime(System.currentTimeMillis());
			goodsTypeMap.put(goodsType.getId(), goodsType);
			success = true;
		}
		return success;
	}
}
