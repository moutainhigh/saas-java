package com.hq.storeClient.common.restClientResp;

import java.util.List;

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


	
	public int getCode(){
		return this.status.getCode();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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
