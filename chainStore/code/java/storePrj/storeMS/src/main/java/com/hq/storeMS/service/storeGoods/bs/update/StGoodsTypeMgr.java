package com.hq.storeMS.service.storeGoods.bs.update;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeAddForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeRemoveForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsTypeUpdateForm;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.GoodsType;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeGoods.data.StoreGoodsBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class StGoodsTypeMgr {
	public static StGoodsTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StGoodsTypeMgr.class);
	}

	// 添加商品分类
	public OperateTips addGoodsType(long storeId, GoodsTypeAddForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.AddGoodsType.getDescript() + "失败");
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		if (checkNameExists(inputData.getName(), storeGoods)) {
			tips.setTips("商品分类已存在");
			return tips;
		}
		if (StoreGoodsBeanHelper.getInstance().addGoodsType(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
			addLogger(storeId, inputData.getName(), StoreGoodsUpdateType.AddGoodsType);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 删除商品分类
	public OperateTips removeGoodsType(long storeId, GoodsTypeRemoveForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.RemoveGoodsType.getDescript() + "失败");
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		if (StoreGoodsBeanHelper.getInstance().removeGoodsType(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
			tips.setSuccess(true);
			addLogger(storeId, storeGoods.getGoodsTypeMap().get(inputData.getGoodsTypeId()).getName(),
					StoreGoodsUpdateType.RemoveGoodsType);
		}
		return tips;
	}

	// 修改商品分类
	public OperateTips updateGoodsType(long storeId, GoodsTypeUpdateForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.UpdateGoodsType.getDescript() + "失败");
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);

		if (checkNameExists(inputData.getName(), storeGoods)) {
			tips.setTips("商品分类已存在");
			return tips;
		}

		if (StoreGoodsBeanHelper.getInstance().updateGoodsType(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
			tips.setSuccess(true);
			addLogger(storeId, inputData.getName(), StoreGoodsUpdateType.UpdateGoodsType);
		}
		return tips;
	}

	private void addLogger(long storeId, String typeName, StoreGoodsUpdateType updateType) {
		OpLogTaskMgr.getInstance()
				.add(OpLog.newInstance(storeId, typeName, OpLogTypeEnum.Product, updateType.getDescript()));
	}

	private boolean checkNameExists(String name, StoreGoods storeGoods) {
		boolean flag = false;
		Map<String, GoodsType> goodsTypeMap = storeGoods.takeGoodsTypeMapWithTypeNameKey();
		if (StringUtils.isNoneBlank(name) && MapUtils.isNotEmpty(goodsTypeMap)) {
			GoodsType goodsType = goodsTypeMap.get(name);
			if (goodsType != null && goodsType.getEntityState() != EntityState.Deleted.ordinal()) {
				flag = true;
			}
		}
		return flag;
	}
}
