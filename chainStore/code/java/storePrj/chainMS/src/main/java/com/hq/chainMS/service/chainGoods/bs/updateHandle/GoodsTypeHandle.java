package com.hq.chainMS.service.chainGoods.bs.updateHandle;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.chainGoods.apiData.GoodsTypeAddForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsTypeRemoveForm;
import com.hq.chainMS.service.chainGoods.apiData.GoodsTypeUpdateForm;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateType;
import com.hq.chainMS.service.chainGoods.bs.ChainGoodsMgr;
import com.hq.chainMS.service.chainGoods.data.GoodsType;
import com.hq.chainMS.service.chainGoods.data.ChainGoods;
import com.hq.chainMS.service.chainGoods.data.ChainGoodsBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsTypeHandle {
	public static GoodsTypeHandle getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsTypeHandle.class);
	}

	// 添加商品分类
	public OperateTips addGoodsType(long chainId, GoodsTypeAddForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.AddGoodsType.getDescript() + "失败");
		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
		if (checkNameExists4Add(inputForm.getName(), chainGoods.getGoodsTypeMap().values())) {
			tips.setTips("商品分类已存在");
			return tips;
		}
		if (ChainGoodsBeanHelper.getInstance().addGoodsType(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 删除商品分类
	public OperateTips removeGoodsType(long chainId, GoodsTypeRemoveForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.RemoveGoodsType.getDescript() + "失败");

		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
		if (ChainGoodsBeanHelper.getInstance().removeGoodsType(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 修改商品分类
	public OperateTips updateGoodsType(long chainId, GoodsTypeUpdateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainGoodsUpdateType.UpdateGoodsType.getDescript() + "失败");

		ChainGoods chainGoods = ChainGoodsMgr.getInstance().get(chainId);
		if (checkNameExists4Update(inputForm.getName(), chainGoods.getGoodsTypeMap().values(), inputForm.getId())) {
			tips.setTips("商品分类已存在");
			return tips;
		}

		if (ChainGoodsBeanHelper.getInstance().updateGoodsType(chainGoods, inputForm)) {
			ChainGoodsMgr.getInstance().update(chainGoods);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean checkNameExists4Add(String name, Collection<GoodsType> types) {
		return checkNameExists(name, types, "");
	}

	private boolean checkNameExists4Update(String name, Collection<GoodsType> types, String id) {
		return checkNameExists(name, types, id);
	}

	private boolean checkNameExists(String name, Collection<GoodsType> types, String id) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		for (GoodsType type : types) {
			if (name.equals(type.getName()) && type.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(type.getId())) {
				return true;
			}
		}
		return false;
	}

}
