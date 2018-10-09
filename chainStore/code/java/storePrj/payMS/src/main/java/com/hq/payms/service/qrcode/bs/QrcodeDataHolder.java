package com.hq.payms.service.qrcode.bs;

import com.zenmind.common.hotSwap.HotSwap;

public class QrcodeDataHolder {
	public static QrcodeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(QrcodeDataHolder.class);
	}
	
}
