package com.hq.chainMS.service.chainCard.bs.updateHandle;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateType;
import com.hq.chainMS.service.chainCard.apiData.PrdCardTypeAddForm;
import com.hq.chainMS.service.chainCard.apiData.PrdCardTypeRemoveForm;
import com.hq.chainMS.service.chainCard.apiData.PrdCardTypeUpdateForm;
import com.hq.chainMS.service.chainCard.bs.ChainCardMgr;
import com.hq.chainMS.service.chainCard.data.ChainCard;
import com.hq.chainMS.service.chainCard.data.ChainCardBeanHelper;
import com.hq.chainMS.service.chainCard.data.PrdCardType;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.common.EntityState;
import com.hq.chainMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class PrdCardTypeHandle {

	public static PrdCardTypeHandle getInstance() {
		return HotSwap.getInstance().getSingleton(PrdCardTypeHandle.class);
	}

	// 添加分类
	public OperateTips addPrdCardType(long chainId, PrdCardTypeAddForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.AddPrdCardType.getMark() + "失败");
		
		ChainCard chainData = ChainCardMgr.getInstance().get(chainId);
		if (checkNameExists4Add(inputForm.getName(), chainData.getPrdCardTypeMap().values())) {
			tips.setTips("次卡分类已存在");
			return tips;
		}
		
		if (ChainCardBeanHelper.getInstance().addPrdCardType(chainData, inputForm)) {
			ChainCardMgr.getInstance().updateChainCard(chainData);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 删除分类
	public OperateTips delPrdCardType(long chainId, PrdCardTypeRemoveForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.DelPrdCardType.getMark() + "失败");

		ChainCard chainData = ChainCardMgr.getInstance().get(chainId);
		if (ChainCardBeanHelper.getInstance().delPrdCardType(chainData, inputForm)) {
			ChainCardMgr.getInstance().updateChainCard(chainData);
			tips.setSuccess(true);
		}
		return tips;
	}

	// 修改分类
	public OperateTips updPrdCardType(long chainId, PrdCardTypeUpdateForm inputForm) {
		ChainUserAuthUtils.getInstance().checkPermission(chainId, AdminPermEnum.SELL_PRODUCT_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, ChainCardUpdateType.UpdPrdCardType.getMark() + "失败");

		ChainCard chainData = ChainCardMgr.getInstance().get(chainId);
		if (checkNameExists4Update(inputForm.getName(), chainData.getPrdCardTypeMap().values(), inputForm.getId())) {
			tips.setTips("次卡分类已存在");
			return tips;
		}

		if (ChainCardBeanHelper.getInstance().updatePrdCardType(chainData, inputForm)) {
			ChainCardMgr.getInstance().updateChainCard(chainData);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean checkNameExists4Add(String name, Collection<PrdCardType> types) {
		return checkNameExists(name, types, "");
	}

	private boolean checkNameExists4Update(String name, Collection<PrdCardType> types, String id) {
		return checkNameExists(name, types, id);
	}

	private boolean checkNameExists(String name, Collection<PrdCardType> types, String id) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		for (PrdCardType type : types) {
			if (name.equals(type.getName()) && type.getEntityState() != EntityState.Deleted.ordinal() && !id.equals(type.getId())) {
				return true;
			}
		}
		return false;
	}
}
