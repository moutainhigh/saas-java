package com.hq.storeMS.service.wxMedia.bs;

import java.util.UUID;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.img.apiData.ImgResp;
import com.hq.storeMS.service.img.bs.ImgMgr;
import com.hq.storeMS.service.wxAccessToken.bs.WxAccessTokenMgr;
import com.hq.storeMS.service.wxAccessToken.data.WxAccessToken;
import com.hq.storeMS.service.wxMedia.apiData.WxMediaSaveApiForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.GetMediaRequestParams;
import com.zenmind.wx.GetMediaResponse;
import com.zenmind.wx.WxProxy;


public class WxMediaHandler {
	public static WxMediaHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxMediaHandler.class);
	}

	public ReqResult<ImgResp> saveImg(WxMediaSaveApiForm form) {
		ReqResult<ImgResp> result = ReqResult.newInstance(false, ImgResp.class);
		try {
			byte[] contentBytes = downloadImgFromWx(form.getMediaId(), form.getAppId());
			if(contentBytes == null) {
				result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
				result.setTips("从微信服务器下载图片失败");
				return result;
			}
			ImgResp imgResp = saveImgToOurServer(form.getMediaId(), contentBytes);
			result.setTarget(imgResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.WeChat, "WxMediaHandler[saveImg]", "", e);
		}
		return result;
	}
	
	/**
	 * 从微信服务器下载图片
	 * @param mediaId
	 * @return
	 */
	private byte[] downloadImgFromWx(String mediaId, String appId) {
		WxAccessToken wxAccessToken = WxAccessTokenMgr.getInstance().getWxAccessToken(appId);
		GetMediaRequestParams getMediaRequestParams = GetMediaRequestParams.newInstance(wxAccessToken.getAccess_token(), mediaId);
		GetMediaResponse getMediaResponse = WxProxy.getInstance().getMedia(getMediaRequestParams);
		return getMediaResponse.getContentBytes();
	}
	
	/**
	 * 保存图片到自己的服务器
	 * @param mediaId
	 * @param contentBytes
	 * @return
	 * @throws Exception
	 */
	private ImgResp saveImgToOurServer(String mediaId, byte[] contentBytes) throws Exception {
		final String pattern = "{}{}{}{}.{}";
		String suffix = "jpg";
		String contentType = "image/jpeg";
		String uuid = UUID.randomUUID().toString();
		String fileName =StringFormatUtil.format(pattern, mediaId, "@", uuid, suffix);
		
		MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, contentType, contentBytes);
		String moduleId = "media"; //moduleId写固定值，不必创建多个文件夹
		return ImgMgr.getInstance().saveWxImg(moduleId, multipartFile);
	}

}
