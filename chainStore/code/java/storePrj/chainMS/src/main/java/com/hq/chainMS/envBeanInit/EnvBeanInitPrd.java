package com.hq.chainMS.envBeanInit;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hq.chainMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

/** 
 * @author zenmind 
 * 其中@Profile用来指定工作环境，例如示例中为"dev",那么该类只会在配置文件为“dev”的环境下，才会调用该类 
 * 这个@EnableBinding注解标识用于开启rabbit消息调试，prd环境，需要开启
 */  
@Component
@Profile("prd") //开发环境时生效 
public class EnvBeanInitPrd{
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return RestTemplateMgr.getInstance().init().getTemplate();
	}
}
