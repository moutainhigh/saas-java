package com.hq.storeMS.envBeanInit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hq.storeMS.zenmind.dao.rest.restSTImpl.RestTemplateMgr;

/** 
 * @author zenmind 
 * 其中@Profile用来指定工作环境，例如示例中为"dev",那么该类只会在配置文件为“dev”的环境下，才会调用该类 
 */  
@Component
@Profile("dev") //开发环境时生效 
public class EnvBeanInitDev{
	@Bean
	RestTemplate restTemplate() {
		return RestTemplateMgr.getInstance().init().getTemplate();
	}
	
	@Bean
	public CorsFilter corsFilter(){
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
