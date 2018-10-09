package com.hq.storeMS.service.storeIncomePay.bs.updateHandle;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeIncomePay.apiData.IncomePayTypeAddForm;
import com.hq.storeMS.service.storeIncomePay.apiData.IncomePayTypeRemoveForm;
import com.hq.storeMS.service.storeIncomePay.apiData.IncomePayTypeUpdateForm;
import com.hq.storeMS.service.storeIncomePay.apiData.StoreIncomePayUpdateType;
import com.hq.storeMS.service.storeIncomePay.bs.StoreIncomePayMgr;
import com.hq.storeMS.service.storeIncomePay.data.IncomePayType;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePay;
import com.hq.storeMS.service.storeIncomePay.data.StoreIncomePayBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class IncomePayTypeMgr {
	public static IncomePayTypeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(IncomePayTypeMgr.class);
	}

	/**
	 * 添加收支分类
	 */
	public OperateTips addIncomePayType(long storeId, IncomePayTypeAddForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreIncomePayUpdateType.AddIncomePayType.getDescript() + "失败");
		StoreIncomePay storeIncomePay = StoreIncomePayMgr.getInstance().getByStoreId(storeId);
		if (checkNameExists(inputData.getName(), inputData.getCategory(), storeIncomePay)) {
			tips.setTips("收支分类已存在");
			return tips;
		}
		if (StoreIncomePayBeanHelper.getInstance().addIncomePayType(storeIncomePay, inputData)) {
			StoreIncomePayMgr.getInstance().update(storeIncomePay);
			tips.setSuccess(true);
		}
		return tips;
	}

	/**
	 * 删除收支分类
	 *
	 * @param storeId
	 * @param formInfo
	 * @return
	 */
	public OperateTips removeIncomePayType(long storeId, IncomePayTypeRemoveForm inputData) {
		OperateTips tips = OperateTips.newInstance(false, StoreIncomePayUpdateType.RemoveIncomePayType.getDescript() + "失败");
		StoreIncomePay storeIncomePay = StoreIncomePayMgr.getInstance().getByStoreId(storeId);
		if (StoreIncomePayBeanHelper.getInstance().removeIncomePayType(storeIncomePay, inputData)) {
			StoreIncomePayMgr.getInstance().update(storeIncomePay);
			tips.setSuccess(true);
		}
		return tips;
	}

	/**
	 * 修改收支分类
	 *
	 * @param storeId
	 * @param formInfo
	 * @return
	 */
	public OperateTips updateIncomePayType(long storeId, IncomePayTypeUpdateForm updateForm) {
		BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.INCOME_PAY_ADMIN);
		OperateTips tips = OperateTips.newInstance(false, StoreIncomePayUpdateType.UpdateIncomePayType.getDescript() + "失败");

		StoreIncomePay storeIncomePay = StoreIncomePayMgr.getInstance().getByStoreId(storeId);

		if (checkNameExists(updateForm.getId(), updateForm.getName(), storeIncomePay)) {
			tips.setTips("收支分类已存在");
			return tips;
		}

		if (StoreIncomePayBeanHelper.getInstance().updateIncomePayType(storeIncomePay, updateForm)) {
			StoreIncomePayMgr.getInstance().update(storeIncomePay);
			tips.setSuccess(true);
		}
		return tips;
	}

	private boolean checkNameExists(String name, int category, StoreIncomePay storeIncomePay) {
		boolean flag = false;
		Map<String, IncomePayType> incomePayTypeMap = storeIncomePay.getIncomePayTypeMap();
		if (!MapUtils.isNotEmpty(incomePayTypeMap))
			return flag;
		for (IncomePayType type : incomePayTypeMap.values()) {
			if (type == null || type.getName() == null || type.getEntityState() == EntityState.Deleted.ordinal())
				continue;
			if (type.getCategory() == category && type.getName().equals(name)) {
				flag = true;
			}
		}
		return flag;
	}

	private boolean checkNameExists(String id, String name, StoreIncomePay storeIncomePay) {
		boolean flag = false;
		Map<String, IncomePayType> incomePayTypeMap = storeIncomePay.getIncomePayTypeMap();
		if (!MapUtils.isNotEmpty(incomePayTypeMap))
			return flag;
		IncomePayType incomePayType = incomePayTypeMap.get(id);
		if (incomePayType == null)
			return flag;
		for (IncomePayType type : incomePayTypeMap.values()) {
			if (type == null || type.getName() == null || type.getEntityState() == EntityState.Deleted.ordinal())
				continue;
			if (type.getCategory() == incomePayType.getCategory() && type.getName().equals(name)) {
				flag = true;
			}
		}
		return flag;
	}
}
