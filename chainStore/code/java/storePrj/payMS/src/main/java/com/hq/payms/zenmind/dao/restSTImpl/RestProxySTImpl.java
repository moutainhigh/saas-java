package com.hq.payms.zenmind.dao.restSTImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.hq.payms.common.constants.ServerConstants;
import com.hq.payms.common.validate.ValidateInfoThreadLocal;
import com.hq.payms.common.validate.info.ValidateInfo;
import com.hq.payms.service.auth.AuthConstants;
import com.zenmind.common.encryt.TokenHelper;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.IntfRestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

/**
 * @author zenmind
 * @version 1.0 ST short for spring template
 */
public class RestProxySTImpl implements IntfRestProxy {
	
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
		
		String token = getTokenFromSubject();
		if(StringUtils.isNotBlank(token)){
			headers.set(AuthConstants.HEADER_ACCESS_TOKEN_NAME, token);
		}
		String validateInfo = getValidateInfo();
		if(StringUtils.isNotBlank(validateInfo)){
			headers.set(AuthConstants.HEADER_ACCESS_VALIDATE_INFO, validateInfo);
		}
		return headers;
	}
	
	private String getValidateInfo(){
		ValidateInfo validateInfo = ValidateInfoThreadLocal.getInstance().get();
		//处理payMS主动发起回调请求时，header中没有appId的情况
		if(validateInfo == null) {
			validateInfo = ValidateInfo.newInstance();
			validateInfo.setAppId(ServerConstants.appId); 
		}
		return JsonUtil.getInstance().toJson(validateInfo);
	}
	
	private String getTokenFromSubject(){
		Subject subject = ThreadContext.getSubject();
		if(subject == null){
			return null;
		}
		return TokenHelper.getInstance().sessionIdToToken(subject.getSession().getId());
	}
	
	@Override
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
			throw (RestProxyException.newInstance(resp.getStatusCodeValue(),
					"微服务出现异常。"));
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

	private RestTemplate getTemplate() {
		return RestTemplateMgr.getInstance().getTemplate();
//		return SpringContextUtil.getBean(RestTemplate.class);
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

	@Override
	public <T> T update(String uri, Object param, Class<T> clazz) {
		HttpHeaders headers = buildHeaders();
		String jsonTarget = JsonUtil.getInstance().toJson(param);

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
}