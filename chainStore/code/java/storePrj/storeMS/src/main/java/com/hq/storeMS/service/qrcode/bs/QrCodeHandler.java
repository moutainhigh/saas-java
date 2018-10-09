package com.hq.storeMS.service.qrcode.bs;

import java.util.List;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.storeMS.service.qrcode.apiData.QrCodeResp;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class QrCodeHandler {

	public static QrCodeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(QrCodeHandler.class);
	}

	public ReqResult<QrCodeResp> genQrCode(QrCodeAPIForm apiForm) {
		ReqResult<QrCodeResp> result = ReqResult.newInstance(false, QrCodeResp.class);
		QrCodeResp qrCodeResp = QrCodeResp.newInstance();
		try {
			qrCodeResp.setImgUrl(QrCodeMgr.getInstance().genQrCode(apiForm));
			result.setTarget(qrCodeResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(apiForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.BUser, "BUserHandler[add]", reason, e);
		}
		return result;
	}
	
	public ReqResult<QrCodeResp> genQrCodeList(List<QrCodeAPIForm> apiForms) {
		ReqResult<QrCodeResp> result = ReqResult.newInstance(false, QrCodeResp.class);
		QrCodeResp qrCodeResp = QrCodeResp.newInstance();
		try {
			for (QrCodeAPIForm apiForm : apiForms) {
				qrCodeResp.addPath(QrCodeMgr.getInstance().genQrCode(apiForm));
			}
			result.setTarget(qrCodeResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(apiForms);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.BUser, "BUserHandler[add]", reason, e);
		}
		return result;
	}

}
