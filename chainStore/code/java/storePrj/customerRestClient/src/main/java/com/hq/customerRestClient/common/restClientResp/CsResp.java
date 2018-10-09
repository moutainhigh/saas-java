package com.hq.customerRestClient.common.restClientResp;

import java.util.List;

import com.zenmind.common.json.JsonUtil;

public class CsResp<T> {
	
	private int code;
	
	private String tJson;
	
	private String tips;
	
	private String tListJson;
	
	//数据同步返回信息
	private String dsResp;	
	
	public CsResp<T> withTips(String tipsP){
		if(tipsP!=null){
			this.tips = tipsP;
		}
		return this;
	}
	
	public CsResp<T> withTarget(T targetP){
		if(targetP!=null){
			this.tJson = JsonUtil.getInstance().toJson(targetP);
		}
		return this;
	}
	
	
	public CsResp<T> withTargetList(List<T> targetListP){
		if(targetListP!=null){
			this.tListJson = JsonUtil.getInstance().toJson(targetListP);
		}
		return this;
	}
	

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String gettJson() {
		return tJson;
	}

	public void settJson(String tJson) {
		this.tJson = tJson;
	}

	public String gettListJson() {
		return tListJson;
	}

	public void settListJson(String tListJson) {
		this.tListJson = tListJson;
	}

	public String getDsResp() {
		return dsResp;
	}

	public void setDsResp(String dsResp) {
		this.dsResp = dsResp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	
}
