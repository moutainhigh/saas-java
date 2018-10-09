package com.hq.storeManagerMS.envBeanInit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hq.storeManagerMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

/** 
 * @author zenmind 
 * 其中@Profile用来指定工作环境，例如示例中为"expe",那么该类只会在配置文件为“expe”的环境下，才会调用该类 
 * 这个@EnableBinding注解标识用于开启rabbit消息调试，开发环境，可以不需要
 */  
@Component
@Profile("expe") //体验环境时生效 
public class EnvBeanInitExpe{
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateMgr.getInstance().setDevTemplate(restTemplate);
		return restTemplate;
	}
	
}
