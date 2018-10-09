package com.hq.payms.service.wxpay.data;

import org.apache.commons.lang3.StringUtils;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 公共的响应参数 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@SynClass
public class CommonResp {
	protected String return_code;             //返回状态码  
	protected String return_msg;              //返回信息  
	protected String appid;                   //公众账号ID  
	protected String mch_id;                  //商户号  
	protected String nonce_str;               //随机字符串  
	protected String sign;                    //签名  
	protected String result_code;             //业务结果  
	protected String err_code;                //错误代码  
	protected String err_code_des;            //错误代码描述  
	
	/*** 子商户模式下额外响应参数 ***/
	protected String sub_appid;				  //子商户公众账号ID
	protected String sub_mch_id;			  //子商户号  
    
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getSub_appid() {
		return sub_appid;
	}
	public void setSub_appid(String sub_appid) {
		this.sub_appid = sub_appid;
	}
	public String getSub_mch_id() {
		return sub_mch_id;
	}
	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	
	@Override
	public String toString() {
		return "CommonResp [return_code=" + return_code + ", return_msg=" + return_msg + ", appid=" + appid
				+ ", mch_id=" + mch_id + ", sub_appid=" + sub_appid + ", sub_mch_id=" + sub_mch_id + ", nonce_str="
				+ nonce_str + ", sign=" + sign + ", result_code=" + result_code + ", err_code=" + err_code
				+ ", err_code_des=" + err_code_des + "]";
	}
	
    public boolean isResultSuccess() {
        return "SUCCESS".equals(this.getReturn_code())
				&& "SUCCESS".equals(this.getResult_code());
    }
    
    public boolean isResultFail() {
        return "FAIL".equals(this.getReturn_code());
    }
    
    public boolean isReturnMsgNotOk() {
    	return StringUtils.isNotBlank(this.getReturn_msg()) && !"OK".equals(this.getReturn_msg());
    }
    
}
