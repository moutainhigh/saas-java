package com.hq.storeMS.service.qrcode.bs;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeFileClient.service.file.data.UploadFileType;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.QrCodeDoActionEnum;
import com.hq.storeMS.service.img.apiData.FileUploadApiForm;
import com.hq.storeMS.service.img.apiData.ImgResp;
import com.hq.storeMS.service.img.bs.ImgMgr;
import com.hq.storeMS.service.img.data.UploadModuleType;
import com.hq.storeMS.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.storeMS.service.wxAccessToken.bs.WxAccessTokenMgr;
import com.hq.storeMS.service.wxAccessToken.data.WxAccessToken;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.qrcode.QRCodeHelper;
import com.zenmind.wx.AcodeResponse;
import com.zenmind.wx.http.ByteUtil;

public class QrCodeMgr {

	public static QrCodeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(QrCodeMgr.class);
	}

	public String genQrCode(QrCodeAPIForm apiForm) throws Exception{
		String logoImgPath = "";
		if(StringUtils.isNoneBlank(apiForm.getLogoUrl())){
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
	
	public String genJoinStoreQrCode(long storeId) throws Exception{
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpg";
		InputStream is = null;
		ImgResp resp = null;
		try {
			String logoPath = StoreMSCfgMgr.getProp().getLogoPath();
			String content = "{}_{}_{}";
			is = QRCodeHelper.newInstance().encode(StringFormatUtil.format(content, ServerConstants.APPID, QrCodeDoActionEnum.JoinStore.ordinal(), storeId), logoPath, true);
			resp = ImgMgr.getInstance().genJoinStoreQrCode(storeId, is, fileName);
			if(resp!=null){
				return resp.getImgPathList().get(0);
			}
		} finally{
			if(is != null){
				is.close();
			}
		}
		return null;
	}
	
	public String genWxACode(long storeId) throws Exception{
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".jpg";
		InputStream is = null;
		ImgResp resp = null;
		try {
//			TokenResponse tokenResponse = QrcodeDataHolder.getInstance().getTokenResponse();
//			if(tokenResponse == null){
//				return null;
//			}
			
			WxAccessToken wxAccessToken = WxAccessTokenMgr.getInstance().getWxAccessToken(StoreMSCfgMgr.getProp().getWxAppId());
			if(wxAccessToken == null || StringUtils.isBlank(wxAccessToken.getAccess_token())) {
				return null;
			}
			AcodeResponse acodeResponse = QrcodeDataHolder.getInstance().getAcodeResponse(wxAccessToken.getAccess_token(), storeId);
			if(acodeResponse != null && acodeResponse.getErrcode() == 0){
				is = ByteUtil.bytes2Stream(acodeResponse.getImageCode());
				FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
				apiForm.setFileType(UploadFileType.IMG.getType());
				apiForm.setModuleType(UploadModuleType.Store.getType());
				apiForm.setModuleId(String.valueOf(storeId));
				resp = ImgMgr.getInstance().saveImg(is, fileName, apiForm);
				if(resp!=null){
					return resp.getImgPathList().get(0);
				}
			}
		} finally{
			if(is != null){
				is.close();
			}
		}
		return null;
	}
}
