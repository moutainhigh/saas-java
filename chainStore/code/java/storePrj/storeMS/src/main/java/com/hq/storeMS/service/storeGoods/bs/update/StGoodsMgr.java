package com.hq.storeMS.service.storeGoods.bs.update;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.goodsDetail.bs.GoodsDetailMgr;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.storeGoods.apiData.GoodsAddForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsRemoveForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsUpdateForm;
import com.hq.storeMS.service.storeGoods.apiData.GoodsUpdateStateForm;
import com.hq.storeMS.service.storeGoods.apiData.StoreGoodsUpdateType;
import com.hq.storeMS.service.storeGoods.bs.StoreGoodsMgr;
import com.hq.storeMS.service.storeGoods.data.Goods;
import com.hq.storeMS.service.storeGoods.data.StoreGoods;
import com.hq.storeMS.service.storeGoods.data.StoreGoodsBeanHelper;
import com.hq.storeMS.service.storeVip.bs.StoreVipMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class StGoodsMgr {
	public static StGoodsMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StGoodsMgr.class);
	}

	// 添加商品
	public OperateTips addGoods(long storeId, GoodsAddForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.AddGoods.getDescript() + "失败");
		if(StoreVipMgr.getInstance().isGoodsLimited(storeId)){
			tips.setTips("当前店铺商品数量已达上限");
			return tips;
		}
		
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		if (checkNumberExists4Add(inputData.getNumber(), storeGoods.getGoodsMap().values())) {
			tips.setTips("商品编号已存在");
			return tips;
		}
		if (StoreGoodsBeanHelper.getInstance().addGoods(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
			GoodsDetail detail = inputData.toGoodsDetail(storeId);
			GoodsDetailMgr.getInstance().addWithId(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreGoodsUpdateType.AddGoods.getDescript()));
		}
		return tips;
	}

	// 删除商品
	public OperateTips removeGoods(long storeId, GoodsRemoveForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.RemoveGoods.getDescript() + "失败");
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		if (StoreGoodsBeanHelper.getInstance().removeGoods(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
			GoodsDetail detail = GoodsDetailMgr.getInstance().get(storeId, inputData.getGoodsId());
			detail.setEntityState(EntityState.Deleted.ordinal());
			GoodsDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreGoodsUpdateType.RemoveGoods.getDescript()));
		}
		return tips;
	}

	// 更新商品基本信息
	public OperateTips updateGoods(long storeId, GoodsUpdateForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.UpdateGoods.getDescript() + "失败");
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		if (checkNumberExists4Update(inputData.getNumber(), storeGoods.getGoodsMap().values(), inputData.getId())) {
			tips.setTips("商品编号已存在");
			return tips;
		}
		if (StoreGoodsBeanHelper.getInstance().updateGoods(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
		}
		//将详情信息也更新
		GoodsDetail detail = GoodsDetailMgr.getInstance().get(storeId, inputData.getId());
		inputData.updateGoodsDetail(detail);
		GoodsDetailMgr.getInstance().update(detail);
		tips.setSuccess(true);
		OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreGoodsUpdateType.UpdateGoods.getDescript()));
		return tips;
	}

	// 更新商品状态
	public OperateTips updateGoodsState(long storeId, GoodsUpdateStateForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreGoodsUpdateType.UpdateGoodsState.getDescript() + "失败");
		StoreGoods storeGoods = StoreGoodsMgr.getInstance().getByStoreId(storeId);
		if (StoreGoodsBeanHelper.getInstance().updateGoodsStatus(storeGoods, inputData)) {
			StoreGoodsMgr.getInstance().update(storeGoods);
			//将详情信息也更新
			GoodsDetail detail = GoodsDetailMgr.getInstance().get(storeId, inputData.getGoodsId());
			detail.setState(inputData.getState());
			GoodsDetailMgr.getInstance().update(detail);
			tips.setSuccess(true);
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(storeId, detail.getName(), OpLogTypeEnum.Product, StoreGoodsUpdateType.UpdateGoodsState.getDescript()));
		}
		return tips;
	}
	
	private boolean checkNumberExists4Add(String number, Collection<Goods> goods) {
		return checkNumberExists(number, goods, ServerConstants.ZERO);
	}
	
	private boolean checkNumberExists4Update(String number, Collection<Goods> goods, String id) {
		return checkNumberExists(number, goods, id);
	}
	
	private boolean checkNumberExists(String number, Collection<Goods> goods, String id) {
		if (StringUtils.isBlank(number)) {
			return false;
		}
		if (CollectionUtils.isNotEmpty(goods)) {
			for (Goods info : goods) {
				if (number.equals(info.getNumber()) && info.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(info.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
