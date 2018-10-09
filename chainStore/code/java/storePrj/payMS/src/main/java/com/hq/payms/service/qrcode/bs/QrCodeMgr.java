package com.hq.payms.service.qrcode.bs;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.hq.payms.service.img.apiData.FileUploadApiForm;
import com.hq.payms.service.img.apiData.ImgResp;
import com.hq.payms.service.img.bs.ImgMgr;
import com.hq.payms.service.img.data.UploadModuleType;
import com.hq.payms.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.storeFileClient.service.file.data.UploadFileType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.qrcode.QRCodeHelper;

public class QrCodeMgr {

	public static QrCodeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(QrCodeMgr.class);
	}

	public String genQrCode(QrCodeAPIForm apiForm) throws Exception{
		String logoImgPath = "";
		if(StringUtils.isNotBlank(apiForm.getLogoUrl())){
			logoImgPath = apiForm.getLogoUrl();
		}
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpg";
		InputStream is = null;
		ImgResp resp = null;
		try {
			is = QRCodeHelper.newInstance().encode(apiForm.getContent(), logoImgPath, true);
			FileUploadApiForm form = FileUploadApiForm.newInstance();
			form.setFileType(UploadFileType.IMG.getType());
			form.setModuleType(UploadModuleType.QrCode.getType());
			form.setModuleId("storeQrcode");
			resp = ImgMgr.getInstance().saveImg(is, fileName, form);
		} finally{
			if(is != null){
				is.close();
			}
		}
		return resp.getImgPathList().get(0);
	}
	
	
}
