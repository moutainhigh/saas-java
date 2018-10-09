package com.hq.payms.service.common;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.zenmind.common.json.JsonUtil;

public class ReqResult<T> {
	
	public static <D> ReqResult<D> newInstance(boolean successP, Class<D> clazz){
		ReqResult<D> target = new ReqResult<D>();
		target.success = successP;
		return target;
	}

	private boolean success;
	
	private RespStatus status;
	
	private String tips;
	
	private T target;
	
	private List<T> targetList;	
	
	public ResponseEntity<RestResp<T>> buildRespEntity(){
		RestResp<T> restResp = new RestResp<T>();
		if(this.isSuccess()){
			restResp.withTarget(this.target).withTargetList((List<T>) this.targetList);
		}else{
			restResp.withTips(this.getTips());
		}
		
		restResp.withCode(this.getCode()).withDsResp();
		ResponseEntity<RestResp<T>> respEntity = ResponseEntity.status(200).body(restResp);
		return respEntity;
	}
	
	public ResponseEntity<RestResp<T>> buildJsonRespEntity(){
		RestResp<T> restResp = new RestResp<T>();
		if(this.isSuccess()){
			restResp.settJson(JsonUtil.getInstance().toJsonWithNonStrKey(this.target));
		}else{
			restResp.withTips(this.getTips());
		}
		
		restResp.withCode(this.getCode()).withDsResp();
		ResponseEntity<RestResp<T>> respEntity = ResponseEntity.status(200).body(restResp);
		return respEntity;
	}
	
	public int getCode(){
		return this.status.getCode();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		if(success){
			this.setStatus(RespStatus.OK);
		}
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public RespStatus getStatus() {
		return status;
	}

	public void setStatus(RespStatus status) {
		this.status = status;
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}

	public List<T> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<T> targetList) {
		this.targetList = targetList;
	}
	
}
