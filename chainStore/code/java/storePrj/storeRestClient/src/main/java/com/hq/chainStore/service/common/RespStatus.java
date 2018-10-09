package com.hq.chainStore.service.common;

public enum RespStatus {
	
	OK(200), //服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
	INVALID_REQUEST(400),// [POST/PUT]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
	UN_AUTHORIZED(401),//用户发出的请求未授权，该操作是幂等的。
	UN_KNOWN(402),//未知错误或代码。
	NOT_FOUND(404),//用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
	INTERNAL_SERVER_ERROR(500),//服务器发生错误，用户将无法判断发出的请求是否成功。
	;
	
	
	
	private int code = 200;
	
	private RespStatus(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static RespStatus valueOf(int code){
		RespStatus status = RespStatus.UN_KNOWN;
		switch (code) {
		case 200:
			status = RespStatus.OK;
			break;
		case 400:
			status = RespStatus.INVALID_REQUEST;
			break;
		case 401:
			status = RespStatus.UN_AUTHORIZED;
			break;
		case 404:
			status = RespStatus.NOT_FOUND;
			break;
		case 500:
			status = RespStatus.INTERNAL_SERVER_ERROR;
			break;

		default:
			break;
		}
		return status;
	}
	
	
}
