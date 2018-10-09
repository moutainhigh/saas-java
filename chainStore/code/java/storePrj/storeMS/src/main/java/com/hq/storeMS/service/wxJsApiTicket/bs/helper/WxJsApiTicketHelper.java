package com.hq.storeMS.service.wxJsApiTicket.bs.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.wxJsApiTicket.data.WxJsApiTicket;
import com.zenmind.common.hotSwap.HotSwap;


public class WxJsApiTicketHelper {
	
	public static WxJsApiTicketHelper getInstance(){
		return HotSwap.getInstance().getSingleton(WxJsApiTicketHelper.class);
	}
	
	/**
	 * 生成JSAPI签名
	 * 
	 * 因为url每次可能不同，所以每次都需要生成签名
	 * @param args
	 */
	public WxJsApiTicket generateSignature(WxJsApiTicket target, String pageUrl) {  
	    String jsapi_ticket = target.getJsapiTicket();
	    //随机字符串  
	    String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
	    //时间戳  
	    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
	    //获取url (当前网页的URL，不包含#及其后面部分)
	    String url = pageUrl;
	    //将参数排序并拼接字符串  
	    String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;  
	    //将字符串进行sha1加密  
	    String signature = SHA1(str);  
	    target.setNonceStr(noncestr);
	    target.setTimestamp(timestamp);
	    target.setSignature(signature);
	    MainLog.info(LogModule.Tmp, "WxJsApiTicketHelper[generateSignature] ", target.toString());
	    return target;
	}  
	
	/**
	 * 用时间戳、随机数、jsapi_ticket和要访问的url按照签名算法拼接字符串
	 * @param decript
	 * @return
	 */
	private String SHA1(String decript) {  
	    try {  
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");  
	        digest.update(decript.getBytes());  
	        byte messageDigest[] = digest.digest();  
	        StringBuffer hexString = new StringBuffer();  
	        // 字节数组转换为 十六进制 数  
            for (int i = 0; i < messageDigest.length; i++) {  
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
                if (shaHex.length() < 2) {  
                    hexString.append(0);  
                }  
                hexString.append(shaHex);  
            }  
            return hexString.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
	    return "";  
	}  
}
