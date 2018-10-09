package com.hq.storeManagerMS.service.charge.bs.updateHandler;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateType;
import com.hq.storeManagerMS.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeHandleHelper {

	public static ChargeHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChargeHandleHelper.class);
	}

	private Map<ChargeUpdateType, IChargeUpdateHandle> handleMapper = new HashMap<ChargeUpdateType, IChargeUpdateHandle>();

	public ChargeHandleHelper() {
		handleMapper.put(ChargeUpdateType.UpdateInfo, new IChargeUpdateHandle(){
			@Override
			public OperateTips update(ChargeUpdateApiForm updateForm){
				return ChargeUpdateHandle.getInstance().updateInfo(updateForm);
			}
		});
		handleMapper.put(ChargeUpdateType.UpdateStatus, new IChargeUpdateHandle(){
			@Override
			public OperateTips update(ChargeUpdateApiForm updateForm){
				return ChargeUpdateHandle.getInstance().updateStatus(updateForm);
			}
		});
		handleMapper.put(ChargeUpdateType.UpdatePayInfo, new IChargeUpdateHandle(){
			@Override
			public OperateTips update(ChargeUpdateApiForm updateForm){
				return ChargeUpdateHandle.getInstance().updatePayInfo(updateForm);
			}
		});
	}

	public OperateTips update(ChargeUpdateApiForm updateForm) {
		ChargeUpdateType updateType = updateForm.getUpdateTypeEnum();
		IChargeUpdateHandle handle = handleMapper.get(updateType);
		if (handle!=null) {
			return handle.update(updateForm);
		}
		return OperateTips.newInstance(false, "操作失败");
	}
}
