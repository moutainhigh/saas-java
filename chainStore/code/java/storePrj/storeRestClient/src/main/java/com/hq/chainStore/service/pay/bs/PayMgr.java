package com.hq.chainStore.service.pay.bs;

import com.hq.chainStore.service.pay.apiData.BeScanApiForm;
import com.hq.chainStore.service.pay.apiData.ScanApiForm;
import com.hq.chainStore.service.pay.data.PayDAO;
import com.hq.chainStore.service.pay.data.PayResp;
import com.zenmind.common.hotSwap.HotSwap;

public class PayMgr {

	public static PayMgr getInstance() {
		return HotSwap.getInstance().getSingleton(PayMgr.class);
	}
	
	public PayResp beScan(BeScanApiForm form) {
		final String path = "beScan";
		return PayDAO.getInstance().beScan(path, form);
	}
	
	public PayResp scan(ScanApiForm form) {
		final String path = "scan";
		return PayDAO.getInstance().scan(path, form);
	}
	
}
