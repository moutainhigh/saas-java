package com.hq.chainStore.service.qrcode.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.chainStore.service.qrcode.apiData.QrCodeResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class QrCodeDAO {

	public static QrCodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(QrCodeDAO.class);
	}

	public QrCodeResp genQrCode(QrCodeAPIForm apiForm) {
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "qrcode", "genQrCode");
		RestResp restResp = RestProxy.getInstance().rawReq(uri, apiForm);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), QrCodeResp.class);
	}
	
}
