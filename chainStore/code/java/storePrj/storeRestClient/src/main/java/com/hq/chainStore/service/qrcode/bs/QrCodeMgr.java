package com.hq.chainStore.service.qrcode.bs;

import com.hq.chainStore.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.chainStore.service.qrcode.apiData.QrCodeResp;
import com.hq.chainStore.service.qrcode.data.QrCodeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class QrCodeMgr {

	public static QrCodeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(QrCodeMgr.class);
	}

	public QrCodeResp genQrCode(QrCodeAPIForm apiForm) throws Exception{
		return QrCodeDAO.getInstance().genQrCode(apiForm);
	}
}
