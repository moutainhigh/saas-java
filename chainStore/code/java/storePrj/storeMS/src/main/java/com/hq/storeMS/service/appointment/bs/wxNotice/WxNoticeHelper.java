package com.hq.storeMS.service.appointment.bs.wxNotice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hq.storeMS.common.config.StoreMSCfg;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.appointment.data.AppointProduct;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailDataHolder;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.wxAccessToken.bs.WxAccessTokenMgr;
import com.hq.storeMS.service.wxAccessToken.data.WxAccessToken;
import com.hq.storeMS.service.wxOpenId.bs.WxOpenIdMgr;
import com.hq.storeMS.service.wxOpenId.data.WxOpenId;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.wx.SendTemplateMsgParams;
import com.zenmind.wx.WxProxy;

/**
 * 智美预约小程序发送通知的helper类
 * 做与具体业务相关的处理 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class WxNoticeHelper {
	public static WxNoticeHelper getInstance(){		
		return HotSwap.getInstance().getSingleton(WxNoticeHelper.class);
	}
	
	private StoreMSCfg cfg = StoreMSCfgMgr.getProp();
	
	public void sendNotice(Appointment appointment) {
		try {
//			String accessToken = QrcodeDataHolder.getInstance().getTokenResponse().getAccess_token();
			WxAccessToken wxAccessToken = WxAccessTokenMgr.getInstance().getWxAccessToken(StoreMSCfgMgr.getProp().getWxAppId());
			String accessToken = wxAccessToken.getAccess_token();
			SendTemplateMsgParams stmParams = buildSendTemplateMsgParams(appointment);
			
			WxProxy.getInstance().sendTemplateMsg(accessToken,stmParams);
			
			MainLog.info(LogModule.Appointment, "WxNoticeHelper[sendNotice]", "");
		} catch (Exception e) {
			MainLog.error(LogModule.Appointment, "WxNoticeHelper[sendNotice]", "", e);
		}
	}
	
	private SendTemplateMsgParams buildSendTemplateMsgParams(Appointment appointment) {
		WxOpenId wxOpenId = WxOpenIdMgr.getInstance().genOpenId(cfg.getWxAppId(),appointment.getJsCode());
		if(wxOpenId == null) return null;
		
		String openId = wxOpenId.getOpenId();
		String templateId = cfg.getWxTemplateId();
		String page = cfg.getWxPage();
		String formId = appointment.getFormId();
		String data = buildDataParam(appointment);
		return SendTemplateMsgParams.newInstance(openId,templateId, page, formId, data); 
		
	}
	
	private String buildDataParam(Appointment appointment) {
		long phone = 0L;
		LeaguerDetail leaguerDetail = LeaguerDetailDataHolder.getInstance().get(appointment.getStoreId(), appointment.getLeaguerId());
		if(leaguerDetail != null) {
			phone = leaguerDetail.getPhone();
		}
		
		String appointProductNames = "";
		List<AppointProduct> appointProducts = appointment.getAppointProducts();
		for (AppointProduct appointProduct : appointProducts) {
			appointProductNames += appointProduct.getProductName() + "，";
		}
		if(appointProductNames.length() > 1) {
			appointProductNames = appointProductNames.substring(0, appointProductNames.length()-1);
		}
		
		String storeName = "";
		String storeAddress = "";
		StoreRO store = StoreMgr.getInstance().getReadOnly(appointment.getStoreId());
		if(store != null) {
			if(StringUtils.isNotBlank(store.getName()))
				storeName = store.getName();
			if(StringUtils.isNotBlank(store.getAddress()))
				storeAddress = store.getAddress();
		}
		
		StringBuilder data = new StringBuilder();
		data.append("\"data\": {");
		data.append(" \"keyword1\": {");
		data.append("\"value\":\"" + appointment.getCreatorName() + "\"");
		data.append("}, ");
		data.append(" \"keyword2\": {");
		data.append("\"value\":\"" + phone + "\"");
		data.append("}, ");
		data.append(" \"keyword3\": {");
		data.append("\"value\":\"" + getFormattedTime(appointment.getAppointTime()) + "\"");
		data.append("}, ");
		data.append(" \"keyword4\": {");
		data.append("\"value\":\"" + appointProductNames + "\"");
		data.append("}, ");
		data.append(" \"keyword5\": {");
		data.append("\"value\":\"" + storeName + "\"");
		data.append("}, ");
		data.append(" \"keyword6\": {");
		data.append("\"value\":\"" + storeAddress + "\"");
		data.append("}}");
		return data.toString();
	}
	
	private String getFormattedTime(long time) {
		  Date date = new Date(time);
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		  String result = formatter.format(date);
		  return result;
	}
	
}
