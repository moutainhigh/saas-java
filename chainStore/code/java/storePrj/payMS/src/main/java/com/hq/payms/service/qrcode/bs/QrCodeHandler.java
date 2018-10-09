package com.hq.payms.service.qrcode.bs;

import com.zenmind.common.hotSwap.HotSwap;

public class QrCodeHandler {

	public static QrCodeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(QrCodeHandler.class);
	}


}
