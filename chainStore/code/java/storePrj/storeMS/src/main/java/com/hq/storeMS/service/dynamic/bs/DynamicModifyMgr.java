package com.hq.storeMS.service.dynamic.bs;

import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.dynamic.apiData.DynamicAddForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicUpdateInfoForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicUpdateStatusForm;
import com.hq.storeMS.service.dynamic.data.Dynamic;
import com.zenmind.common.hotSwap.HotSwap;

public class DynamicModifyMgr {

	public static DynamicModifyMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DynamicModifyMgr.class);
	}
	
	public void addAndReturnId(Dynamic target) {
		DynamicDataHolder.getInstance().addAndReturnId(target);
	}

	public void update(Dynamic target) {
		DynamicDataHolder.getInstance().update(target);
	}

	public Dynamic addByForm(DynamicAddForm inputForm) {
		Dynamic target = inputForm.toDynamic();
		addAndReturnId(target);
		return target;
	}
	
	public OperateTips updateDynamicInfo(long dynamicId, DynamicUpdateInfoForm inputForm) {
		OperateTips operateTips = OperateTips.newInstance(false, "更新失败");
		Dynamic target = DynamicDataHolder.getInstance().get(dynamicId);
		if(target == null) {
			operateTips.setTips("动态不存在，更新失败");
			return operateTips;
		}
		inputForm.updateDynamic(target);
		update(target);
		operateTips.setSuccess(true);
		return operateTips;
	}

	public OperateTips updateDynamicStatus(long dynamicId, DynamicUpdateStatusForm inputForm) {
		OperateTips operateTips = OperateTips.newInstance(false, "发布失败");
		Dynamic target = DynamicDataHolder.getInstance().get(dynamicId);
		if(target == null) {
			operateTips.setTips("动态不存在，发布失败");
			return operateTips;
		}
		inputForm.updateDynamic(target);
		update(target);
		operateTips.setSuccess(true);
		return operateTips;
	}
}
