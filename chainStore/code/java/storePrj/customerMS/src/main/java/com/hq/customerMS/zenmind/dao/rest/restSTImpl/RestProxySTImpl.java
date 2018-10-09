package com.hq.customerMS.zenmind.dao.rest.restSTImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.customerMS.common.validate.info.ValidateInfo;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.IntfRestProxy;
import com.zenmind.dao.rest.RestHeader;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;
import com.zenmind.dao.rest.interceptor.RestRespInterceptor;

/**
 * @author zenmind
 * @version 1.0 ST short for spring template
 */
public class RestProxySTImpl implements IntfRestProxy {
	final private String HEADER_ACCESS_VALIDATE_INFO = "validateInfo";
	
	private static RestProxySTImpl restProxy = new RestProxySTImpl();

	public static RestProxySTImpl getInstance() {
		return restProxy;
	}

	private HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(acceptableMediaTypes);
		
		RestHeader restHeaer = RestHeader.newInstance();
		RestRespInterceptor.getInstance().preHandleReq(restHeaer);
		
		Map<String, String> headerMap = restHeaer.getHeader();
		for (String headName : headerMap.keySet()) {
			headers.set(headName, headerMap.get(headName));
		}
		
		ValidateInfo info = AppIdThreadLocal.getInstance().get();
		if (info != null) {
			headers.set(HEADER_ACCESS_VALIDATE_INFO, JsonUtil.getInstance().toJson(info));
		}
		return headers;
	}
	
	@Override
	public RestResp rawReq(String uri, Object postParam) {
		HttpHeaders headers = buildHeaders();
		String jsonTarget = JsonUtil.getInstance().toJson(postParam);

		HttpEntity<String> entity = new HttpEntity<String>(jsonTarget, headers);
		RestResp tReturn = null;
		ResponseEntity<RestResp> resp = send(uri, HttpMethod.POST, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				tReturn = restResp;
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(),
						restResp.getTips()));
			}
		}
		return tReturn;
	}
	
	@Override
	public <T> T add(String uri, Object param, Class<T> clazz) {
		HttpHeaders headers = buildHeaders();
		String jsonTarget = JsonUtil.getInstance().toJson(param);

		HttpEntity<String> entity = new HttpEntity<String>(jsonTarget, headers);
		T tReturn = null;
		ResponseEntity<RestResp> resp = send(uri, HttpMethod.POST, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				String tJson = restResp.gettJson();
				tReturn = (T) JsonUtil.getInstance().fromJson(tJson, clazz);
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(),
						restResp.getTips()));
			}
		}

		return tReturn;
	}

	/**
	 * 业务上区分update，代码层面不保证update之已经存在该对象的.
	 */
	@Override
	public <T> void update(String uri, T target) {

		HttpHeaders headers = buildHeaders();
		String jsonTarget = JsonUtil.getInstance().toJson(target);
		HttpEntity<String> entity = new HttpEntity<String>(jsonTarget, headers);

		ResponseEntity<RestResp> resp = send(uri, HttpMethod.PUT, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(),
						restResp.getTips()));
			}
		}

	}
	
	@Override
	public void delete(String uri) {
		HttpHeaders headers = buildHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<RestResp> resp = send(uri, HttpMethod.DELETE, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(),
						restResp.getTips()));
			}
		}
	}
	
	@Override
	public <T> T delete(String uri, Class<T> clazz) {
		HttpHeaders headers = buildHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		T tReturn = null;
		ResponseEntity<RestResp> resp = send(uri, HttpMethod.DELETE, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				String tJson = restResp.gettJson();
				tReturn = (T) JsonUtil.getInstance().fromJson(tJson, clazz);
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(), restResp.getTips()));
			}
		}
		return tReturn;
	}

	@Override
	public <T> T get(String uri, Class<T> clazz) {
		HttpHeaders headers = buildHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		T tReturn = null;

		ResponseEntity<RestResp> resp = send(uri, HttpMethod.GET, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				String tJson = restResp.gettJson();
				tReturn = (T) JsonUtil.getInstance().fromJson(tJson, clazz);
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(),
						restResp.getTips()));
			}
		} else {
			throw (RestProxyException.newInstance(resp.getStatusCodeValue(), "微服务出现异常。"));
		}

		return tReturn;
	}

	private ResponseEntity<RestResp> send(String uri, HttpMethod method, HttpEntity<String> entity) {
		ResponseEntity<RestResp> respEntity = null;
		try {
			respEntity = (ResponseEntity<RestResp>) getTemplate().exchange(uri, method, entity, RestResp.class);
			//使用断路器  在Junit调试模式下，不生效？ 原因应该是测试程序启动的时候 没有加@EnableCircuitBreaker
//			respEntity = HystrixUtil.execute(uri, method, entity);
		} catch (RuntimeException e) {
			throw (RestProxyException.newInstance("微服务出现异常。", e));
		}
		return respEntity;
	}

	@Override
	public <T> List<T> list(String uri, Class<T> clazz) {
		List<T> targetList = null;
		HttpHeaders headers = buildHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<RestResp> resp = send(uri, HttpMethod.GET, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				String tListJson = restResp.gettListJson();
				targetList = JsonUtil.getInstance().parseList(tListJson, clazz);
			} else {
				throw (RestProxyException.newInstance(
						resp.getStatusCodeValue(), restResp.getTips()));
			}
		}
		return targetList;
	}

	private RestTemplate getTemplate() {
		return RestTemplateMgr.getInstance().getTemplate();
	}

	@Override
	public RestResp postFile(String uri, Object postParam) {
		
		RestResp restResp = getTemplate().postForObject(uri, postParam, RestResp.class);
	    RestResp tReturn = null;
		
		if (restResp.getCode() == 200) {
			tReturn = restResp;
		} else {
			throw (RestProxyException.newInstance(restResp.getCode(),
					restResp.getTips()));
		}
		return tReturn;
	}
	
	public RestResp postSingleFile(String uri, Map<String, Object> postParam, Map<String, File> fileMap) {
		return postFile(uri, getSingleFileParams(postParam, fileMap));
	}

	public RestResp postMutiFile(String uri, Map<String, Object> postParam, Map<String, List<File>> filesMap) {
		return postFile(uri, getMutiFileParams(postParam, filesMap));
	}

	private Object getMutiFileParams(Map<String, Object> postParam, Map<String, List<File>> filesMap) {
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		
		Set<String> paramKeys = postParam.keySet();
		for (String key : paramKeys) {
			param.add(key, postParam.get(key));
		}
		
		Set<String> fileKeys = filesMap.keySet();
		for (String key : fileKeys) {
			for (File file : filesMap.get(key)) {
				param.add(key, exchangeStream(file));
			}
		}
		return param;
	}
	
	private Object getSingleFileParams(Map<String, Object> postParam, Map<String, File> fileMap) {
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
		
		Set<String> paramKeys = postParam.keySet();
		for (String key : paramKeys) {
			param.add(key, postParam.get(key));
		}
		
		Set<String> fileKeys = fileMap.keySet();
		for (String key : fileKeys) {
			param.add(key, exchangeStream(fileMap.get(key)));
		}
		return param;
	}

	private ByteArrayResource exchangeStream(final File file) {
		ByteArrayResource resource = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			while (is.read(buffer) > 0) {
				bos.write(buffer);
			}
			is.close();
			bos.close();
			resource = new ByteArrayResource(bos.toByteArray()) {
				@Override
				public String getFilename() throws IllegalStateException {
					return file.getName();
				}

			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}

	public <T> T update(String uri, Object target, Class<T> clazz) {
		HttpHeaders headers = buildHeaders();
		String jsonTarget = JsonUtil.getInstance().toJson(target);

		HttpEntity<String> entity = new HttpEntity<String>(jsonTarget, headers);
		T tReturn = null;
		ResponseEntity<RestResp> resp = send(uri, HttpMethod.PUT, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				String tJson = restResp.gettJson();
				tReturn = (T) JsonUtil.getInstance().fromJson(tJson, clazz);
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(), restResp.getTips()));
			}
		}
		return tReturn;
	}

	public RestResp rawGetReq(String uri) {
		HttpHeaders headers = buildHeaders();
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		RestResp tReturn = null;
		ResponseEntity<RestResp> resp = send(uri, HttpMethod.GET, entity);
		if (resp.getStatusCode().is2xxSuccessful()) {
			RestResp restResp = resp.getBody();
			if (restResp.getCode() == 200) {
				tReturn = restResp;
			} else {
				throw (RestProxyException.newInstance(restResp.getCode(), restResp.getTips()));
			}
		}
		return tReturn;
	}

}