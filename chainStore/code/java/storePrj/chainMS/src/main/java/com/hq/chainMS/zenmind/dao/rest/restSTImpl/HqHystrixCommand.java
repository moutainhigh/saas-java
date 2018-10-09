package com.hq.chainMS.zenmind.dao.rest.restSTImpl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hq.chainMS.service.common.RespStatus;
import com.netflix.hystrix.HystrixCommand;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class HqHystrixCommand extends HystrixCommand<ResponseEntity<RestResp>> {
	private String uri = "";
	private HttpMethod method = null;
	private HttpEntity<String> entity = null;

	public HqHystrixCommand(Setter setter, String uri, HttpMethod method,
			HttpEntity<String> entity) {
		super(setter);
		this.uri = uri;
		this.method = method;
		this.entity = entity;
	}

	@Override
	protected ResponseEntity<RestResp> run() throws Exception {
		ResponseEntity<RestResp> respEntity = null;
		try {
			respEntity = (ResponseEntity<RestResp>) getTemplate().exchange(uri, method, entity, RestResp.class);
		} catch (RuntimeException e) {
			throw (RestProxyException.newInstance("微服务出现异常。", e));
		}
		return respEntity;
	}
	
	@Override
	public ResponseEntity<RestResp> getFallback() {
		System.out.println("uri="+uri);
		System.out.println("method="+method.toString());
		System.out.println("entity="+JsonUtil.getInstance().toJson(entity));
		System.out.println("============>>>>>>>>>>>fallback");
		//返回服务不可用的信息给Client
		RestResp restResp = new RestResp();
		restResp.setCode(RespStatus.INTERNAL_SERVER_ERROR.getCode());
		restResp.setDsResp("断路器-服务暂不可用，请稍后尝试。");
		ResponseEntity<RestResp> respEntity = new ResponseEntity<RestResp>(restResp, HttpStatus.INTERNAL_SERVER_ERROR);
		return respEntity;
	}

	private RestTemplate getTemplate() {
		return RestTemplateMgr.getInstance().getTemplate();
	}
}