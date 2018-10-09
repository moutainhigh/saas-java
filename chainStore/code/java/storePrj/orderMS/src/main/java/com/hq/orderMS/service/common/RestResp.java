package com.hq.orderMS.service.common;

import java.util.List;

import com.zenmind.dataSyn.DataSynMgr;

public class RestResp<T> {
	
	private int code;
	
	private String tJson;
	
	private String tips;
	
	private String tListJson;
	
	//登陆的时候回用到token;
	private String token;
	
	//数据同步返回信息
	private String dsResp;	
	
	public RestResp<T> withTips(String tipsP){
		if(tipsP!=null){
			this.tips = tipsP;
		}
		return this;
	}
	
	public RestResp<T> withTarget(T targetP){
		if(targetP!=null){
			this.tJson = DataSynMgr.getInstance().toClientData(targetP);
		}
		return this;
	}
	
	public RestResp<T> withCode(int codeP){
		this.code = codeP;
		return this;
	}
	
	
	public RestResp<T> withTargetList(List<T> targetListP){
		if(targetListP!=null){
			this.tListJson = DataSynMgr.getInstance().toClientDataList(targetListP);
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	
}
