package com.hq.chainMS.envBeanInit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hq.chainMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

/** 
 * @author zenmind 
 */  
@Component
@Profile("test") //开发环境时生效 
public class EnvBeanInitTest{
	@Bean
	RestTemplate restTemplate() {
		return RestTemplateMgr.getInstance().init().getTemplate();
	}
}
